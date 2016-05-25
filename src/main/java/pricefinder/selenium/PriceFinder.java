package pricefinder.selenium;

import pricefinder.PriceFinderBuilder;

public class PriceFinder extends pricefinder.PriceFinder{

    public static PriceFinder getSeleniumPriceFinder(){
        return ((PriceFinder) (new PriceFinderBuilder(new PriceFinder()))
                .setFinder(new PriceElementFinder())
                .setDbFinder(new DbPriceElementFinder())
                .setLoader(new PageLoader())
                .build());
    }

}
