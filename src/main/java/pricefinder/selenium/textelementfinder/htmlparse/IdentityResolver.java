package pricefinder.selenium.textelementfinder.htmlparse;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import pricefinder.selenium.SeleniumElement;
import pricefinder.selenium.identity.IdIdentity;
import pricefinder.selenium.identity.XpathIdentity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IdentityResolver {

    private static List<String> badIdCache = new LinkedList<>();

    public static void resetCache(){
        badIdCache.clear();
    }

    private Stack<Integer> childCountStack;
    private HashMap<String, String> attributes;

    private String buildXpath(){

        String expression = "/*[last()]";

        for (Integer aChildCountStack : childCountStack)
            expression +=
                    "/*[last()-" + String.valueOf(
                            Math.max(aChildCountStack, 0)
                    ) + ']';

        return expression;

    }

    public IdentityResolver(
            Stack<Integer> childCountStack,
            HashMap<String, String> attributes
    ){
        this.childCountStack = childCountStack;
        this.attributes = attributes;
    }

    public SeleniumElement findElement(SearchContext context){

        SeleniumElement element = null;
        List<WebElement> webElements;

        try {

            if (attributes.containsKey("id") && !badIdCache.contains(attributes.get("id"))) {
                try {
                    webElements = context.findElements(By.id(attributes.get("id")));

                    if (webElements.size() != 1)
                        badIdCache.add(attributes.get("id"));

                    else {
                        element = new SeleniumElement(webElements.get(0));
                        element.setIdentity(new IdIdentity(attributes.get("id")));
                    }
                }
                catch (NoSuchElementException e){
                    badIdCache.add(attributes.get("id"));
                    return findElement(context);
                }

            } else {
                element = new XpathIdentity(buildXpath()).find(context);
            }

        }catch (NoSuchElementException e){
            return null;
        }

        return element;

    }

}