/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.weapon;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Weapon;
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
    @ServiceProvider(service = IPlugin.class)})

public class WeaponPlugin implements IPlugin {
    
    private Entity weapon;

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Weapon.class) {
                world.removeEntity(e);
            }
        }
    }
    
     private Entity createWeapon(GameData gameData) {
        //float deacceleration = 10;
        float speed = 300;
        //float rotationSpeed = 5;
        float radius = 8;
        
        
        Entity weapon = new Weapon("weapon.png");
//        Position playerPosition = new Position(gameData.getSceneWidth()/2, gameData.getSceneHeight()/2, 3.1415f / 2);
//        
//        Movement playerMovement = new Movement(speed);
//        
//        
//        
//        weapon.addComponent(playerPosition);
//        player.addComponent(playerMovement);
        return weapon;
    } 
    
}
