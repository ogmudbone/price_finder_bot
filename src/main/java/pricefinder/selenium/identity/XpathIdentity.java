package pricefinder.selenium.identity;

import org.openqa.selenium.By;

public class XpathIdentity extends FindByElementIdentity {

    public int getIdentityType() {
        return IdentityTypes.XPATH;
    }

    @Override
    protected By getBy() {
        return By.xpath(getIdentityString());
    }

}
