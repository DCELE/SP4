/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.player;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */

@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class PlayerPlugin implements IPlugin{

    @Override
    public void start(GameData gameData, World world) {
    Entity player = createPlayer(gameData);
    world.addEntity(player);
    
    
    
    
        System.out.println("player random");
    }

    @Override
    public void stop(GameData gameData, World world) {
    
    }
    
    private Entity createPlayer(GameData gameData) {
        Entity player = new Entity("player.png");
        Position playerPosition = new Position(gameData.getSceneWidth()/2, gameData.getSceneHeight()/2, 3.1415f / 2);
       
        player.addComponent(playerPosition);
        return player;
    } 
    
    
    
}
