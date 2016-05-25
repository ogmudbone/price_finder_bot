package pricefinder.identity;

public abstract class BaseElementIdentity implements ElementIdentityInterface {

    private String identityString;

    public String getIdentityString(){
        return identityString;
    }

    public void setIdentityString(String string){
        this.identityString = string;
    }

}
