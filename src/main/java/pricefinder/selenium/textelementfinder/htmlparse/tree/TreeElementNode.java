package pricefinder.selenium.textelementfinder.htmlparse.tree;

import pricefinder.selenium.textelementfinder.htmlparse.TextElement;

import java.util.LinkedList;

public class TreeElementNode {

    private TextElement element;
    private LinkedList<TreeElementNode> childs = new LinkedList<>();

    public TreeElementNode(TextElement element){
        this.element = element;
    }

    public void addChild(TreeElementNode child){
        this.childs.add(child);
    }

    public LinkedList<TreeElementNode> getChilds(){
        return childs;
    }

    public TextElement getElement() {
        return element;
    }

    public TextElement merge(){

        TextElement mergedElement =
                new TextElement(
                        element.getTagStack(),
                        element.getChildCountStack(),
                        element.getInnerText(),
                        element.getAttributes()
                );

        for(TreeElementNode node : childs)
            mergedElement = mergedElement.merge(node.merge());

        return mergedElement;

    }

}
