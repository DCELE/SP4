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
import group14.common.gameobjects.Player;
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
       // Tiles are used as nodes in A Star
       // When an Enemy moves, it goes to a tile. It will go to the center of the tile.
       Position enemyPosition = entity.getComponent(Position.class);
       Position targetPosition = entityAI.getTargetPosition();
       Tile enemyTile = room.getTile((int) enemyPosition.getX(), (int) enemyPosition.getY());
       Tile targetTile = room.getTile((int) targetPosition.getX(), (int) targetPosition.getY());
       
       // Check if there is a tile to make sure the Enemy and Player are not out of the screen
       if (enemyTile == null || targetTile == null) {
           return;
       }
       
       ArrayList<Node> fringe = new ArrayList<Node>(); // list of all the nodes that will be checked
       ArrayList<Tile> rejected = new ArrayList<Tile>(); // list of all nodes that have been checked
                                                         //can contain tiles that have obstacles
       
       // Current node is the tile the Enemy is on and the goal is the tile the player is on
       Node currentNode = new Node(enemyTile);
       Node goal = new Node(targetTile);
       
       // Add the current node to the fringe
       // The fringe will be a list of tiles the Enemy will check
       fringe.add(currentNode);
       
       // When enemy calculates path to target
       // fringe contains all the nodes we want to check
       // If fringe is not empty then we want to check all the nodes
       while (!fringe.isEmpty()) {
           
           // lowestNode chooses the most relevant fringe
           // The most relevant node is the one with the lowest heuristic and shortest path
           // The current node is the node inside the fringe that is closest to the goal
           currentNode = lowestNode(fringe, goal);

           // When a node has been chosen to be checked, it will be removed from the fringe
           // because when you check the node and keep it in the fringe then it will be checked everytime
           // so to avoid checking what has already been checked, it will be removed
           fringe.remove(currentNode);
           
           // A node that has been removed from the fringe will be added to rejected so we
           // know that we don't have to check it again
           rejected.add(currentNode.getTile());
           
           // If the tile that the current node is on the same as the tile the player is on
           // then we know that the target has been found
           if (currentNode.getTile() == goal.getTile()){

               // The path with all the tiles the enemy has to go to to get to the player
               ArrayList<Node> path = currentNode.getPath();

               // First tile (index 0) will always be the tile the enemy is on
               // If the enemy and player are on the standing on the same tile then the path 
               // size would be 1 since the enemy would not need to go to other tiles to find the 
               // player.
               
               // Checking if the enemy and player are standing on the same tile
               if (path.size() > 1) {
                   entityAI.setNode(path.get(1)); // The tile the enemy has to go to to get to the player
               } else {
                   entityAI.setNode(path.get(0)); // The tile that the enemy is at
               }
               return;
           }
          
           // If we don't find the target, then we will find the neighbours
           
           // Getting the tile from the node that is currently being checked
           Tile tile = (Tile) currentNode.getTile();
           
           // Finding the neighbouring tile coordinates around the current node
           int[][] neighbours = {{tile.getRoomX() - 1, tile.getRoomY() + 1}, {tile.getRoomX(), tile.getRoomY() + 1}, 
               {tile.getRoomX() + 1, tile.getRoomY() + 1}, {tile.getRoomX() - 1, tile.getRoomY()}, 
               {tile.getRoomX() + 1, tile.getRoomY()}, {tile.getRoomX() - 1, tile.getRoomY() - 1}, 
               {tile.getRoomX(), tile.getRoomY() - 1}, {tile.getRoomX() + 1, tile.getRoomY() - 1}};
           
           // Checking every coordinate of the neighbours to see if there is a tile
           for (int[] neighbour : neighbours) {
               
               // Based on the neighbour coordinates, we will get a room tile
               // We will skip if the room tile is null since it will be outside of the game screen
               // We will skip if rejected contains tile
               if (room.getRoomTile(neighbour[0], neighbour[1]) != null && !rejected.contains(room.getRoomTile(neighbour[0], neighbour[1]))) {
                   
                   // Making a new node and adding it to the fringe
                   Node node = new Node(room.getRoomTile(neighbour[0], neighbour[1]));
                   node.setParent(currentNode);
                   fringe.add(node);
                    
               }
           }
       }
    }

    public float heuristics(Node node, Node goal) {
        // Getting the positions for the node and the goal
        Position nodePostition = node.getTile().getComponent(Position.class);
        Position goalPosition = goal.getTile().getComponent(Position.class);
        
        // Getting the coordinates
        float x1 = nodePostition.getX();
        float y1 = nodePostition.getY();
        float x2 = goalPosition.getX();
        float y2 = goalPosition.getY();
        
        // Calculating the distance between the two positions with pythagoras
        float distance = (float) Math.sqrt(Math.pow((double) (x2 - x1), 2) + Math.pow((double) (y2 - y1), 2));
        
        // The heuristics is the distance
        return distance;
    }
    
    public float evaluation(Node node, Node goal) {
        // Evaluation is the path size and the heuristics
        // The number of tiles the enemy has to go to get to the goal plus the distance
        return node.getPath().size() + heuristics(node, goal);
    }
    
    public Node lowestNode(ArrayList<Node> fringe, Node goal) {
        // Getting the first node in the fringe
        Node lowestNode = fringe.get(0);
        
        // Calculating the evaluation value on the lowest node
        float evaluationValue = evaluation(lowestNode, goal);
        
        // Checking nodes in the fringe to find the node in the fringe with the lowest evaluation value
        for (Node node : fringe) {
            
            // The new evaluation value is the evaluation value of the node that is currently being checked
            float newEvaluationValue = evaluation(node, goal);
            
            // if the evaluation value is higher than the new evaluation value, then the evaluation value
            // will be assigned the new evaluation value and the lowest node will be node that is currently
            // being checked.
            if (newEvaluationValue < evaluationValue) {
                evaluationValue = newEvaluationValue;
                lowestNode = node;
            }
        }
        // Getting the lowest node so we don't have to look at every node.
        return lowestNode;
    }
    
    @Override
    public void update(GameData gameData, World world) {
        
        // Getting every room
        List<Entity> rooms = world.getEntities(Room.class);
        
        // If there are no rooms then we will jump out of this method and not run A Star
        if(rooms.isEmpty()){
        return;
        }
        
        // If there is a room then we will get the first one
        Room room = (Room) rooms.get(0);
        
        // Getting all enemies and then calculate A Star
        for (Entity entity : world.getEntities(Enemy.class)){
            AStar(entity,room);
        }
        
    }
    
}
