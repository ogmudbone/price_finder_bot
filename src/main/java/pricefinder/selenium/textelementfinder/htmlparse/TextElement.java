package pricefinder.selenium.textelementfinder.htmlparse;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import pricefinder.selenium.Element;
import pricefinder.selenium.identity.XpathIdentity;

import java.util.Objects;
import java.util.Stack;

public class TextElement {

    private Stack<String> tagStack;
    private Stack<Integer> childCountStack;
    private String innerText;

    public static ElementRelationship getRelationship(TextElement el1, TextElement el2){

        Stack<Integer> el1Stack = el1.childCountStack, el2Stack = el2.childCountStack;

        for(int i = 0; i < Math.min(el1Stack.size(), el2Stack.size()); i++)
            if(!Objects.equals(el1Stack.get(i), el2Stack.get(i)))
                return ElementRelationship.NONE;

        return el1Stack.size() > el2Stack.size() ?
                ElementRelationship.CHILD :
                ElementRelationship.PARENT;

    }

    private String buildXpath(){

        String expression = "/*[last()]";

        for (Integer aChildCountStack : childCountStack)
            expression +=
                    "/*[last()-" + String.valueOf(
                            Math.max(aChildCountStack, 0)
                    ) + ']';

        return expression;

    }

    public TextElement(Stack<String> tagStack, Stack<Integer> childCountStack, String innerText){
        this.tagStack = tagStack;
        this.childCountStack = childCountStack;
        this.innerText = innerText;
    }

    public Stack<String> getTagStack() {
        return tagStack;
    }

    public Stack<Integer> getChildCountStack() {
        return childCountStack;
    }

    public String getInnerText() {
        return innerText;
    }

    public Element findElement(SearchContext context){

        try{
            return new XpathIdentity(buildXpath()).find(context);
        }
        catch (NoSuchElementException e){
            return null;
        }

    }

    public ElementRelationship getRelationship(TextElement element){
        return getRelationship(this, element);
    }

    public TextElement merge(TextElement element){

        switch(this.getRelationship(element)){

            case CHILD:
                return new TextElement(
                        element.tagStack,
                        element.childCountStack,
                        element.innerText + innerText
                );

            case PARENT:
                return new TextElement(
                        tagStack,
                        childCountStack,
                        element.innerText + innerText
                );

        }

        return null;

    }

    public enum ElementRelationship{
        CHILD, PARENT, NONE
    }

}
