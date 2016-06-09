package pricefinder.selenium;

import pricefinder.driver.HtmlUnitDriverProvider;

public class SeleniumPriceFinder extends pricefinder.PriceFinder{

    public SeleniumPriceFinder(){
        this.setDriverProvider(new HtmlUnitDriverProvider());
        this.setFinder(new SeleniumPriceElementFinder());
    }

}
