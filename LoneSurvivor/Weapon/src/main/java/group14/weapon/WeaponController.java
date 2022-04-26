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
import group14.common.services.IUpdate;
import group14.common.services.WeaponSPI;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class), 
    @ServiceProvider(service = WeaponSPI.class) })

public class WeaponController implements IUpdate, WeaponSPI {

    @Override
    public void update(GameData gameData, World world) {
        for (Entity weapon : world.getEntities(Weapon.class)) {
<<<<<<< HEAD
            
            Weapon newweapon = new Weapon("weapon.png");
            
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
=======
            Weapon newWeapon = new Weapon();
//            Position position = weapon.getComponent(Position.class);
//            Movement movement = weapon.getComponent(Movement.class);
////            Timer timer = weapon.getComponent(Timer.class);
////            movement.setUp(false);
////            if (timer.getExpiration() < 0) {
////                world.removeEntity(weapon);
////            }
////            timer.update(weapon, gameData);
//            movement.update(weapon, gameData);
//            position.update(weapon, gameData);
>>>>>>> parent of c3ea9fd... weapontemp

//            setShape(weapon);
        }
    }

    @Override
<<<<<<< HEAD
    public Entity createWeapon(Entity entity, GameData gameData) {
//        //float deacceleration = 10;
//        float speed = 300;
//        //float rotationSpeed = 5;
//        float radius = 8;
=======
    public Entity createWeapon(Entity e, GameData gameData) {
        //float deacceleration = 10;
        float speed = 300;
        //float rotationSpeed = 5;
        float radius = 8;
>>>>>>> parent of c3ea9fd... weapontemp
        
        
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
    
}
