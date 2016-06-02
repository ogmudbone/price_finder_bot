package pricefinder.selenium.filters;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import pricefinder.selenium.PriceCandidate;

import java.util.List;

public class PositionFilter extends Filter {

    public final static int ON_SCREEN_SCORE = 20;
    public final static int CENTER_SCORE = 15;

    private Point centerPoint;
    private double maxDistance;

    private void init(WebDriver driver){

        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        centerPoint = new Point(screenWidth / 2, screenHeight / 2);
        maxDistance = Math.sqrt(centerPoint.getY()^2 + centerPoint.getX()^2);

    }

    private void scoreForScreenCenterDistance(PriceCandidate candidate, double distance){
        candidate.addScore(
                (int)((1 - distance/maxDistance)*CENTER_SCORE)
        );
    }

    @Override
    void filter(PriceCandidate input, WebDriver driver) {

        if(input.isOnScreen(driver)) {

            input.addScore(ON_SCREEN_SCORE);

            Point elementCenter = input.getCenterPoint();

            double pointDistance = Math.sqrt(
                    (elementCenter.x + centerPoint.x) ^ 2 + (elementCenter.y + centerPoint.y) ^ 2
            );

            scoreForScreenCenterDistance(input, pointDistance);

        }

    }

    @Override
    void beforeFilter(List<PriceCandidate> input, WebDriver driver) {
        init(driver);
    }

}
