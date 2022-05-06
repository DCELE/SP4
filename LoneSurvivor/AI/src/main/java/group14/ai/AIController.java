/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.ai;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Enemy;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Node;
import group14.common.gameobjects.Room;
import group14.common.gameobjects.Tile;
import group14.common.gameobjects.components.AIBrain;
import group14.common.gameobjects.components.Position;
import group14.common.services.IUpdate;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})

public class AIController implements IUpdate{
    
    public void AStar(Entity entity, Room room){
        
       // Get AIBrain and check if it has a target
       AIBrain entityAI = entity.getComponent(AIBrain.class);
       if (entityAI.getTarget() == null) {
           return;
       }
       
       // Get Enemy and Target positions and get room tiles based on those positions
       Position enemyPosition = entity.getComponent(Position.class);
       Position targetPosition = entityAI.getTarget();
       Tile enemyTile = room.getTile((int) enemyPosition.getX(), (int) enemyPosition.getY());
       Tile targetTile = room.getTile((int) targetPosition.getX(), (int) targetPosition.getY());
       if (enemyTile == null || targetTile == null) {
           return;
       }
       
       ArrayList<Node> fringe = new ArrayList<Node>(); // list of all the nodes that will be checked
       ArrayList<Tile> rejected = new ArrayList<Tile>(); // can contain tiles that have obstacles
       
       // Current node is the tile the Enemy is on and the goal is the tile the player is on
       Node currentNode = new Node(enemyTile);
       Node goal = new Node(targetTile);
        //System.out.println("start " + enemyTile.getRoomX() + " , " + enemyTile.getRoomY());
        //System.out.println("goal " + targetTile.getRoomX() + " , " + targetTile.getRoomY());
       
       // Add the current node to the fringe
       fringe.add(currentNode);
       
       // When enemy calculates path to target
       while (!fringe.isEmpty()) {
           currentNode = lowestNode(fringe, goal);
           //System.out.println("checking node " + currentNode.getTile().getRoomX() + " , " + currentNode.getTile().getRoomY());
           //System.out.println(fringe.contains(currentNode));
           fringe.remove(currentNode);
           rejected.add(currentNode.getTile());
           if (currentNode.getTile() == goal.getTile()){
               //System.out.println("       found goal");
               ArrayList<Node> path = currentNode.getPath();
               //System.out.println("       " + path.size());
               if (path.size() > 1) {
                   //System.out.println("         getting at index 1 " + path.get(1).getTile().getRoomX() + " , " + path.get(1).getTile().getRoomY());
                   entityAI.setNode(path.get(1));
               } else {
                   //System.out.println("         getting at index 0 " + path.get(0).getTile().getRoomX() + " , " + path.get(0).getTile().getRoomY());
                   entityAI.setNode(path.get(0));
               }
               return;
           }
           
           Tile tile = (Tile) currentNode.getTile();
           int[][] successors = {{tile.getRoomX() - 1, tile.getRoomY() + 1}, {tile.getRoomX(), tile.getRoomY() + 1}, 
               {tile.getRoomX() + 1, tile.getRoomY() + 1}, {tile.getRoomX() - 1, tile.getRoomY()}, 
               {tile.getRoomX() + 1, tile.getRoomY()}, {tile.getRoomX() - 1, tile.getRoomY() - 1}, 
               {tile.getRoomX(), tile.getRoomY() - 1}, {tile.getRoomX() + 1, tile.getRoomY() - 1}};
           // System.out.println("     adding neighbours ");
           for (int[] neighbour : successors) {
               if (room.getRoomTile(neighbour[0], neighbour[1]) == null || !rejected.contains(room.getRoomTile(neighbour[0], neighbour[1]))) {
                   Node node = new Node(room.getRoomTile(neighbour[0], neighbour[1]));
                   node.setParent(currentNode);
                   fringe.add(node);
                   // System.out.println("         adding node " + node.getTile().getRoomX() + " , " + node.getTile().getRoomY());
               } else {
                   //System.out.println("         tile already checked " + neighbour[0] + " , " + neighbour[1]);
               }
           }
       }
    }

    public float heuristics(Node node, Node goal) {
        Position nodePostition = node.getTile().getComponent(Position.class);
        Position goalPosition = goal.getTile().getComponent(Position.class);
        
        float x1 = nodePostition.getX();
        float y1 = nodePostition.getY();
        float x2 = goalPosition.getX();
        float y2 = goalPosition.getY();
        float distance = (float) Math.sqrt(Math.pow((double) (x2 - x1), 2) + Math.pow((double) (y2 - y1), 2));
        return distance;
    }
    
    public float evaluation(Node node, Node goal) {
        return node.getPath().size() + heuristics(node, goal);
    }
    
    public Node lowestNode(ArrayList<Node> fringe, Node goal) {
        Node lowestNode = fringe.get(0);
        //System.out.println("getting lowest node (fringe size) " + fringe.size());
        //System.out.println("        first in fringe " + lowestNode.getTile().getRoomX() + " , " + lowestNode.getTile().getRoomY());
        float evaluationValue = evaluation(lowestNode, goal);
        //System.out.println("        first in fringe evaluation value " + evaluationValue);
        for (Node node : fringe) {
            //System.out.println("                comparing to " + node.getTile().getRoomX() + " , " + node.getTile().getRoomY());
            float newEvaluationValue = evaluation(node, goal);
            //System.out.println("                comparing to evaluation value " + newEvaluationValue);
            if (newEvaluationValue < evaluationValue) {
                //System.out.println("                replacing lowest node");
                evaluationValue = newEvaluationValue;
                lowestNode = node;
            }
        }
        //System.out.println("     returning " + lowestNode.getTile().getRoomX() + " , " + lowestNode.getTile().getRoomY());
        return lowestNode;
    }
    
    @Override
    public void update(GameData gameData, World world) {
        List<Entity> rooms = world.getEntities(Room.class);
        if(rooms.isEmpty()){
        return;
        }
        
        Room room = (Room) rooms.get(0);
        
        for (Entity entity : world.getEntities(Enemy.class)){
            AStar(entity,room);
        }
        
    }
    
}
