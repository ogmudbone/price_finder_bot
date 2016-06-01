package pricefinder;

import pricefinder.identity.DomainIdentity;

public abstract class Element {

    private DomainIdentity identity;

    public abstract String getText();

    public DomainIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(DomainIdentity identity) {
        this.identity = identity;
    }

    public Element(){}

    public Element(Element element){
        this.identity = element.identity;
    }

}
