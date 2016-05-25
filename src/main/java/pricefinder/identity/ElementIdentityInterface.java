package pricefinder.identity;

import org.openqa.selenium.WebDriver;
import pricefinder.Element;

public interface ElementIdentityInterface {

    /**
     *  Finds web element with this identity
     *
     * @param driver current page driver
     * @return web element suits this identity
     */
    Element find(WebDriver driver);


    String getIdentityString();

    void setIdentityString(String string);

    int getIdentityType();

}
