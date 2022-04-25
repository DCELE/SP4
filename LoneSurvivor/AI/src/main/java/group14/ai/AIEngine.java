/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.ai;

import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Position;
import group14.common.gameobjects.components.Sight;
import group14.common.services.ICollisionChecker;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author frederikkelan
 */
public class AIEngine {
    
    private ICollisionChecker collisionChecker;
    private final int weight = 5;
    private Position lastSeen;
    
    
    public AIEngine(ICollisionChecker collisionChecker){
        this.collisionChecker = collisionChecker;
    }
    
    public void setCollisionEngine(ICollisionChecker collisionChecker){
        this.collisionChecker = collisionChecker;
    } 
    
    public Position search(World world, Entity e, Position goalState){
        HashMap<String, Node> fringe = new HashMap<>();
        HashMap<String, Node> visited = new HashMap<>();
        
        Position initialState = e.getComponent(Position.class);
        Sight sight = e.getComponent(Sight.class);
        float sightLimit = sight.getSightLimit();
        
        Node initialNode = new Node(initialState);
        
        if(h(initialNode, goalState) > sightLimit){
            if(this.lastSeen != null){
                goalState = this.lastSeen;
                if(initialNode.isState(this.lastSeen)){
                    this.lastSeen = null;
                    return null;
                }
            } else {
                return null;
            }  
        } else {
            this.lastSeen = new Position(goalState.getX(), goalState.getY());
        }
        
        fringe.put(initialNode.key(), initialNode);
        while(!fringe.isEmpty()){
            Node node = getCheapestNode(fringe);
            fringe.remove(node.key());
            visited.put(node.key(), node);
            
            if(node.isState(goalState)){
                return node.nextMove();
            }
            
            HashMap<String, Node> children = expandNode(world, e, node, goalState, fringe, visited);
            fringe.putAll(children);
        }
        return null; 
    }
    
    private HashMap<String, Node> expandNode(World world, Entity e, Node node, Position goalState,
            HashMap<String, Node> fringe, HashMap<String, Node> visited){
        HashMap<String, Node> successors = new HashMap<>();
        ArrayList<Position> children = getChildren(world, e, node.state);
        
        for(Position child : children){
            Node n = new Node(child);
            if(visited.containsKey(n.key())){
                continue;
            }
            
            n.parent = node;
            n.depth = node.depth + 1;
            n.cost = node.cost + 1;
            n.f = f(n, goalState);
            
            if(fringe.containsKey(n.key()) && n.cost > fringe.get(n.key()).cost){
                continue;
            }
            
            successors.put(n.key(), n);
        }
        return successors;
    }
    
    private ArrayList<Position> getChildren(World world, Entity e, Position state){
        float x = state.getX();
        float y = state.getY();
        ArrayList<Position> children = new ArrayList<>();
        
        addIfValid(world, e, children, x, y + 1);
        addIfValid(world, e, children, x, y - 1);
        addIfValid(world, e, children, x + 1, y);
        addIfValid(world, e, children, x - 1, y);
        addIfValid(world, e, children, x + 1, y + 1);
        addIfValid(world, e, children, x - 1, y + 1);
        addIfValid(world, e, children, x + 1, y - 1);
        addIfValid(world, e, children, x - 1, y - 1);
        
        return children;
    }
    
    private void addIfValid(World world, Entity e, ArrayList<Position> children, float x, float y){
        if(this.collisionChecker == null){
            children.add(new Position(x, y));
        } else if (this.collisionChecker.isPositionFree(world, e, x, y)){
            children.add(new Position(x, y));
        }
    }
    
    private Node getCheapestNode(HashMap<String, Node> fringe) {
        Node cheapest = null;
        for(Node k : fringe.values()){
            if(cheapest == null){
                cheapest = k;
            } else if(cheapest.f > k.f){
                cheapest = k;
            }
        }
        return cheapest;
    }
    
    private double f(Node k, Position targetPosition){
        return g(k) + weight*h(k, targetPosition);
    }
    
    private double g(Node k){
        return k.cost;
    }
    
    private double h(Node k, Position targetPosition){
        float dx = k.state.getX() - targetPosition.getX();
        float dy = k.state.getY() - targetPosition.getY();
        
        double distance = Math.sqrt(dx*dx+dy*dy);
        return distance;
    }
    
    
}
