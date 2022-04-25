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
public class Timer implements Component {
    
    private float expiration;

    public Timer(float expiration) {
        this.expiration = expiration;
    }

    public float getExpiration() {
        return expiration;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }

    public void reduceExpiration(float delta) {
        this.expiration -= delta;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
        if (expiration > 0) {
            reduceExpiration(gameData.getDeltaTime());
        }

//        if (expiration <= 0) {
//            LifePart lifePart = entity.getPart(LifePart.class);
//            lifePart.setLife(0);
//
//        }
    }
    
}
