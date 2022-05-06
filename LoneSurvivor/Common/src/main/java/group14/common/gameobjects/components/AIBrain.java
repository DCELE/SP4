/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Node;

/**
 *
 * @author Dilara
 */
public class AIBrain implements Component {

    private Node node;
    private Position target;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    
    @Override
    public void update(Entity entity, GameData gameData) {
        if (node == null || target == null) {
            return;
        }
        Position tilePosition = node.getTile().getComponent(Position.class);
        Movement entityMovement = entity.getComponent(Movement.class);
        Position entityPosition = entity.getComponent(Position.class);

        // Calculate which direction the AI looks so it looks at its target
        float x = tilePosition.getX() - entityPosition.getX();
        float y = tilePosition.getY() - entityPosition.getY();
        entityPosition.setRadians((float) Math.atan2(y, x));
   

        // Block the enemy from going beyond the scene
        float dx = entityPosition.getX() + (float) Math.cos(entityPosition.getRadians()) * entityMovement.getSpeed() * gameData.getDeltaTime();
        float dy = entityPosition.getY() + (float) Math.sin(entityPosition.getRadians()) * entityMovement.getSpeed() * gameData.getDeltaTime();
        if (dx > gameData.getSceneWidth() || dx < 0) {
            return;
        }
        if (dy > gameData.getSceneHeight() || dy < 0) {
            return;
        }
        
        // AI goes to the direction it looks
        entityPosition.setX(entityPosition.getX() + (float) Math.cos(entityPosition.getRadians()) * entityMovement.getSpeed() * gameData.getDeltaTime());
        entityPosition.setY(entityPosition.getY() + (float) Math.sin(entityPosition.getRadians()) * entityMovement.getSpeed() * gameData.getDeltaTime());

        
    }

}
