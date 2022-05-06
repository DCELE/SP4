/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import static group14.common.game.Input.DOWN;
import static group14.common.game.Input.LEFT;
import static group14.common.game.Input.RIGHT;
import static group14.common.game.Input.UP;
import group14.common.gameobjects.Entity;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author Dilara
 */
public class Movement implements Component{

    
    private float dx, dy;
    private float speed;
    private boolean left, right, up, down;
    
    
    public Movement( float speed) {
        this.speed = speed;
    }
    
    public float getDx() {
        return dx;
    }
    
    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }
    
    public void setDy(float dy) {
        this.dy = dy;
    }


    
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
    
    public void setDown(boolean down) {
        this.down = down;
    }
    
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }
    
    
    @Override
    public void update(Entity entity, GameData gameData) {
        Position position = entity.getComponent(Position.class);
        float x = position.getX();
        float y = position.getY();
        float dt = gameData.getDeltaTime();

       
        // If pressing up or down, the player will move on the y axis           
        if (up) {
            dy += speed * dt;
        } else if (down) {
            dy -= speed * dt;
        }
        
        // If pressing left or right, the player will move on the x axis
        if (right) {
            dx += speed * dt;
        } else if (left) {
            dx -= speed * dt;
        }

        // Blocks if player goes beyond scene
        if (x + dx > gameData.getSceneWidth() || x + dx < 0) {
            dx = 0;
            dy = 0;
            return;
        }
        if (y + dy > gameData.getSceneHeight() || y + dy < 0) {
            dx = 0;
            dy = 0;
            return;
        }
        // set position
        x += dx;
        y += dy;
        

        position.setX(x);
        position.setY(y);

        float rotationY = (float)Math.sin(position.getRadians());
        float rotationX = (float)Math.cos(position.getRadians());
            
        if (up || down){
            rotationY = dy;
        }
        
        if (left || right){
            rotationX = dx;
        } 
        
        position.setRadians((float) Math.atan2(rotationY, rotationX));
            
        dx = 0;
        dy = 0;
        
    }
    
    
    
    
    
}
