package pricefinder.selenium.textelementfinder;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.Element;

import java.util.List;

public interface TextElementsFinderInterface {

    List<Element> find(WebDriver driver);

}