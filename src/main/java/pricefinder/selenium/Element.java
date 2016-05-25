package pricefinder.selenium;

import org.openqa.selenium.WebElement;
import pricefinder.identity.ElementIdentityInterface;

public class Element extends pricefinder.Element {

    private WebElement webElement;

    public Element(WebElement element){
        this.webElement = element;
    }

    @Override
    public ElementIdentityInterface getIdentity() {
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

    public WebElement getWebElement() {
        return webElement;
    }
}
