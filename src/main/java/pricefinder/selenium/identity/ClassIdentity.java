package pricefinder.selenium.identity;

import org.openqa.selenium.By;

public class ClassIdentity extends FindByElementIdentity {
    @Override
    protected By getBy() {
        return By.className(getIdentityString());
    }

    @Override
    public int getIdentityType() {
        return IdentityTypes.CLASS;
    }
}
