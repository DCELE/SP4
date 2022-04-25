/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.ai;

import group14.common.gameobjects.components.Position;
import java.util.ArrayList;

/**
 *
 * @author frederikkelan
 */
public class Node {
    
    public Position state;
    public Node parent;
    public int depth;
    public int cost;
    public double f;
    
    public Node(Position state){
        this.state = state;
    }
    
    public String key(){
        return state.getX() + ":" + state.getY();
    }
    
    public boolean isState(Position otherPosition){
        Position playerPosition = this.state;
        return playerPosition.getX() == otherPosition.getX() && playerPosition.getY() == otherPosition.getY();
        
    }
    
    public Position nextMove(){
        ArrayList<Node> path = path();
        if(path.size() > 1){
            path.remove(path.size() - 1);
        }
        return path.get(path.size() - 1).state;
    }
    
    private ArrayList<Node> path(){
        ArrayList<Node> path = new ArrayList<>();
        path.add(this);
        
        Node currentNode = this;
        while(currentNode.parent != null){
            currentNode = currentNode.parent;
            path.add(currentNode);
        }
        return path;
    }
    
    
}
