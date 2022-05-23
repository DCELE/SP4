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

    // The position of the player is in the center so if it goes to the border
    // of the screen then it will continue all the way until the center of the player
    // touches the border of the screen. This means that half of the player will be 
    // outside of the screen. To avoid this, we will need half a tile.
    float halfTile = 16 * 3 / 2;
    @Override
    public void update(Entity entity, GameData gameData, World world) {
        Position entityPosition = entity.getComponent(Position.class);
        
        // Checking if the player is at the borders and then minus/plus half a tile if it is
        // minus/plus according to what side of the map it is
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
