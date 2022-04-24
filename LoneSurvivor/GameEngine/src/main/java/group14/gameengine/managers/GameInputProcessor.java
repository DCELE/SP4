/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.gameengine.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import group14.common.game.GameData;
import group14.common.game.Input;

/**
 *
 * @author Dilara
 */
public class GameInputProcessor extends InputAdapter{
    
    private final GameData gameData;

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }
    public boolean keyDown(int k) {
        if (k == Keys.UP) {
            gameData.getInput().setKeyStatus(Input.UP, true);
        }
        if (k == Keys.LEFT) {
            gameData.getInput().setKeyStatus(Input.LEFT, true);
        }
        if (k == Keys.DOWN) {
            gameData.getInput().setKeyStatus(Input.DOWN, true);
        }
        if (k == Keys.RIGHT) {
            gameData.getInput().setKeyStatus(Input.RIGHT, true);
        }
        if (k == Keys.ENTER) {
            gameData.getInput().setKeyStatus(Input.ENTER, true);
        }
        if (k == Keys.ESCAPE) {
            gameData.getInput().setKeyStatus(Input.ESCAPE, true);
        }
        if (k == Keys.SPACE) {
            gameData.getInput().setKeyStatus(Input.SPACE, true);
        }
        if (k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
            gameData.getInput().setKeyStatus(Input.SHIFT, true);
        }
        return true;
    }
    public boolean keyUp(int k) {
        if (k == Keys.UP) {
            gameData.getInput().setKeyStatus(Input.UP, false);
        }
        if (k == Keys.LEFT) {
            gameData.getInput().setKeyStatus(Input.LEFT, false);
        }
        if (k == Keys.DOWN) {
            gameData.getInput().setKeyStatus(Input.DOWN, false);
        }
        if (k == Keys.RIGHT) {
            gameData.getInput().setKeyStatus(Input.RIGHT, false);
        }
        if (k == Keys.ENTER) {
            gameData.getInput().setKeyStatus(Input.ENTER, false);
        }
        if (k == Keys.ESCAPE) {
            gameData.getInput().setKeyStatus(Input.ESCAPE, false);
        }
        if (k == Keys.SPACE) {
            gameData.getInput().setKeyStatus(Input.SPACE, false);
        }
        if (k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
            gameData.getInput().setKeyStatus(Input.SHIFT, false);
        }
        return true;
    }
    
}
