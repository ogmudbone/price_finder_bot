package pricefinder.driver;

import org.openqa.selenium.WebDriver;

public interface DriverProviderInterface {

    WebDriver getDriver();
    void afterPageLoad(WebDriver driver);

}
