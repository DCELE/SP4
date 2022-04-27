/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import group14.common.gameobjects.Entity;

/**
 *
 * @author Dilara
 */
public class AIBrain implements Component{

    Position target;
    

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }
    
    public void findPath() {
        
    }
    
    public float calculateHeuristic() {
        
        return 0;
        
    }
    
    @Override
    public void update(Entity entity, GameData gameData) {
    
    }
    
}
