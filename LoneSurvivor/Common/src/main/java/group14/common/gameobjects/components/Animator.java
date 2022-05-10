/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import java.util.HashMap;

/**
 *
 * @author Dilara
 */
public class Animator implements Component {

    private HashMap<String, AnimationFrame[]> animations;
    private String currentAnimation;
    private int currentIndex;
    private float timer;
    
    private boolean setForDuration;
    private float triggerDuration;
    private String previousAnimation;
    
    public Animator(String startAnimation) {
        this.animations = new HashMap<String, AnimationFrame[]>();
        this.currentAnimation = startAnimation;
        currentIndex = 0;
        timer = 0;
        setForDuration = false;
    }

    public void addAnimation(String trigger, AnimationFrame[] frames) {
        this.animations.put(trigger, frames);
    }

    public void setTrigger(String trigger) {
        this.currentAnimation = trigger;
        this.currentIndex = 0;
        this.timer = 0;
    }
    
    public void setTriggerForDuration(String trigger, float duration) {
        this.previousAnimation = currentAnimation;
        this.triggerDuration = duration;
        this.setForDuration = true;
        setTrigger(trigger);
    }

    @Override
    public void update(Entity entity, GameData gameData, World world) {
        this.timer += gameData.getDeltaTime();
        
        if (setForDuration == true) {
            triggerDuration -= gameData.getDeltaTime();
            if (triggerDuration <= 0) {
                setForDuration = false;
                setTrigger(previousAnimation);
            }
        }
        AnimationFrame[] currentAnimationFrames = this.animations.get(this.currentAnimation);

        if (currentAnimationFrames[this.currentIndex].getDuration() < this.timer) {
            this.currentIndex += 1;
            if (this.currentIndex > currentAnimationFrames.length) {
                this.currentIndex = 0;
            }
            entity.setImage(currentAnimationFrames[this.currentIndex].getImage());
        }
    }

}
