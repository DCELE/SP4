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
    
    float coolDown = 0.5f;
    float timer = 0;
    
    @Override
    public void update(GameData gameData, World world) {
    
        if (timer > 0){
            timer -= gameData.getDeltaTime();
        }
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getInput().isDown(SPACE) && timer <=0){
                timer = coolDown;
                Position position = player.getComponent(Position.class);
                Weapon weapon = new Weapon("weapon.png", position.getX(), position.getY(), 300, position.getRadians());
                world.addEntity(weapon);
            }
        }
        for (Entity weapon : world.getEntities(Weapon.class)) {
            Weapon currentWeapon = (Weapon)weapon;
            currentWeapon.updateWeapon(gameData);
            
        }
    }
    
}
