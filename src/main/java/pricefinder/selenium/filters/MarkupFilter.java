package pricefinder.selenium.filters;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.Element;
import pricefinder.selenium.PriceCandidate;

import java.util.List;

public class MarkupFilter extends Filter {

    public final int ATTRIBUTE_SCORE = 10;
    public final int OLD_PENALTY = 10;
    public final int ITEMPROP_SCORE = 40;

    private void checkAttributes(PriceCandidate candidate, Element element){

        String[] attributes = {"id", "class"};

        for(String attribute : attributes)

            if(element.getWebElement().getAttribute(attribute) != null){
                if(element.getWebElement().getAttribute(attribute).contains("price"))
                    candidate.addScore(ATTRIBUTE_SCORE);
                if(element.getWebElement().getAttribute(attribute).contains("old"))
                    candidate.addScore(-OLD_PENALTY);
            }

        if(element.getWebElement().getAttribute("itemprop") != null &&
                element.getWebElement().getAttribute("itemprop").equals("price"))
            candidate.addScore(ITEMPROP_SCORE);

    }

    @Override
    void filter(PriceCandidate input, WebDriver driver) {
        checkAttributes(input, input);

        List<Element> childs = input.getChilds();

        for(Element child : childs)
            checkAttributes(input, child);

        if(input.getParent(0) != null){
            checkAttributes(input, input.getParent(0));

            childs = input.getParent(0).getChilds();

            for(Element child : childs)
                checkAttributes(input, child);

        }

    }

    @Override
    void beforeFilter(List<PriceCandidate> input, WebDriver driver) {

    }

}