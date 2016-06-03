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
            price = finder.get("http://www.foxtrot.com.ua/ru/shop/kondicyonery_delfa_acr-09.html");
        } catch (PriceNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(price);
    }

}
