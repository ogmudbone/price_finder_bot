package pricefinder;

import pricefinder.exceptions.PriceNotFoundException;

public abstract class BasePriceFinder {

    /**
     * Uses to save price element identity on same
     * domain between calls
     */
    private DbAccessProviderInterface dbProvider;


    public void setDbAccessProvider(DbAccessProviderInterface provider){
        this.dbProvider = provider;
    }

    /**
     * Finds price on page with specified url
     * @param url url of page to search price
     * @return price of item
     */
    public String get(String url) throws PriceNotFoundException{

        return "lol";

    }

}
