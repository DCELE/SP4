/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects;

import java.util.ArrayList;

/**
 *
 * @author Dilara
 */
public class Node {
    
    Node parent;
    Tile tile;
    float evaluationValue;

    public Node(String name, boolean b, org.w3c.dom.Node item, org.w3c.dom.Node parentNode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public float getEvaluationValue() {
        return evaluationValue;
    }

    public void setEvaluationValue(float evaluationValue) {
        this.evaluationValue = evaluationValue;
    }
    
    
    public Node(Tile tile){
        this.tile = tile;
    }
    
    public Node(Tile tile, Node parent){
        this.tile = tile;
        this.parent = parent;
    }
    
    
    // Getting the parent of a node until you get to where you started
    // All of these nodes will be saved in path so we have the path from
    // the beginning all the way to that node
    public ArrayList <Node> getPath(){
        ArrayList <Node> path = new ArrayList <Node>();
        Node currentNode = this;
        path.add(currentNode);
        while (currentNode.getParent() != null ) {
            currentNode = currentNode.getParent();
            path.add(0, parent);
        }
        return path;
    }
    
    
}
