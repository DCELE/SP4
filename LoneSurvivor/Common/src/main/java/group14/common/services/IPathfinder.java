/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.services;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Position;

/**
 *
 * @author frederikkelan
 */
public interface IPathfinder {
    Position findNextPosition(Entity e, GameData gameData, World world);
    
}
