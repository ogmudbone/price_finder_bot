package pricefinder.selenium.filters;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.PriceCandidate;

import java.util.LinkedList;
import java.util.List;

public class FilterManager {

    private LinkedList<Filter> filters = new LinkedList<>();

    public FilterManager addFilter(Filter filter){
        this.filters.add(filter);
        return this;
    }

    public List<PriceCandidate> filter(List<PriceCandidate> elements, WebDriver driver){

        for(Filter filter : filters) {
            filter.setExecutor(driver);
            filter.beforeFilter(elements, driver);
        }

        for(PriceCandidate candidate : elements)
            for(Filter filter : filters)
            filter.filter(candidate, driver);

        elements.sort(((o1, o2) -> o1.getScore() - o2.getScore()));

        return elements;

    }

}
