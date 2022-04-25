/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.services;

import group14.common.game.GameData;
import group14.common.gameobjects.Entity;

/**
 *
 * @author Chris
 */
public interface WeaponSPI {
    
    Entity createWeapon(Entity e, GameData gameData);
    
}
