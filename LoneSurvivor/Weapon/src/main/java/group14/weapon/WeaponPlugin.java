/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.weapon;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.PointManager;
import group14.common.gameobjects.Weapon;
import group14.common.services.IPlugin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */

@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class WeaponPlugin implements IPlugin{

    
    @Override
    public void start(GameData gameData, World world) {
        PointManager pointManager = new PointManager();
        world.addEntity(pointManager);
    }

    @Override
    public void stop(GameData gameData, World world) {
    
        for (Entity weapon : world.getEntities(Weapon.class)) {
            world.removeEntity(weapon);
        }
        
        for (Entity pointManager : world.getEntities(PointManager.class)) {
            world.removeEntity(pointManager);
        }
        
    }
    
}
