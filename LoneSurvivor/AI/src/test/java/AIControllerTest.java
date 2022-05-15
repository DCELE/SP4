/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.components.AIBrain;
import group14.common.gameobjects.components.Position;
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



public class AIControllerTest {
    
    
    public AIControllerTest() {
    }
    
    @Test
    public void AITargetPlayer(){
        
       Player player = new Player();
       
       Entity entity = new Entity();
        
       Position expectedResult = player.getComponent(Position.class);
       
       AIBrain entityAI = entity.getComponent(AIBrain.class);
       
       Position result =  entityAI.getTargetPosition();
        
       assertEquals(expectedResult, result);
       
       fail("Did not find target");
   
    }
    
   

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
