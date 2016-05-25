package pricefinder.selenium.filters;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.PriceCandidate;

import java.util.LinkedList;

public class FilterManager {

    private LinkedList<Filter> filters = new LinkedList<>();

    public FilterManager addFilter(Filter filter){
        this.filters.add(filter);
        return this;
    }

    public LinkedList<PriceCandidate> filter(LinkedList<PriceCandidate> elements, WebDriver driver){

        LinkedList<PriceCandidate> filteredElements = new LinkedList<>(elements);

        for(Filter filter : filters) {
            filter.setExecutor(driver);
            filter.beforeFilter(filteredElements, driver);
        }

        for(PriceCandidate candidate : elements)
            for(Filter filter : filters)
            filter.filter(candidate, driver);

        filteredElements.sort(((o1, o2) -> o1.getScore() - o2.getScore()));

        return filteredElements;

    }

}
