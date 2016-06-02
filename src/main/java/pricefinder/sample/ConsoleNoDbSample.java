package pricefinder.sample;

import pricefinder.exceptions.PriceNotFoundException;
import pricefinder.selenium.PriceFinder;

import java.net.MalformedURLException;
import java.util.logging.Level;

public class ConsoleNoDbSample {

    public static void main(String[] args){
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        PriceFinder finder = PriceFinder.getSeleniumPriceFinder();
        String price = null;
        try {
            price = finder.get("https://f.ua/campingaz/fold-n-cool-classic-20l-dark-blue.html");
        } catch (PriceNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(price);
    }

}
