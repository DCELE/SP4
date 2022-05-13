/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.gameengine.gamestate;

import group14.gameengine.Game;

/**
 *
 * @author Dilara
 */
public abstract class GameState {

    protected Game game;

    protected GameState(Game game) {
        this.game = game;
    }
    
    public void switchScreen(GameState gameState) {
        
    }

    public abstract void onOpen();

    public abstract void onClose();

    public abstract void render();
}
