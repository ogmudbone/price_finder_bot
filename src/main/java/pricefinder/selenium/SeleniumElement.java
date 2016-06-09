package pricefinder.selenium;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SeleniumElement extends pricefinder.Element {

    private WebElement webElement;
    private static int screenHeight = -1;
    private static int screenWidth = -1;

    private ArrayList<WebElement> parents = new ArrayList<>();
    private Point location;
    private Dimension rect;

    public SeleniumElement(WebElement element){
        this.webElement = element;
    }

    public SeleniumElement(SeleniumElement element){
        super(element);
        this.webElement = element.webElement;
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    public WebElement getWebElement(){
        return webElement;
    }

    private static void initScreenDimension(WebDriver driver){
        screenHeight = driver.manage().window().getSize().getHeight();
        screenWidth = driver.manage().window().getSize().getWidth();
    }

    private void fillParent(int depth){
        if(parents.size() - 1 < depth){
            fillParent(depth - 1);

            if(parents.size() == 0 || parents.get(parents.size() - 1) != null) {
                try {
                    parents.add(webElement.findElement(By.xpath("..")));
                } catch (NoSuchElementException e) {
                    parents.add(null);
                }
            }
            else
                parents.add(null);
        }
    }

    public SeleniumElement getParent(int depth){

        if(parents.size() -1 < depth){
            fillParent(depth);
        }

        return new SeleniumElement(parents.get(depth));

    }

    public List<SeleniumElement> getChilds(){

        List<SeleniumElement> childs = new LinkedList<>();

        try {

            List<WebElement> webElements = getWebElement().findElements(By.xpath("child::*"));

            for (WebElement element : webElements)
                childs.add(new SeleniumElement(element));

            return childs;

        }catch (NoSuchElementException e){
            return childs;
        }

    }

    public String getCss(String propertyName){

        try{
            return webElement.getCssValue(propertyName);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass().toString());
            return "";
        }

    }

    public Point getLocation(){

        if(location == null)
            try {
                location = webElement.getLocation();
            }
            catch (StaleElementReferenceException e){
                location = null;
            }

        return location;

    }

    public Dimension getSize(){

        if(rect == null)
            try {
                rect = webElement.getSize();
            }
            catch (StaleElementReferenceException e){
                rect = null;
            }

        return rect;

    }

    public boolean isOnScreen(WebDriver driver){

        if(screenHeight == -1 || screenWidth == -1)
            initScreenDimension(driver);

        Point location = getLocation();
        Dimension size = getSize();

        if(location == null || size == null)
            return false;

        int x = location.getX() + size.getWidth();
        int y = location.getY() + size.getHeight();

        return x < screenWidth && y < screenHeight;

    }

    public Point getCenterPoint(){

        Point location = getLocation();
        Dimension size = getSize();

        if(location == null ||size == null)
            return null;

        return new Point(
                location.getX() + (size.getWidth()) / 2,
                location.getY() + (size.getHeight()) / 2
        );

    }

}