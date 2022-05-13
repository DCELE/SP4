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

//    private Entity createEnemy(GameData gameData, Entity target, float x, float y) {
//
//        //float deacceleration = 10;
//        float acceleration = 50;
//        //float rotationSpeed = 5;
//        float radius = 8;
//        float health = 1;
//        float heigth = 16 * 3;
//        float width = 11 * 3;
//
//        Entity enemy = new Enemy("enemy.png");
//        Position enemyPosition = new Position(x, y, 3.1415f / 2);
//        Health enemyHealth = new Health(health);
//        Collider enemyCollider = new Collider(heigth, width);
//        AIBrain enemyAI = new AIBrain();
//        enemyAI.setTarget(target);
//        Movement enemyMovement = new Movement(acceleration);
//        MapCollider enemyMapCollider = new MapCollider();
//        EnemyPunch enemyPunch = new EnemyPunch();
//        Animator enemyAnimator = new Animator("idle", enemy);
//        enemyAnimator.addAnimation("idle", new AnimationFrame[]{new AnimationFrame("enemy.png", 1)});
//        enemyAnimator.addAnimation("eating", new AnimationFrame[]{new AnimationFrame("enemy_open_mouth.png", 1)});
//        
//        enemy.addComponent(enemyPosition);
//        enemy.addComponent(enemyHealth);
//        enemy.addComponent(enemyCollider);
//        enemy.addComponent(enemyAI);
//        enemy.addComponent(enemyMovement);
//        enemy.addComponent(enemyMapCollider);
//        enemy.addComponent(enemyPunch);
//        enemy.addComponent(enemyAnimator);
//        return enemy;
//    }
    @Override
    public void start(GameData gameData, World world) {
        
        // Finding the player and creating an enemy and setting its target to be the player
//        List<Entity> players = world.getEntities(Player.class);
//        Entity player = null;
//        if (!players.isEmpty()) {
//            player = players.get(0);
//        }
//
//        Entity enemy = createEnemy(gameData, player, 200, 200);
//        world.addEntity(enemy);

    }

    @Override
    public void stop(GameData gameData, World world) {
        
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }

    
}
