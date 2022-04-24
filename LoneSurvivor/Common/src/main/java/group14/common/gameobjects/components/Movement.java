/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import group14.common.gameobjects.Entity;
import static java.lang.Math.sqrt;

/**
 *
 * @author Dilara
 */
public class Movement implements Component{

    
    private float dx, dy;
    private float acceleration;
    private float maxSpeed;
    private boolean left, right, up, down;
    
    
    public Movement(float acceleration, float maxSpeed) {
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
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


    public float getAcceleration() {
        return acceleration;
    }
    
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }
    
    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setSpeed(float speed) {
        this.maxSpeed = speed;
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
            dy += acceleration * dt;
        } else if (down) {
            dy -= acceleration * dt;
        }
        
        // If pressing left or right, the player will move on the x axis
        if (right) {
            dx += acceleration * dt;
        } else if (left) {
            dx -= acceleration * dt;
        }

        // deccelerating
        float vec = (float) sqrt(dx * dx + dy * dy);
        if (up == false && down == false && vec >0) {
            dy -= (dy / vec) * 500 * dt;
        }
        
        if (right == false && left == false && vec >0) {
            dx -= (dx / vec) * 500 * dt;
        }
        
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        if (x > gameData.getSceneWidth()) {
            x = 0;
        } else if (x < 0) {
            x = gameData.getSceneWidth();
        }

        y += dy * dt;
        if (y > gameData.getSceneHeight()) {
            y = 0;
        } else if (y < 0) {
            y = gameData.getSceneHeight();
        }

        position.setX(x);
        position.setY(y);

    }
    
    
    
    
    
}
