/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.services;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;

/**
 *
 * @author frederikkelan
 */
public interface ICollisionChecker {
    boolean isPositionFree(World world, Entity e, float x, float y);
    boolean isInRoom(World world, Entity me, Entity room);
    void leavingRoom(GameData gameData, World world, Entity me, float newX, float newY);
    
}
