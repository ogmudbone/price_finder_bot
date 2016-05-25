package pricefinder.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import pricefinder.PageLoaderInterface;
import pricefinder.exceptions.BadUrlException;
import pricefinder.selenium.driver.DriverProviderInterface;

public class PageLoader implements PageLoaderInterface {

    private WebDriver driver;
    private DriverProviderInterface driverProvider;

    public void setDriverProvider(DriverProviderInterface driverProvider) {
        this.driverProvider = driverProvider;
    }

    @Override
    public void loadPage(String url) throws BadUrlException{

        WebDriver driver = driverProvider.getDriver();

        try {
            driver.get(url);
        }
        catch (WebDriverException e){
            throw new BadUrlException();
        }

        driverProvider.afterPageLoad(driver);

    }

}
