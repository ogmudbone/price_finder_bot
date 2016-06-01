package pricefinder.selenium.textelementfinder.htmlparse.tree;

import pricefinder.selenium.textelementfinder.htmlparse.TextElement;

import java.util.ArrayList;
import java.util.LinkedList;

public class WebElementsPathForest {

    private LinkedList<TreeElementNode> rootNodes = new LinkedList<>();

    private void add(TreeElementNode element, LinkedList<TreeElementNode> nodes){

        for(TreeElementNode node : new LinkedList<>(nodes)){

            TextElement.ElementRelationship relationship =
                    TextElement.getRelationship(node.getElement(), element.getElement());

            if(relationship == TextElement.ElementRelationship.CHILD){
                nodes.remove(node);
                element.addChild(node);
            }

            if(relationship == TextElement.ElementRelationship.PARENT){
                add(element, node.getChilds());
                return;
            }

        }

        nodes.add(element);

    }

    public void add(TextElement element){
        add(new TreeElementNode(element), rootNodes);
    }

    public ArrayList<TextElement> merge(){

        ArrayList<TextElement> elements = new ArrayList<>(rootNodes.size());

        for(TreeElementNode node : rootNodes)
            elements.add(node.merge());

        return elements;

    }

}
