/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.ai;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPathfinder;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author frederikkelan
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPathfinder.class),})

public class AIPathfinder implements IPathfinder{
    
    

    @Override
    public Position findNextPosition(Entity e, GameData gameData, World world) {
    }
    
}
