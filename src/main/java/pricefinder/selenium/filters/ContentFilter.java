package pricefinder.selenium.filters;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.Element;
import pricefinder.selenium.PriceCandidate;

import java.util.List;

public class ContentFilter extends Filter {

    public static final int CLEARNESS_SCORE = 50;
    public static final int CURRENCY_SCORE = 10;
    public static final int PARENT_DEPTH = 2;

    public final static String[] currencies = {
            "uah", "$", "rub", "грн", "руб", "гривен", "дол",
            "ГРН", "UAH", "RUB", "РУБ", "ГРИВЕН"
    };

    private static String currencyRegexp(){
        String regexp = "(";

        for(String currency : currencies)
            regexp += currency + "|";

        return regexp.substring(0, regexp.length() - 2) + ")";

    }

    private boolean isCurrency(String string){

        return string.matches("(?i)" + currencyRegexp() + "\\.?");

    }

    private boolean hasCurrency(String string){
        for(String currency : currencies)
            if(string.contains(currency))
                return true;
        return false;
    }

    private void scoreClearness(PriceCandidate candidate){

        String candidateText = candidate.getText();

        if (candidateText == null)
            return;

        int badSymbolsCount = 0;

        for(int i = 0; i < candidateText.length(); i++){

            if(isCurrency(candidateText.substring(i)))
                break;

            if(
                    (candidateText.charAt(i) > 57 || candidateText.charAt(i) < 48) &&
                            candidateText.charAt(i) != 46 && candidateText.charAt(i) != 44 &&
                            candidateText.charAt(i) != 32
                    )
                badSymbolsCount++;

        }

        candidate.addScore((int)(
                CLEARNESS_SCORE*( 1 - (float)badSymbolsCount / (float)candidateText.length() )
        ));

    }

    private void scoreCurrency(PriceCandidate candidate){

        Element parent = candidate.getParent(PARENT_DEPTH);

        if(parent != null) {

            String parentText = parent.getText();

            if (parentText != null && hasCurrency(parentText))
                candidate.addScore(CURRENCY_SCORE);

        }

    }

    @Override
    void filter(PriceCandidate input, WebDriver driver) {
        scoreClearness(input);
        scoreCurrency(input);
    }

    @Override
    void beforeFilter(List<PriceCandidate> input, WebDriver driver) {

    }

}