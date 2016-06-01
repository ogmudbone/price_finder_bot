package pricefinder.selenium.identity;

import pricefinder.identity.DomainIdentity;

public class IdentityTypes {

    public final static String XPATH = "xpath";
    public final static String ID = "id";
    public final static String CLASS = "class";

    public static FindByElementIdentity createInstance(String type, String identityString){

        FindByElementIdentity identity = null;

        switch (type){
            case XPATH:
                identity = new XpathIdentity(identityString);
                break;
            case ID:
                identity = new IdIdentity(identityString);
                break;
            case CLASS:
                identity = new ClassIdentity(identityString);
        }

        return identity;

    }

    public static FindByElementIdentity cast(DomainIdentity identity){

        FindByElementIdentity castedIdentity = null;

        switch (identity.getIdentityType()){
            case XPATH:
                castedIdentity = ((XpathIdentity) identity);
                break;
            case ID:
                castedIdentity = ((IdIdentity) identity);
                break;
            case CLASS:
                castedIdentity = ((ClassIdentity) identity);
        }

        return castedIdentity;

    }

}
