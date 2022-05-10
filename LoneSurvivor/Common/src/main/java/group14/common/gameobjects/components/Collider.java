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
public class Collider implements Component {

    float height;
    float width;

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
    
    public Collider (float height, float width) {
        this.height = height;
        this.width = width;
    }
    
    public boolean checkPointCollider(float pointX, float pointY, float positionX, float positionY) {

        float x1 = positionX - this.width / 2;
        float x2 = positionX + this.width / 2;
        float y1 = positionY - this.height / 2;
        float y2 = positionY + this.height / 2;

        if (pointX > x1 && pointX < x2 && pointY > y1 && pointY < y2) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void update(Entity entity, GameData gameData, World world) {
    
    }
    
}
