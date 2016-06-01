package pricefinder;

import org.openqa.selenium.WebDriver;
import pricefinder.identity.DomainIdentity;

public interface PriceElementFinderInterface {

    Element find(
            WebDriver driver
    );

    DomainIdentity getRecentIdentity();

    Element findByIdentity(
            WebDriver driver,
            DomainIdentity identity
    );

}
