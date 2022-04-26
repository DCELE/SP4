/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import group14.common.gameobjects.Entity;

/**
 *
 * @author frederikkelan
 */
public class Sight implements Component {

    private float expiration;
    private float sightLimit;
    
    public Sight(float sightLimit){
        this.expiration = 0;
        this.sightLimit = sightLimit;
    }
    
    public float getSightLimit(){
        if(expiration > 0){
            return 0;
        }
        return this.sightLimit;
    }
    
    public void blindFor(float expiration){
        this.expiration = expiration;
    }
    
    public void reduceExpiration(float delta){
        this.expiration -= delta;
        if(this.expiration < 0){
            this.expiration = 0;
        }
    }


    @Override
    public void update(Entity entity, GameData gameData) {
    if(expiration > 0){
            reduceExpiration(gameData.getDeltaTime());
        }
    }
    
}
