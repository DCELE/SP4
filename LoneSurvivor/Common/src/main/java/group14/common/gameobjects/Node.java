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
