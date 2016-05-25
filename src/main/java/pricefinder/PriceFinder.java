package pricefinder;

import pricefinder.exceptions.BadUrlException;
import pricefinder.exceptions.PriceNotFoundException;
import pricefinder.selenium.PageLoader;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class PriceFinder {

    /**
     * Uses to save price element identity on same
     * domain between calls
     */
    private DbAccessProviderInterface dbProvider;

    private DbPriceElementFinderInterface dbFinder;

    private PriceElementFinderInterface finder;

    private PageLoader loader;


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

    public void setLoader(PageLoader loader) {
        this.loader = loader;
    }

    /**
     * Finds price on page with specified url
     * @param url url of page to search price
     * @return price of item
     */
    public String get(String url) throws PriceNotFoundException, BadUrlException{


        Element priceElement;
        loader.loadPage(url);

        if(dbEnable()) {
            priceElement = dbFinder.find(loader, dbProvider);

            if(priceElement != null)
                return priceElement.getText();
            else try {
                dbProvider.remove(((new URL(url)).getHost()));
            } catch (MalformedURLException e) {
                throw new BadUrlException();
            }

        }

        priceElement = finder.find(loader);

        if(priceElement != null){

            if(dbEnable() && priceElement.getIdentity() != null) try {

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
