/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;

/**
 *
 * @author Dilara
 */
public class MapCollider implements Component {

    float halfTile = 16 * 3 / 2;
    @Override
    public void update(Entity entity, GameData gameData, World world) {
        Position entityPosition = entity.getComponent(Position.class);
        
        if (entityPosition.getX() > gameData.getSceneWidth() - halfTile) {
            entityPosition.setX(gameData.getSceneWidth() - halfTile);
        }
        if (entityPosition.getX() < 0 + halfTile) {
            entityPosition.setX(0 + halfTile);
        }
        if (entityPosition.getY() > gameData.getSceneHeight() - halfTile) {
            entityPosition.setY(gameData.getSceneHeight() - halfTile);
        }
        if (entityPosition.getY() < 0 + halfTile) {
            entityPosition.setY(0 + halfTile);
        }
    }
    
}
