package pricefinder.selenium.identity;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import pricefinder.identity.DomainIdentity;
import pricefinder.selenium.SeleniumElement;

public abstract class FindByElementIdentity extends DomainIdentity {

    public FindByElementIdentity(String identityString){
        this.setIdentityString(identityString);
        this.setIdentityType(getIdentityType());
    }

    public SeleniumElement find(SearchContext driver) {
        try {

            SeleniumElement element = new SeleniumElement( driver.findElement( getBy() ) );
            element.setIdentity(this);
            return element;
        }
        catch (ElementNotFoundException e){
            return null;
        }
    }

    protected abstract By getBy();

}
