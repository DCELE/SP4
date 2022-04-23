package group14.gameengine;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import group14.common.game.GameData;

/**
 *
 * @author Dilara
 */
public class Game implements ApplicationListener{

    public static OrthographicCamera cam;
    private final GameData gameData = new GameData();
    
    @Override
    public void create() {
        gameData.setSceneWidth(Gdx.graphics.getWidth());
        gameData.setSceneHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getSceneWidth(), gameData.getSceneHeight());
        cam.translate(gameData.getSceneWidth() / 2, gameData.getSceneHeight() / 2);
        cam.update();
    }

    @Override
    public void resize(int i, int i1) {
    
    }

    @Override
    public void render() {
        gameData.setDeltaTime(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameData.getInput().update();
        gameData.getInput().updateMouse(Gdx.input.getX(), gameData.getSceneHeight() - Gdx.input.getY());
    }

    @Override
    public void pause() {
    
    }

    @Override
    public void resume() {
    
    }

    @Override
    public void dispose() {
    
    }
    
    
    
}
