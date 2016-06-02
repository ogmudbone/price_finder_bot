package pricefinder.selenium.filters;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.Element;
import pricefinder.selenium.PriceCandidate;

import java.util.HashMap;
import java.util.List;

public class MarkupFilter extends Filter {

    public final int ATTRIBUTE_SCORE = 5;
    public final int OLD_PENALTY = 5;

    public final String GET_ATTR_JS = "var items = {};" +
            " for (index = 0; index < arguments[0].attributes.length; ++index) {" +
            " items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value" +
            " }; " +
            "return items;";


    private void checkAttributes(PriceCandidate candidate, Element element){

        //noinspection unchecked
        HashMap<String, String> attributes =
                (HashMap<String, String>)executeJavascript(GET_ATTR_JS, element.getWebElement());

        for(String value : attributes.values()) {
            if (value.contains("price"))
                candidate.addScore(ATTRIBUTE_SCORE);
            if(value.contains("old"))
                candidate.addScore(-OLD_PENALTY);
        }

        for(String key : attributes.keySet()) {
            if (key.contains("price"))
                candidate.addScore(ATTRIBUTE_SCORE);
        }

    }

    @Override
    void filter(PriceCandidate input, WebDriver driver) {
        checkAttributes(input, input);
        checkAttributes(input, input.getParent(0));
    }

    @Override
    void beforeFilter(List<PriceCandidate> input, WebDriver driver) {

    }

}