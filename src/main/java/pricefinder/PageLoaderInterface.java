package pricefinder;

import pricefinder.exceptions.BadUrlException;

public interface PageLoaderInterface {

    void loadPage(String url) throws BadUrlException;

}