package pricefinder.selenium.filters;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.PriceCandidate;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class FontSizeFilter extends Filter {

    public final static int FONT_SIZE_SCORE = 15;

    @Override
    void beforeFilter(List<PriceCandidate> input, WebDriver driver) {

        int[] fontSizes = new int[input.size()];

        for(int i = 0; i < input.size(); i++){

            String fontSizeString = input.get(i).getCss("font-size");
            int fontSize = -1;
            if(fontSizeString != null && fontSizeString.contains("px"))
                fontSize = Float.valueOf(fontSizeString.substring(0, fontSizeString.indexOf("px"))).intValue();

            fontSizes[i] = fontSize;

        }

        double averageSize = IntStream.of(fontSizes).filter(value -> value > 0).average().getAsDouble();
        double[] variance = new double[fontSizes.length];

        for(int i = 0; i < variance.length;i++)
            variance[i] = fontSizes[i] > -1 ? Math.pow(averageSize - fontSizes[i], 2) : -1;

        double maxVariance = DoubleStream.of(variance).max().getAsDouble();

        for(int i = 0; i < input.size();i++){

            if(fontSizes[i] == -1){
                input.get(i).addScore(5);
            }

            else if(fontSizes[i] > averageSize){
                input.get(i).addScore((int)(FONT_SIZE_SCORE*(variance[i]/maxVariance)));
            }

        }

    }

    @Override
    void filter(PriceCandidate input, WebDriver driver) {

    }

}
