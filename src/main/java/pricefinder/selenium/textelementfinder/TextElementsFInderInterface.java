package pricefinder.selenium.textelementfinder;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.SeleniumElement;

import java.util.List;

public interface TextElementsFinderInterface {

    List<SeleniumElement> find(WebDriver driver);

}