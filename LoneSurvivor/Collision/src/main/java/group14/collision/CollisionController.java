/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.collision;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Collider;
import group14.common.gameobjects.components.Movement;
import group14.common.gameobjects.components.Position;
import group14.common.services.IUpdate;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})

public class CollisionController implements IUpdate{

    private void collide(Entity entityA, Entity entityB) {
        
        // Get position for entity A and entity B
        Position positionA = entityA.getComponent(Position.class);
        Position positionB = entityB.getComponent(Position.class);
        
        // Get collider for entity A and entity B
        Collider colliderA = entityA.getComponent(Collider.class);
        Collider colliderB = entityB.getComponent(Collider.class);

        // calculate the distance between both entities
        float xDifference = positionA.getX() - positionB.getX();
        float yDifference = positionA.getY() - positionB.getY();

        // check if they overlap eachother based on the corner of their colliders and their distance
        float overlapX = colliderA.getWidth() / 2 + colliderB.getWidth() / 2 - Math.abs(xDifference);
        float overlapY = colliderA.getHeight() / 2 + colliderB.getHeight() / 2 - Math.abs(yDifference);

        // check if entity a can move
        if (entityA.hasComponent(Movement.class)) {
            // if the overlap at the x axis is higher or the same as the overlap on the y axis then
            if (overlapX >= overlapY) {
                // if the distance on the y axis is higher than 0 then according to where the overlap happens
                // then the position of entity A will be pushed 
                if (yDifference > 0) {
                    positionA.setY(positionA.getY() + overlapY);
                } else {
                    positionA.setY(positionA.getY() - overlapY);

                }
            } else {
                // the same check for x axis
                if (xDifference > 0) {
                    positionA.setX(positionA.getX() + overlapX);
                } else {
                    positionA.setX(positionA.getX() - overlapX);
                }
            }

        }

    }
    
    private boolean collides(Entity a, Entity b) {
        // check if two entities collides, if they are the same entity then we return
        if (a.equals(b)) {
            return false;
        }
        
        // get positions for entity A and entity B
        Position posA = a.getComponent(Position.class);
        Position posB = b.getComponent(Position.class);
        
        // get collider for entity A and entity B
        Collider boxA = a.getComponent(Collider.class);
        Collider boxB = b.getComponent(Collider.class);

        // if any of the positions are null then return
        if (posA == null || posB == null || boxA == null || boxB == null) {
            return false;
        }

        // checking if the two colliders are inside each other
        float w = (boxA.getWidth() + boxB.getWidth()) / 2;
        float h = (boxA.getHeight() + boxB.getHeight()) / 2;
        float dx = posA.getX() - posB.getX();
        float dy = posA.getY() - posB.getY();

        // return true or false depending on if they are inside eachother or not
        // they collide if it is true
        return Math.abs(dx) <= w && Math.abs(dy) <= h;
    }
    
    @Override
    public void update(GameData gameData, World world) {
        // updating every entity and check if any of them collides with each other
        for (Entity a : world.getEntities()) {
            for (Entity b : world.getEntities()) {
                if (collides(a, b)) {
                    collide(a, b);
                }
            }
        }
    }
    
}
