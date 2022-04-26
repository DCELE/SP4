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
import group14.common.gameobjects.components.Timer;
import group14.common.services.IEntityProcessingService;
import group14.common.services.WeaponSPI;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class), 
    @ServiceProvider(service = WeaponSPI.class) })

public class WeaponController implements IEntityProcessingService, WeaponSPI {

    private String image;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity weapon : world.getEntities(Weapon.class)) {
            
            Position position = weapon.getComponent(Position.class);
            Movement movement = weapon.getComponent(Movement.class);
            Timer timer = weapon.getComponent(Timer.class);
            movement.setUp(true);
            if (timer.getExpiration() < 0) {
                world.removeEntity(weapon);
            }
            timer.process(gameData, weapon);
            movement.process(gameData, weapon);
            position.process(gameData, weapon);

            setImage("weapon.png");

        }
    }

    @Override
    public Entity createWeapon(Entity e, GameData gameData) {
//        //float deacceleration = 10;
//        float speed = 300;
//        //float rotationSpeed = 5;
//        float radius = 8;
        
        
        Entity weapon = new Weapon("weapon.png");
        Position weaponPosition = new Position(gameData.getSceneWidth()/2, gameData.getSceneHeight()/2, 3.1415f / 2);
//        
//        Movement playerMovement = new Movement(speed);
//        
//        
//        
        weapon.addComponent(weaponPosition);
//        player.addComponent(playerMovement);
        return weapon;
    }

    private void setImage(String image) {
        this.image = image;
        
    }
    
}
        

