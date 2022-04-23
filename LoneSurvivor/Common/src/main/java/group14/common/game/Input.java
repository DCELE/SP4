/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.game;

/**
 *
 * @author Dilara
 */
public class Input {

    private static boolean[] keys;
    private static boolean[] pressedKeys;
    private static final int NUMBEROFKEYS = 10;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ENTER = 4;
    public static final int ESCAPE = 5;
    public static final int SPACE = 6;
    public static final int SHIFT = 7;
    public static final int R = 8;
    public static final int MOUSELEFT = 9;

    public static float mouseX;
    public static float mouseY;

    public Input() {
        this.keys = new boolean[NUMBEROFKEYS];
        this.pressedKeys = new boolean[NUMBEROFKEYS];
    }

    public void update() {
        for (int i = 0; i < NUMBEROFKEYS; i++) {
            pressedKeys[i] = keys[i];
        }
    }

    public void updateMouse(float x, float y) {
        mouseX = x;
        mouseY = y;
    }

    public void setKeyStatus(int key, boolean pressed) {
        keys[key] = pressed;
    }

    public boolean isDown(int key) {
        return keys[key];
    }

    public boolean isPressed(int key) {
        return keys[key] && !pressedKeys[key];
    }

    public void resetKeys() {
        this.keys = new boolean[NUMBEROFKEYS];
        this.pressedKeys = new boolean[NUMBEROFKEYS];
    }

}