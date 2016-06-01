package pricefinder;

import org.openqa.selenium.WebDriver;
import pricefinder.exceptions.PriceNotFoundException;
import pricefinder.identity.DomainIdentity;
import pricefinder.driver.DriverProviderInterface;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class PriceFinder {

    /**
     * Uses to save price element identity on same
     * domain between calls
     */
    private DbAccessProviderInterface dbProvider;

    private DriverProviderInterface driverProvider;

    private PriceElementFinderInterface finder;

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
    public String get(String url) throws PriceNotFoundException, MalformedURLException {

        Element priceElement;
        DomainIdentity newIdentity;
        DomainIdentity identity = null;
        WebDriver driver = driverProvider.getDriver();
        String host = (new URL(url)).getHost();

        driver.get(url);
        driverProvider.afterPageLoad(driver);

        if (dbEnable()) identity = dbProvider.readIdentity(host);

        priceElement = finder.findByIdentity(driver, identity);

        if(priceElement == null) priceElement = finder.find(driver);
        if(priceElement == null) throw new PriceNotFoundException();

        if(dbEnable()) {

            newIdentity = finder.getRecentIdentity();

            if (newIdentity != null && !newIdentity.equals(identity)) {
                if (identity != null) dbProvider.remove(host);
                newIdentity.setDomain(host);
                dbProvider.write(identity);
            }

        }

        return priceElement.getText();

    }

}