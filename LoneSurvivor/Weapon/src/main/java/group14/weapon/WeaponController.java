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
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class)})

public class WeaponController implements IUpdate {

    @Override
    public void update(GameData gameData, World world) {
        for (Entity weapon : world.getEntities(Weapon.class)) {

            Position position = weapon.getComponent(Position.class);
            Movement movement = weapon.getComponent(Movement.class);
            Timer timer = weapon.getComponent(Timer.class);
            movement.setUp(true);
            if (timer.getExpiration() < 0) {
                world.removeEntity(weapon);
            }
            timer.update(weapon, gameData);
            movement.update(weapon, gameData);
            position.update(weapon, gameData);

//            setShape(weapon);
        }
    }
    
}
