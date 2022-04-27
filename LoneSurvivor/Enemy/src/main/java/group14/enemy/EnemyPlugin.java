package group14.enemy;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Enemy;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Collider;
import group14.common.gameobjects.components.Health;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class EnemyPlugin implements IPlugin{

    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = createEnemy(gameData);
        world.addEntity(enemy);
   
        System.out.println("enemy random");
    }

    @Override
    public void stop(GameData gameData, World world) {
    
    }

    private Entity createEnemy(GameData gameData) {
        
        //float deacceleration = 10;
        float acceleration = 500;
        float maxSpeed = 300;
        //float rotationSpeed = 5;
        float radius = 8;
        float health = 1;
        float heigth = 16 * 3;
        float width = 11 * 3;
        
        Entity enemy = new Enemy("enemy.png");
        Position enemyPosition = new Position(gameData.getSceneWidth()/3, gameData.getSceneHeight()/3, 3.1415f / 2);
        Health enemyHealth = new Health(health);
        Collider enemyCollider = new Collider(heigth, width);
        
        enemy.addComponent(enemyPosition);
        enemy.addComponent(enemyHealth);
        enemy.addComponent(enemyCollider);
        return enemy;
    }

   
    
}