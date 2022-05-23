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
public class EndState extends GameState {

    ImageButton startButton;
    Table buttons;
    Texture startButtonTexture;
    TextureRegion startButtonRegion;
    TextureRegionDrawable startButtonDrawable;
    Stage stage;

    // end game screen
    public EndState(Game game) {
        super(game);

        buttons = new Table();
        stage = new Stage(new ScreenViewport());
        startButtonTexture = new Texture("assets/start_button.png");
        startButtonRegion = new TextureRegion(startButtonTexture);
        startButtonDrawable = new TextureRegionDrawable(startButtonRegion);

        // Creating button and giving it an animation when clicked
        startButton = new ImageButton(startButtonDrawable);

        startButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/start_button.png"))));
        startButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/start_button_pressed.png"))));

        stage.addActor(startButton);

        // size of the button
        float heigth = (float) (startButtonTexture.getHeight() * 0.5);
        float width = (float) (startButtonTexture.getWidth() * 0.5);
        startButton.setSize(width, heigth);
        startButton.setPosition(game.gameData.getSceneWidth() / 2 - width / 2, 50);

        // listen to mouse click on the button
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
        
        // background color
        Gdx.gl.glClearColor(66f / 255.0f, 40f / 255.0f, 53f / 255.0f, 1);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

}
