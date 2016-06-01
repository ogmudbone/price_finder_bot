package pricefinder.selenium;

import pricefinder.PriceFinderBuilder;
import pricefinder.driver.HtmlUnitDriverProvider;

public class PriceFinder extends pricefinder.PriceFinder{

    public static PriceFinder getSeleniumPriceFinder(){
        return ((PriceFinder) (new PriceFinderBuilder(new PriceFinder()))
                .setFinder(new PriceElementFinder())
                .setDriverProvider(new HtmlUnitDriverProvider())
                .build());
    }

}
