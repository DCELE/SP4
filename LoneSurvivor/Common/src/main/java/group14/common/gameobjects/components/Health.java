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
public class Health implements Component {

    float health;
    boolean death = false;

    public float getHealth() {
        return health;
    }

    public boolean isDeath() {
        return death;
    }
    
    public Health(float health){
        this.health = health;
    }
    
    public void damage(float damage){
        health -= damage;
        if (health <= 0){
            death = true;
        }
    }
    
    @Override
    public void update(Entity entity, GameData gameData, World world) {
    
    }
    
}
