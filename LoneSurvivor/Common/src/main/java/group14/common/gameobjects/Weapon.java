/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects;

import group14.common.game.GameData;
import group14.common.gameobjects.components.Position;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author Dilara
 */
public class Weapon extends Entity{
    
    float speed;
    
    public Weapon() {
        super();
    }

    public Weapon(String image, float x, float y, float speed, float rotation) {
        super(image);
        this.speed = speed;
        Position position = new Position(x, y, rotation);
        addComponent(position);
        
    }
    
    public void updateWeapon(GameData gameData){
        Position position = getComponent(Position.class);
        float x = position.getX();
        float y = position.getY();
        float rotation = position.getRadians();
        float deltaTime = gameData.getDeltaTime();
        position.setX(x + (float) cos(rotation) * speed * deltaTime);
        position.setY(y + (float) sin(rotation) * speed * deltaTime);
        
    }
    
}
