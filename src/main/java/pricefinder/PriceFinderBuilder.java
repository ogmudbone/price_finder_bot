package pricefinder;

import pricefinder.selenium.PageLoader;

public class PriceFinderBuilder {

    private DbAccessProviderInterface dbProvider;
    private DbPriceElementFinderInterface dbFinder;
    private PriceElementFinderInterface finder;
    private PageLoader loader;

    private PriceFinder priceFinder;

    public PriceFinderBuilder(PriceFinder finder){
        this.priceFinder = finder;
    }

    public PriceFinderBuilder setDbProvider(DbAccessProviderInterface dbProvider) {
        this.dbProvider = dbProvider;
        return this;
    }

    public PriceFinderBuilder setDbFinder(DbPriceElementFinderInterface dbFinder) {
        this.dbFinder = dbFinder;
        return this;
    }

    public PriceFinderBuilder setFinder(PriceElementFinderInterface finder) {
        this.finder = finder;
        return this;
    }

    public PriceFinderBuilder setLoader(PageLoader loader) {
        this.loader = loader;
        return this;
    }

    public PriceFinder build(){

        PriceFinder newPriceFinder = this.priceFinder != null ? this.priceFinder : new PriceFinder() {};
        newPriceFinder.setDbAccessProvider(dbProvider);
        newPriceFinder.setDbFinder(dbFinder);
        newPriceFinder.setLoader(loader);
        newPriceFinder.setFinder(finder);

        return newPriceFinder;

    }

}
