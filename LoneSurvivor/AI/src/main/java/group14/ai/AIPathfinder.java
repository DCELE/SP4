/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.ai;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.PlayerPart;
import group14.common.gameobjects.components.Position;
import group14.common.services.ICollisionChecker;
import group14.common.services.IPathfinder;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author frederikkelan
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPathfinder.class),})

public class AIPathfinder implements IPathfinder{
    
    private AIEngine aiEngine = new AIEngine(null);
    private RandomEngine randomEngine = new RandomEngine(null);
    
    public void addCollisionChecker(ICollisionChecker collisionChecker) {
        this.aiEngine.setCollisionEngine(collisionChecker);
        this.randomEngine.setCollisionEngine(collisionChecker);
    }
    
    public void removeCollisionChecker(ICollisionChecker collisionChecker) {
        this.aiEngine.setCollisionEngine(null);
        this.randomEngine.setCollisionEngine(null);
    }
    
    @Override
    public Position findNextPosition(Entity e, GameData gameData, World world) {
        Position nextPosition = null;
        
        Entity target = getTarget(e, world);
        if (target != null) {
            Position targetPosition = target.getComponent(Position.class);
            if (this.aiEngine != null) {
                nextPosition = this.aiEngine.search(world, e, targetPosition);
            }
        }

        if (randomEngine != null && nextPosition == null) {
            nextPosition = randomEngine.randomMove(world, e);
        }

        return nextPosition;
    }
    
    private Entity getTarget(Entity e, World world) {
        for (Entity entity : world.getEntities()) {
            PlayerPart playerPart = entity.getComponent(PlayerPart.class);
            if (entity != e && playerPart != null) {
                return entity;
            }
        }

        return null;
    }
    
}
