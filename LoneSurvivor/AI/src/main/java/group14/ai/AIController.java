/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.ai;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.services.IUpdate;

/**
 *
 * @author Dilara
 */
public class AIController implements IUpdate{
    

    @Override
    public void update(GameData gameData, World world) {
        for (Entity entity : world.getEntities()){
            
        }
        
    }
    
}
