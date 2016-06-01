package pricefinder;

import pricefinder.driver.DriverProviderInterface;

public class PriceFinderBuilder {

    private DbAccessProviderInterface dbProvider;
    private PriceElementFinderInterface finder;
    private DriverProviderInterface driverProvider;

    private PriceFinder priceFinder;

    public PriceFinderBuilder(PriceFinder finder){
        this.priceFinder = finder;
    }

    public PriceFinderBuilder setDbProvider(DbAccessProviderInterface dbProvider) {
        this.dbProvider = dbProvider;
        return this;
    }

    public PriceFinderBuilder setDriverProvider(DriverProviderInterface driverProvider){
        this.driverProvider = driverProvider;
        return this;
    }

    public PriceFinderBuilder setFinder(PriceElementFinderInterface finder) {
        this.finder = finder;
        return this;
    }

    public PriceFinder build(){

        PriceFinder newPriceFinder = this.priceFinder != null ? this.priceFinder : new PriceFinder() {};
        newPriceFinder.setDbAccessProvider(dbProvider);
        newPriceFinder.setFinder(finder);
        newPriceFinder.setDriverProvider(driverProvider);

        return newPriceFinder;

    }

}
