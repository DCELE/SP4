/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.weapon;

import group14.common.game.GameData;
import static group14.common.game.Input.SPACE;
import group14.common.game.World;
import group14.common.gameobjects.Enemy;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.Weapon;
import group14.common.gameobjects.components.Collider;
import group14.common.gameobjects.components.Health;
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

public class WeaponController implements IUpdate {

    float coolDown = 0.2f;
    float timer = 0;
    float weaponDamage = 1;

    @Override
    public void update(GameData gameData, World world) {

        if (timer > 0) {
            timer -= gameData.getDeltaTime();
        }
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getInput().isDown(SPACE) && timer <= 0) {
                timer = coolDown;
                Position position = player.getComponent(Position.class);
                Weapon weapon = new Weapon("weapon.png", position.getX(), position.getY(), 300, position.getRadians());
                float heigth = 3 * 3;
                float width = 3 * 3;
                Collider weaponCollider = new Collider(heigth, width);
                weapon.addComponent(weaponCollider);
                world.addEntity(weapon);
            }
        }
        for (Entity weapon : world.getEntities(Weapon.class)) {
            Weapon currentWeapon = (Weapon) weapon;
            currentWeapon.updateWeapon(gameData);
            Position weaponPosition = weapon.getComponent(Position.class);
            for (Entity entity : world.getEntities()) {
                Position entityPosition = entity.getComponent(Position.class);
                if (!entity.hasComponent(Collider.class) || entity.getClass().equals(Player.class)) {
                    continue;
                }
                Collider entityCollider = entity.getComponent(Collider.class);
                boolean hit = entityCollider.checkPointCollider(weaponPosition.getX(), weaponPosition.getY(), entityPosition.getX(), entityPosition.getY());
                if (hit == true) {
                    if (entity.hasComponent(Health.class)) {
                        Health entityHealth = entity.getComponent(Health.class);
                        entityHealth.damage(weaponDamage);
                        if (entityHealth.isDeath() == true) {
                            world.removeEntity(entity);
                        }
                    }
                    world.removeEntity(weapon);
                }
            }
            
        }
    }

}
