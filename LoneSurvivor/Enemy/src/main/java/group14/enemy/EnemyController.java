/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.enemy;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Enemy;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.LifePart;
import group14.common.gameobjects.components.Movement;
import group14.common.gameobjects.components.Position;
import group14.common.gameobjects.components.Sight;
import group14.common.services.ICollisionChecker;
import group14.common.services.IPathfinder;
import group14.common.services.IUpdate;

/**
 *
 * @author Dilara
 */
public class EnemyController implements IUpdate {

    private IPathfinder aiPathfinder;
    private ICollisionChecker collisionChecker;
    
    public void addPathfinder(IPathfinder aiPathfinder){
        this.aiPathfinder = aiPathfinder;
    }
      
    public void removePathfinder(IPathfinder aiPathfinder){
        this.aiPathfinder = null;
    }
    
    @Override
    public void update(GameData gameData, World world) {
        for(Entity enemy : world.getEntities(Enemy.class)){
            Position position = enemy.getComponent(Position.class);
            Movement movement = enemy.getComponent(Movement.class);
            Sight sight = enemy.getComponent(Sight.class);
            LifePart life = enemy.getComponent(LifePart.class);
            
            if(aiPathfinder != null){
                Position nextPosition = aiPathfinder.findNextPosition(enemy, gameData, world);
                movement.setRight(position.getX() < nextPosition.getX());
                movement.setLeft(position.getX() > nextPosition.getX());
                movement.setUp(position.getY() < nextPosition.getY());
                movement.setDown(position.getY() > nextPosition.getY());
                
                if(collisionChecker!= null){
                    collisionChecker.leavingRoom(gameData, world, enemy, nextPosition.getX(), nextPosition.getY());
                }
            }
            
            movement.update(enemy, gameData);
            position.update(enemy, gameData);
            sight.update(enemy, gameData);
            life.update(enemy, gameData);
            
            if(life.getLife() <= 0){
                world.removeEntity(enemy);
            }
            updateShape(enemy);
        }
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        Position position = entity.getComponent(Position.class);
        float x = position.getX();
        float y = position.getY();
        float radians = 3.1415f / 2;

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 10);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 10);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
        
    }
    
    public void addCollisionChecker(ICollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public void removeCollisionChecker(ICollisionChecker collisionChecker) {
        this.collisionChecker = null;
    }
    
    
}
