package pricefinder.selenium.identity;

import org.openqa.selenium.By;

public class XpathIdentity extends FindByElementIdentity {

    public XpathIdentity(String identityString) {
        super(identityString);
    }

    public String getIdentityType() {
        return IdentityTypes.XPATH;
    }

    @Override
    protected By getBy() {
        return By.xpath(getIdentityString());
    }

}
