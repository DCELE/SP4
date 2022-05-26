/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.player;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.Weapon;
import group14.common.gameobjects.components.AnimationFrame;
import group14.common.gameobjects.components.Animator;
import group14.common.gameobjects.components.Collider;
import group14.common.gameobjects.components.Health;
import group14.common.gameobjects.components.MapCollider;
import group14.common.gameobjects.components.Movement;
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
        // creating player at start and adding to world
    Entity player = createPlayer(gameData);
    world.addEntity(player);
    
    }

    @Override
    public void stop(GameData gameData, World world) {
        
        // removing player from world at stop to make sure it is gone when module is uninstalled
        for (Entity player : world.getEntities(Player.class)) {
            world.removeEntity(player);
        }
    
    }
    
    private Entity createPlayer(GameData gameData) {
        
        float speed = 300;
        float health = 1000;
        float heigth = 13 * 3;
        float width = 12 * 3;
        
        Entity player = new Player("player.png");
        Position playerPosition = new Position(gameData.getSceneWidth()/2, gameData.getSceneHeight()/2, 3.1415f / 2);
        
        Movement playerMovement = new Movement(speed);
        Health playerHealth = new Health(health);
        Collider playerCollider = new Collider(heigth, width);
        MapCollider playerMapCollider = new MapCollider();
        Animator playerAnimator = new Animator("idle", player);
        playerAnimator.addAnimation("idle", new AnimationFrame[]{new AnimationFrame("player.png", 1)});
        playerAnimator.addAnimation("shooting", new AnimationFrame[]{new AnimationFrame("player_open_mouth.png", 1)});
        
        player.addComponent(playerPosition);
        player.addComponent(playerMovement);
        player.addComponent(playerHealth);
        player.addComponent(playerCollider);
        player.addComponent(playerMapCollider);
        player.addComponent(playerAnimator);
        return player;
    } 
}
