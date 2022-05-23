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
    
    // How fast the weapon goes woosh
    float speed;
    
    public Weapon() {
        super();
    }

    public Weapon(String image, float x, float y, float speed, float rotation) {
        super(image);
        this.speed = speed;
        
        // Creating a position from the coordinates x and y and the rotation of where the player shoots
        Position position = new Position(x, y, rotation);
        
        // Giving the position to the weapon
        addComponent(position);
        
    }
    
    // Updating the position of the weapon after being used
    public void updateWeapon(GameData gameData){
        
        // Getting a position
        Position position = getComponent(Position.class);
        
        // Getting the coordinates for the position
        float x = position.getX();
        float y = position.getY();
        
        // Getting the radians for rotation
        float rotation = position.getRadians();
        
        // Getting the time
        float deltaTime = gameData.getDeltaTime();
        
        // x and y will be updated based on rotation and speed
        position.setX(x + (float) cos(rotation) * speed * deltaTime);
        position.setY(y + (float) sin(rotation) * speed * deltaTime);
        
    }
    
}
