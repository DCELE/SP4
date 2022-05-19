/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.components.Movement;
import group14.common.gameobjects.components.Position;
import group14.player.PlayerController;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author muhamadsdu
 */
public class PlayerControllerTest {
    
    // Create instances of classes
    PlayerController playerController;
    Player player;
    World world;
    GameData gameData;
    
    @BeforeEach
    public void init() {
        
        // Instantiate the classes
        playerController = new PlayerController();
        player = new Player();
        world = new World();
        gameData = new GameData();
        
        // Set SceneWidth, SceneHeight and DeltaTime
        gameData.setSceneWidth(200);
        gameData.setSceneHeight(300);
        gameData.setDeltaTime(1);
        
        float speed = 300;
        float x = 100;
        float y = 100;
        
        player.addComponent(new Movement(speed));
        player.addComponent(new Position(x, y, 3.1415f / 2));
        world.addEntity(player);
    
    }
    
    @Test
    public void playerMovement(){
        
        Player player = (Player) world.getEntities(Player.class).get(0);
        Position position = player.getComponent(Position.class);
        Movement movement = player.getComponent(Movement.class);
        
        // Move the 'player' to a new position
        movement.setRight(true);
        movement.update(player, gameData, world);

        float x = position.getX();
        float y = position.getY();
        
        playerController.update(gameData, world);

        // Compare the old position with the new position
        Assert.assertNotEquals(x, (float) gameData.getSceneWidth()/2);
        Assert.assertNotEquals(y, (float) gameData.getSceneHeight()/2);
  
    }
} 

