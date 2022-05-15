/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import java.sql.Array;

/**
 *
 * @author Dilara
 */
public class Position implements Component {

    private float x, y;
    private float radians;

    public Position(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;
    }
    

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    @Override
    public void update(Entity entity, GameData gameData, World world) {
    }

}