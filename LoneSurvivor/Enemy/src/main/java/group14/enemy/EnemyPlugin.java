package group14.enemy;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
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
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private Entity createEnemy(GameData gameData) {
        
        //float deacceleration = 10;
        float acceleration = 500;
        float maxSpeed = 300;
        //float rotationSpeed = 5;
        float radius = 8;
        
        
        Entity enemy = new Entity("player.png");
        Position playerPosition = new Position(gameData.getSceneWidth()/3, gameData.getSceneHeight()/3, 3.1415f / 3);
        
        
        
        
        enemy.addComponent(playerPosition);
        return enemy;
    }

   
    
}