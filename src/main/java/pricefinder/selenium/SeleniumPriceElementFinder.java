package pricefinder.selenium;

import org.openqa.selenium.WebDriver;
import pricefinder.Element;
import pricefinder.PriceElementFinderInterface;
import pricefinder.identity.DomainIdentity;
import pricefinder.selenium.filters.*;
import pricefinder.selenium.identity.IdentityTypes;
import pricefinder.selenium.textelementfinder.TextElementsFinderInterface;
import pricefinder.selenium.textelementfinder.htmlparse.TextElementFinder;

import java.util.ArrayList;
import java.util.List;

public class SeleniumPriceElementFinder implements PriceElementFinderInterface {

    private DomainIdentity recentIdentity = null;
    private TextElementsFinderInterface textElementFinder = new TextElementFinder();
    private FilterManager filterManager = (new FilterManager())
            .addFilter(new ContentFilter())
            .addFilter(new FontSizeFilter())
            .addFilter(new MarkupFilter())
            .addFilter(new PositionFilter());

    protected void setTextElementFinder(TextElementsFinderInterface textElementFinder) {
        this.textElementFinder = textElementFinder;
    }

    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    @Override
    public Element find(WebDriver driver) {

        List<SeleniumElement> textElements =
                textElementFinder.find(driver);
        ArrayList<PriceCandidate> candidates = new ArrayList<>(textElements.size());

        for(SeleniumElement element : textElements)
        candidates.add(new PriceCandidate(element));

        filterManager.filter(candidates, driver);

        for(PriceCandidate candidate : candidates){
            System.out.println("Score : " + candidate.getScore());
            System.out.println(candidate.getText());
            System.out.println();
        }

        return candidates.get(candidates.size() - 1);

    }

    @Override
    public DomainIdentity getRecentIdentity() {
        return recentIdentity;
    }

    @Override
    public Element findByIdentity(
            WebDriver driver, DomainIdentity identity
    ) {
        return identity == null ?
                null :
                IdentityTypes.cast(identity).find(driver);
    }

}