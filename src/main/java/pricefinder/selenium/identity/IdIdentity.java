package pricefinder.selenium.identity;

import org.openqa.selenium.By;

public class IdIdentity extends FindByElementIdentity {

    public IdIdentity(String identityString) {
        super(identityString);
    }

    @Override
    protected By getBy() {
        return By.id(getIdentityString());
    }

    @Override
    public String getIdentityType() {
        return IdentityTypes.ID;
    }

}
