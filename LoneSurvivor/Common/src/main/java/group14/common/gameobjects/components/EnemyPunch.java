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
public class EnemyPunch implements Component {

    float cooldown = 1f;
    float timer = 0;
    float damage = 1;
    float range = 16 * 3;
    
    @Override
    public void update(Entity entity, GameData gameData, World world) {
    
        // Timer counts down if there is a cooldown
        if (timer > 0) {
            timer -= gameData.getDeltaTime();
            return;
        }
        
        AIBrain enemyBrain = entity.getComponent(AIBrain.class);
        Position enemyPosition = entity.getComponent(Position.class);
        Entity target = enemyBrain.getTarget();
        
        // If enemy doesn't have a target then it won't punch anything
        // The target could be gone if it is dead or not loaded
        if (target == null) {
            return;
        }
        
        // Getting the position of the target
        Position targetPosition = target.getComponent(Position.class);
        
        // Calculating the distance between the enemy position and the target position
        // to see if we are in the distance that we can hit the player
        float distance = (float) Math.sqrt(Math.pow(enemyPosition.getX() -
                targetPosition.getX(),2) + Math.pow(enemyPosition.getY() - 
                        targetPosition.getY(), 2));
        
        // If the distance is less than the range, then the target will lose health
        // after the hit, there will be a cooldown
        if (distance < range) {
            Health targetHealth = target.getComponent(Health.class);
            targetHealth.damage(damage);
            timer = cooldown;
            
            // Adding animation so everytime the enemy punches then it will change to the
            // eating animation
            Animator entityAnimator = entity.getComponent(Animator.class);
            entityAnimator.setTriggerForDuration("eating",0.15f);
            
            // Target will be removed from the world if death
            if (targetHealth.isDeath() == true) {
                world.removeEntity(target);
            }
        }
        
    }
    
}
