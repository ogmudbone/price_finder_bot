package pricefinder.driver;

import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitDriverProvider implements DriverProviderInterface {

    public WebDriver getDriver() {
        return new CustomHtmlUnitDriver();
    }

    public void afterPageLoad(WebDriver driver) {
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
    }

    private static class CustomHtmlUnitDriver extends HtmlUnitDriver{

        public CustomHtmlUnitDriver(){
            super();
            this.getWebClient().getCurrentWindow().setOuterHeight(768);
            this.getWebClient().getCurrentWindow().setOuterWidth(1366);
            this.getWebClient().addRequestHeader("X-Javascript-Enabled", "false");
        }

        @Override
        protected WebClient modifyWebClient(WebClient client) {
            WebClient modifiedClient = super.modifyWebClient(client);

            modifiedClient.getOptions().setThrowExceptionOnScriptError(false);
            return modifiedClient;
        }

    }

}
