/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.badlogic.gdx.Gdx;
import group14.common.game.GameData;
import static group14.common.game.Input.DOWN;
import static group14.common.game.Input.LEFT;
import static group14.common.game.Input.RIGHT;
import static group14.common.game.Input.UP;
import group14.common.game.World;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.components.Movement;
import group14.common.gameobjects.components.Position;
import group14.player.PlayerController;
import java.awt.event.KeyEvent;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author muhamadsdu
 */
public class PlayerControllerTest {
    
    PlayerController playerController;
    Player player;
    World world;
    GameData gameData;
    
    @BeforeEach
    public void init() {
        
        playerController = new PlayerController();
        player = new Player();
        world = new World();
        gameData = new GameData();
        
        
        gameData.getInput().isDown(0);
        gameData.setSceneWidth(200);
        gameData.setSceneHeight(300);
        gameData.setDeltaTime(1);
        
        float speed = 300;
        //float rotationSpeed = 5;
        float radius = 8;
        
        float heigth = 13 * 3;
        float width = 12 * 3;
        float x = 100;
        float y = 100;
        
        player.addComponent(new Movement(speed));
        player.addComponent(new Position(x,y, 3.1415f / 2));
   
        world.addEntity(player);
    
    }
    
    @Test
    public void playerMovement(){
        
        Player player = (Player) world.getEntities(Player.class).get(0);
        Position position = player.getComponent(Position.class);
        Movement movement = player.getComponent(Movement.class);
        
        float x = position.getX();
        float y = position.getY();
        
        //playerController.update(gameData, world);
        
        position = player.getComponent(Position.class);
        
        Assert.assertNotEquals(x, (float) gameData.getSceneWidth()/2);
        Assert.assertNotEquals(y, (float) gameData.getSceneHeight()/2);
       
        
//        movement.setUp(gameData.getInput().isDown(UP));
        
//        for (int i = 0; i < 1000; i++) {
//            movement.setUp(true); 
//        }
        
//        movement.setUp(true);
        
        
//        for (int i = 0; i < 10; i++) {
//            movement.KeyPress(KeyEvent.VK_UP);
//            
//        }
//        
//        if (gameData.getDeltaTime() != 1000) {
//            movement.setUp(true);
//            gameData.setDeltaTime(gameData.getDeltaTime() + 1);
//            System.out.println("test if");
//        }
//        
        


        
        

//        System.out.println(x);
//        System.out.println((float)gameData.getSceneWidth()/2);
        
        
        
        
        
        
    }
    
   
} 
    
    
   
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

