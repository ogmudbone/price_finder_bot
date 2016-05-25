package pricefinder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import pricefinder.driver.DriverProviderInterface;
import pricefinder.exceptions.BadUrlException;
import pricefinder.exceptions.PriceNotFoundException;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BasePriceFinder {

    /**
     * Uses to save price element identity on same
     * domain between calls
     */
    private DbAccessProviderInterface dbProvider;

    private DbPriceElementFinderInterface dbFinder;

    private PriceElementFinderInterface finder;

    private DriverProviderInterface driverProvider;

    /**
     * Check, availability of db access
     * @return is db access enable
     */
    protected boolean dbEnable(){
        return dbProvider != null;
    }

    public void setDbAccessProvider(DbAccessProviderInterface provider){
        this.dbProvider = provider;
    }

    public void setDbFinder(DbPriceElementFinderInterface dbFinder){
        this.dbFinder = dbFinder;
    }

    public void setFinder(PriceElementFinderInterface finder) {
        this.finder = finder;
    }

    public void setDriverProvider(DriverProviderInterface driverProvider) {
        this.driverProvider = driverProvider;
    }

    /**
     * Finds price on page with specified url
     * @param url url of page to search price
     * @return price of item
     */
    public String get(String url) throws PriceNotFoundException, BadUrlException{

        WebDriver driver = driverProvider.getDriver();
        Element priceElement;

        try {
            driver.get(url);
        }
        catch (WebDriverException e){
            throw new BadUrlException();
        }

        driverProvider.afterPageLoad(driver);

        if(dbEnable()) {
            priceElement = dbFinder.find(driver, dbProvider);

            if(priceElement != null)
                return priceElement.getText();
            else try {
                dbProvider.remove(((new URL(url)).getHost()));
            } catch (MalformedURLException e) {
                throw new BadUrlException();
            }

        }

        priceElement = finder.find(driver);

        if(priceElement != null){

            if(dbEnable()) try {

                dbProvider.write(
                        (new URL(url)).getHost(),
                        priceElement.getIdentity().getIdentityString(),
                        priceElement.getIdentity().getIdentityType()
                );

                } catch (MalformedURLException e) {
                    throw new BadUrlException();
                }

            return priceElement.getText();

        }
        else throw new PriceNotFoundException();

    }

}
