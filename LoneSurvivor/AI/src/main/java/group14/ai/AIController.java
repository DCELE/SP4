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

/**
 *
 * @author Dilara
 */
public class AIController implements IUpdate{
    
    public void AStar(Entity entity, Room room){
       AIBrain entityAI = entity.getComponent(AIBrain.class);
       if (entityAI.getTarget() == null) {
           return;
       }
       Position enemyPosition = entity.getComponent(Position.class);
       Position targetPosition = entityAI.getTarget();
       Tile enemyTile = room.getTile((int) enemyPosition.getX(), (int) enemyPosition.getY());
       Tile targetTile = room.getTile((int) targetPosition.getX(), (int) targetPosition.getY());
       if (enemyTile == null || targetTile == null) {
           return;
       }
       
       ArrayList<Node> fringe = new ArrayList<Node>();
       ArrayList<Tile> rejected = new ArrayList<Tile>(); // tiles that have obstacles
       
       Node startNode = new Node(enemyTile);
       fringe.add(startNode);
       
       while (!fringe.isEmpty()) {
           
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
