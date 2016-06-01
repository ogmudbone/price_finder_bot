package pricefinder.selenium;

import org.openqa.selenium.WebElement;

public class PriceCandidate extends Element {

    private int score;

    public PriceCandidate(WebElement element) {
        super(element);
    }

    public PriceCandidate(Element element){
        super(element);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

}