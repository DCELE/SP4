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
        Position positionA = entityA.getComponent(Position.class);
        Position positionB = entityB.getComponent(Position.class);
        Collider colliderA = entityA.getComponent(Collider.class);
        Collider colliderB = entityB.getComponent(Collider.class);

        float xDifference = positionA.getX() - positionB.getX();
        float yDifference = positionA.getY() - positionB.getY();

        float overlapX = colliderA.getWidth() / 2 + colliderB.getWidth() / 2 - Math.abs(xDifference);
        float overlapY = colliderA.getHeight() / 2 + colliderB.getHeight() / 2 - Math.abs(yDifference);

        if (entityA.hasComponent(Movement.class)) {
            if (overlapX >= overlapY) {
                if (yDifference > 0) {
                    positionA.setY(positionA.getY() + overlapY);
                } else {
                    positionA.setY(positionA.getY() - overlapY);

                }
            } else {
                if (xDifference > 0) {
                    positionA.setX(positionA.getX() + overlapX);
                } else {
                    positionA.setX(positionA.getX() - overlapX);
                }
            }

        }

    }
    
    private boolean collides(Entity a, Entity b) {
        if (a.equals(b)) {
            return false;
        }
        Position posA = a.getComponent(Position.class);
        Position posB = b.getComponent(Position.class);
        Collider boxA = a.getComponent(Collider.class);
        Collider boxB = b.getComponent(Collider.class);

        if (posA == null || posB == null || boxA == null || boxB == null) {
            return false;
        }

        float w = (boxA.getWidth() + boxB.getWidth()) / 2;
        float h = (boxA.getHeight() + boxB.getHeight()) / 2;
        float dx = posA.getX() - posB.getX();
        float dy = posA.getY() - posB.getY();

        return Math.abs(dx) <= w && Math.abs(dy) <= h;
    }
    
    @Override
    public void update(GameData gameData, World world) {
        for (Entity a : world.getEntities()) {
            for (Entity b : world.getEntities()) {
                if (collides(a, b)) {
                    collide(a, b);
                }
            }
        }
    }
    
}
