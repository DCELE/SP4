package group14.enemy;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Enemy;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.Weapon;
import group14.common.gameobjects.components.AIBrain;
import group14.common.gameobjects.components.AnimationFrame;
import group14.common.gameobjects.components.Animator;
import group14.common.gameobjects.components.Collider;
import group14.common.gameobjects.components.EnemyPunch;
import group14.common.gameobjects.components.Health;
import group14.common.gameobjects.components.MapCollider;
import group14.common.gameobjects.components.Movement;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;
import java.util.List;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class EnemyPlugin implements IPlugin {

    @Override
    public void start(GameData gameData, World world) {
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }

    
}
