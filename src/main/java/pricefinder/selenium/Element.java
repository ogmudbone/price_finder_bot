package pricefinder.selenium;

import org.openqa.selenium.WebElement;

public class Element extends pricefinder.Element {

    private WebElement webElement;

    public Element(WebElement element){
        this.webElement = element;
    }

    public Element(Element element){
        super(element);
        this.webElement = element.webElement;
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    public WebElement getWebElement() {
        return webElement;
    }

}