package pricefinder.selenium.identity;

import org.openqa.selenium.By;

public class IdIdentity extends FindByElementIdentity {

    @Override
    protected By getBy() {
        return By.id(getIdentityString());
    }

    @Override
    public int getIdentityType() {
        return IdentityTypes.ID;
    }

}
