package pricefinder.identity;

public class DomainIdentity {

    private String domain;
    private String identityString;
    private String identityType;
    private boolean useAjax;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIdentityString() {
        return identityString;
    }

    public void setIdentityString(String identityString) {
        this.identityString = identityString;
    }

    public boolean isUseAjax() {
        return useAjax;
    }

    public void setUseAjax(boolean useAjax) {
        this.useAjax = useAjax;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public boolean equals(DomainIdentity otherIdentity) {

        return otherIdentity != null &&
                this.domain.equals(otherIdentity.domain) &&
                this.identityString.equals(otherIdentity.identityString) &&
                this.identityType.equals(otherIdentity.identityType) &&
                this.useAjax == otherIdentity.useAjax;

    }

}