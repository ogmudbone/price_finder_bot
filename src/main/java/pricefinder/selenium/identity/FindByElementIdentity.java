package pricefinder.selenium.identity;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pricefinder.Element;
import pricefinder.identity.BaseElementIdentity;

abstract class FindByElementIdentity extends BaseElementIdentity {

    @Override
    public Element find(WebDriver driver) {
        try {

            return new Element( driver.findElement( getBy() ) );

        }
        catch (ElementNotFoundException e){
            return null;
        }
    }

    protected abstract By getBy();

}
