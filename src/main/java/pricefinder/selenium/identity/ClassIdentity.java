package pricefinder.selenium.identity;

import org.openqa.selenium.By;

public class ClassIdentity extends FindByElementIdentity {
    public ClassIdentity(String identityString) {
        super(identityString);
    }

    @Override
    protected By getBy() {
        return By.className(getIdentityString());
    }

    @Override
    public String getIdentityType() {
        return IdentityTypes.CLASS;
    }
}
