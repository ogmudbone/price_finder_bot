package pricefinder.selenium.filters;

import com.gargoylesoftware.htmlunit.javascript.host.css.CSS2Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pricefinder.selenium.PriceCandidate;

import java.util.LinkedList;


public abstract class Filter {

    private JavascriptExecutor executor;

    void setExecutor(WebDriver driver){

        this.executor = (JavascriptExecutor)driver;

    }

    protected CSS2Properties getCssProperties(WebElement element){
        return (CSS2Properties)executor.
                executeScript("return getComputedStyle(arguments[0])", element);
    }

    protected Object executeJavascript(String script, Object... args){
        return executor.executeScript(script, args);
    }

    abstract void filter(PriceCandidate input, WebDriver driver);
    abstract void beforeFilter(LinkedList<PriceCandidate> input, WebDriver driver);

}
