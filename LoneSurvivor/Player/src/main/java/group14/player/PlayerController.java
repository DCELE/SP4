/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.player;

import group14.common.game.GameData;
import static group14.common.game.Input.DOWN;
import static group14.common.game.Input.LEFT;
import static group14.common.game.Input.RIGHT;
import static group14.common.game.Input.SPACE;
import static group14.common.game.Input.UP;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.Weapon;
import group14.common.gameobjects.components.Movement;
import group14.common.gameobjects.components.Position;
import group14.common.services.IUpdate;
import group14.common.services.WeaponSPI;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;



/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class), 
    @ServiceProvider(service = WeaponSPI.class) })

public class PlayerController implements IUpdate, WeaponSPI {

    @Override
    public void update(GameData gameData, World world) {
        //System.out.println(world.getEntities(Player.class).size());
        for (Entity player : world.getEntities(Player.class)) {
            Position position = player.getComponent(Position.class);
            Movement movement = player.getComponent(Movement.class);
//            Weapon weapon = player.getComponent(Weapon.class);
            //LifePart lifePart = player.getPart(LifePart.class);

            movement.setLeft(gameData.getInput().isDown(LEFT));
            movement.setRight(gameData.getInput().isDown(RIGHT));
            movement.setUp(gameData.getInput().isDown(UP));
            movement.setDown(gameData.getInput().isDown(DOWN));

            if (gameData.getInput().isDown(SPACE)) {
                
                Entity newWeapon = Lookup.getDefault().lookup(WeaponSPI.class).createWeapon(player, gameData);
                world.addEntity(newWeapon);
            }

            movement.update(player, gameData);
            position.update(player, gameData);
            //lifePart.process(gameData, player);

            //updateShape(player);

        }
    }

    @Override
    public Entity createWeapon(Entity e, GameData gameData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
