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
    
    
    // Getting the parent of a node until you get to where you started
    // All of these nodes will be saved in path so we have the path from
    // the beginning all the way to that node
    
    // This method will take every node that leads up to the target and will return it as a list
    
    // If the AI has found the target then it will use this method to find the path
    public ArrayList <Node> getPath(){
        // The path of nodes will be an array
        ArrayList <Node> path = new ArrayList <Node>();
        
        // The current node is the node the target is currently standing on
        Node currentNode = this;
        
        // The current node will be added to path list
        path.add(currentNode);
        
        // While there is a parent to the node that is checked, then it will keep getting the parent
        while (currentNode.getParent() != null ) {
            currentNode = currentNode.getParent();
            
            // When we get a node that does have a parent, then we know that it is not the starting point
            // and then it will be added as the first index in the path
            path.add(0, currentNode);
        }
        // Returning the path when there are no more parents
        return path;
    }
    
    
}
