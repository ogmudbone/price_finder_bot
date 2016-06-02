package pricefinder.selenium.textelementfinder.htmlparse;

import org.openqa.selenium.WebDriver;
import pricefinder.selenium.Element;
import pricefinder.selenium.textelementfinder.TextElementsFinderInterface;
import pricefinder.selenium.textelementfinder.htmlparse.tree.WebElementsPathForest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class TextElementFinder implements TextElementsFinderInterface{

    private boolean checkElementText(String text){
        for(char symbol : text.toCharArray())
            if(symbol > 47 && symbol < 58)
                return text.length() < 30;

        return false;
    }

    private String stripComments(String html){
        return html.replaceAll("(?s)<!--.*?-->", "");
    }

    private boolean isComment(String tag){
        return tag.length() >= 3 && tag.substring(0, 3).equals("!--");
    }

    private int skipJavaScript(CharSequence html, int i){

        final String scriptTag = "<script";
        char quote = 0;
        int tagCompletion = 0;
        int j = i;

        for( ;j > -1 && tagCompletion < 7; j-- ){

            if(html.charAt(j) == '\'' || html.charAt(j) == '"' && html.charAt(j-1) != '\\')
                quote = quote == html.charAt(j) ? 0 :
                        ( quote == 0 ? html.charAt(j) : quote );

            if(quote == 0)
            if(scriptTag.charAt(6 - tagCompletion) == html.charAt(j))
                tagCompletion++;
            else
            tagCompletion = 0;

        }

        return j;

    }

    private HashMap<String,String> parseAttributes(String tagData){

        HashMap<String, String> attributes = new HashMap<>();
        String[] splittedAttributes;

        return attributes;

    }

    private ParseDataBuffer tryFindTag(CharSequence html, int i){

        IdentityResolver.resetCache();

        String innerTextBuffer = "";
        String tagBuffer = "";
        String tag;
        int j = i;
        int arrowPos = -1; // '>' pos
        ParseDataBuffer buffer = new ParseDataBuffer();

        for (
                ;j > -1 && (html.charAt(j) != '<' || (j > 0 && html.charAt(j-1) == '\\')); j--
                ) {

            if(html.charAt(j) != '\r' && html.charAt(j) != '\n' && html.charAt(j) != '\t') {

                if(html.charAt(j) == '>') {
                    if(arrowPos != -1){
                        innerTextBuffer += tagBuffer;
                        tagBuffer = "";
                    }
                    arrowPos = j;
                }


                    if (arrowPos == -1)
                        innerTextBuffer += html.charAt(j);
                    else
                        tagBuffer += html.charAt(j);

            }

        }

        j--;

        tagBuffer = new StringBuffer(tagBuffer).reverse().toString();

        tag = tagBuffer.substring(
                        0, tagBuffer.indexOf(' ') != -1 ? tagBuffer.indexOf(' ') :  tagBuffer.indexOf('>')
                );

        if(tag.charAt(0) == '/'){
            buffer.tag = tag.substring(1);
            buffer.tagType = TagType.CLOSE;
        }
        else{
            buffer.tag = tag;
            buffer.tagType = TagType.OPEN;
        }

        buffer.skip = i - j;
        buffer.innerText = innerTextBuffer.trim();
        buffer.tagBuffer = tagBuffer;

        if(Objects.equals(buffer.tag, "script")) {
            buffer.skip = i - skipJavaScript(html, j);
            buffer.tagType = TagType.OPEN;
        }

        return buffer;

    }

    @Override
    public LinkedList<Element> find(WebDriver driver){

        WebElementsPathForest forest = new WebElementsPathForest();

        CharSequence html = stripComments(driver.getPageSource());

        Stack<Integer> childCountStack = new Stack<>();
        LinkedList<Element> textElements = new LinkedList<>();
        Stack<String> innerTextStack = new Stack<>();
        boolean inBody = false;
        Stack<String> tagStack = new Stack<>();
        HashMap<String, String> tagAttributes;

        for(int i = html.length() - 1; i > -1;){

            ParseDataBuffer buffer = tryFindTag(html, i);

            if(Objects.equals(buffer.tag, "body")) {
                if(inBody){
                    break;
                }
                else
                    inBody = true;
            }

            i -= buffer.skip;

            if(isComment(buffer.tag))
                continue;

            if(!buffer.innerText.isEmpty() && !innerTextStack.isEmpty())
            innerTextStack.add(
                    innerTextStack.pop() + buffer.innerText
            );

            switch (buffer.tagType){

                case NONE:
                    break;

                case OPEN:

                    if(tagStack.peek().equals(buffer.tag)) {

                        tagStack.pop();
                        childCountStack.pop();

                        if (!innerTextStack.peek().equals("")) {
                            tagAttributes = parseAttributes(buffer.tagBuffer);
                            //noinspection unchecked
                            forest.add(new TextElement(
                                    (Stack<String>) tagStack.clone(), // FOR DEBUG,
                                    (Stack<Integer>) childCountStack.clone(),
                                    innerTextStack.pop(),
                                    tagAttributes
                            ));

                        } else
                            innerTextStack.pop();

                    }
                    if (childCountStack.size() != 0)
                        childCountStack.add(childCountStack.pop() + 1);

                    break;

                case CLOSE:

                    tagStack.add(buffer.tag);
                    childCountStack.add(0);
                    innerTextStack.add("");

            }

        }

        for(TextElement element : forest.merge()){

            if(checkElementText(element.getInnerText()))
                textElements.add( element.findElement(driver) );

        }

        return textElements;

    }

    private static class ParseDataBuffer{
        public String innerText = "";
        public String tag = "";
        public int skip;
        public TagType tagType;
        public String tagBuffer;
    }

    public enum TagType {
        NONE, OPEN, CLOSE
    }

}