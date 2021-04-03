package algorithms.search;

import algorithms.mazeGenerators.Position;

public class Node {
    Node parent;
    boolean visit;
    AState value;

    public Node(AState value) {
        this.parent = null ;
        this.visit = false;
        this.value = value;
    }

    public void setVisit(){visit = true;}
    public void setParent(Node father){ parent = father;}
    public boolean isVisit(){ return visit;}
    public Node getParent() { return parent;}

    public AState getValue() { return value;}
    public void setValue(AState value) {this.value = value;}

}

