/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.gameengine.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import group14.gameengine.Game;

/**
 *
 * @author Dilara
 */
public class StartState extends GameState {

    Texture logo;
    ImageButton startButton;
    Table buttons;
    Texture startButtonTexture;
    TextureRegion startButtonRegion;
    TextureRegionDrawable startButtonDrawable;
    Stage stage;
    
    public StartState(Game game) {
        super(game);
        logo = new Texture("assets/startscreen.png");
        buttons = new Table();
        stage = new Stage(new ScreenViewport());
        startButtonTexture = new Texture("assets/start_button.png");
        startButtonRegion = new TextureRegion(startButtonTexture);
        startButtonDrawable = new TextureRegionDrawable(startButtonRegion);
        
        
        startButton = new ImageButton(startButtonDrawable);
        
        startButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/start_button.png"))));
        
        startButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/start_button_pressed.png"))));
        
        stage.addActor(startButton);
        float heigth = (float) (startButtonTexture.getHeight() * 0.5);
        float width = (float) (startButtonTexture.getWidth() * 0.5);
        startButton.setSize(width, heigth);
        startButton.setPosition(game.gameData.getSceneWidth() / 2 - width /2, 50);
        
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit button clicked");
                game.switchState(new PlayState(game));
            }
            
        });
        Gdx.input.setInputProcessor(stage);
        
    }
    
    
    @Override
    public void onOpen() {
   
    }

    @Override
    public void onClose() {
        stage.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClearColor(66, 40, 53, 1);
        Gdx.gl.glClearColor(66f/255.0f, 40f/255.0f, 53f/255.0f, 1);


        game.batch.begin();
        //game.batch.draw(logo, game.gameData.getSceneWidth() / 2, game.gameData.getSceneHeight() / 2);
        float heigth = (float) (logo.getHeight() * 0.3);
        float width = (float) (logo.getWidth() * 0.3);
        game.batch.draw(logo, (float) game.gameData.getSceneWidth() / 2 - width / 2, 
                (float) game.gameData.getSceneHeight() / 2 - heigth / 2 + 30, width, heigth);
        game.batch.end();
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    
    
}
