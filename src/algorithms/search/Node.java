package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * this class will represent a node for the searching algorithms
 * parent: the node which discovered the current node
 * visit: if =true: this node has been discovered
 * value: the AState that this node is representing
 */
public class Node {
    Node parent;
    boolean visit;
    AState value;

    //constructor
    public Node(AState value) {
        this.parent = null ;
        this.visit = false;
        this.value = value;
    }

    //getters & setters

    public void setVisit(){visit = true;}
    public boolean isVisit(){ return visit;}

    public void setParent(Node father){ parent = father;}
    public Node getParent() { return parent;}

    public AState getValue() { return value;}
    public void setValue(AState value) {this.value = value;}

}

