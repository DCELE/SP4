/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.weapon;

import group14.common.game.GameData;
import static group14.common.game.Input.SPACE;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.Weapon;
import group14.common.gameobjects.components.Position;
import group14.common.services.IUpdate;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})

public class WeaponController implements IUpdate{
    
    @Override
    public void update(GameData gameData, World world) {
    
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getInput().isPressed(SPACE)){
                Position position = player.getComponent(Position.class);
                Weapon weapon = new Weapon("weapon.png", position.getX(), position.getY(), 300, position.getRadians());
                world.addEntity(weapon);
                System.out.println("weapon test");
            }
            System.out.println("weapon for test");
        }
        for (Entity weapon : world.getEntities(Weapon.class)) {
            Weapon currentWeapon = (Weapon)weapon;
            currentWeapon.updateWeapon(gameData);
            
        }
    }
    
}
