package group14.gameengine;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.PointManager;
import group14.common.gameobjects.Tile;
import group14.common.gameobjects.components.Component;
import group14.common.gameobjects.components.Health;
import group14.common.services.IPlugin;
import group14.common.services.IUpdate;
import group14.gameengine.gamestate.GameState;
import group14.gameengine.gamestate.PlayState;
import group14.gameengine.gamestate.StartState;
import group14.gameengine.managers.AssetController;
import group14.gameengine.managers.GameInputProcessor;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author Dilara
 */
public class Game implements ApplicationListener{

    public static OrthographicCamera cam;
    public final GameData gameData = new GameData();
    public final Lookup lookup = Lookup.getDefault();
    public List<IPlugin> gamePlugins = new CopyOnWriteArrayList<>();
    public Lookup.Result<IPlugin> result;
    public World world = new World();
    public SpriteBatch spriteBatch;
    public AssetController assetController;
    public SpriteBatch batch;
    public BitmapFont font;
    public GameState currentGameState;
    
    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2, 2);
        
        gameData.setSceneWidth(Gdx.graphics.getWidth());
        gameData.setSceneHeight(Gdx.graphics.getHeight());


        cam = new OrthographicCamera(gameData.getSceneWidth(), gameData.getSceneHeight());
        cam.translate(gameData.getSceneWidth() / 2, gameData.getSceneHeight() / 2);
        cam.update();
        this.spriteBatch = new SpriteBatch();
        this.assetController = new AssetController();
        result = lookup.lookupResult(IPlugin.class);

        result.allItems();
        currentGameState = new StartState(this);
    }

    @Override
    public void resize(int i, int i1) {
    
    }

    @Override
    public void render() {
        currentGameState.render();
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
    

    public void switchState(GameState gameState) {
        currentGameState.onClose();
        currentGameState = gameState;
        currentGameState.onOpen();
    }
     
}
