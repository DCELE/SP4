/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects.components;

/**
 *
 * @author Dilara
 */
public class AnimationFrame {

    private String image;
    private float duration;

    public AnimationFrame(String image, float duration) {
        this.image = image;
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public float getDuration() {
        return duration;
    }
}
