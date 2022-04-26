package group14.gameengine;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Tile;
import group14.common.services.IPlugin;
import group14.common.services.IUpdate;
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
    private final GameData gameData = new GameData();
    private final Lookup lookup = Lookup.getDefault();
    private List<IPlugin> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IPlugin> result;
    private World world = new World();
    private SpriteBatch spriteBatch;
    private AssetController assetController;
    
    @Override
    public void create() {
        gameData.setSceneWidth(Gdx.graphics.getWidth());
        gameData.setSceneHeight(Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        cam = new OrthographicCamera(gameData.getSceneWidth(), gameData.getSceneHeight());
        cam.translate(gameData.getSceneWidth() / 2, gameData.getSceneHeight() / 2);
        cam.update();
        this.spriteBatch = new SpriteBatch();
        this.assetController = new AssetController();
        result = lookup.lookupResult(IPlugin.class);
        result.addLookupListener(lookupListener);
        result.allItems();
    }

    @Override
    public void resize(int i, int i1) {
    
    }

    @Override
    public void render() {
        gameData.setDeltaTime(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameData.getInput().update();
        for (IUpdate update : lookup.lookupAll(IUpdate.class)) {
            update.update(gameData, world);
        }
        draw();
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
    
    private void draw() {
    spriteBatch.begin();
    for (Entity entity : world.getEntities(Tile.class)) {
            assetController.drawEntity(entity, this.spriteBatch);
    }
    for (Entity entity : world.getEntities()) {
        if (!entity.getClass().equals(Tile.class)){
            assetController.drawEntity(entity, this.spriteBatch);
        }
    }
    spriteBatch.end();
    
    }
    
    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IPlugin> updated = result.allInstances();

            for (IPlugin plugins : updated) {
                // Newly installed modules
                if (!gamePlugins.contains(plugins)) {
                    plugins.start(gameData, world);
                    gamePlugins.add(plugins);
                }
            }

            // Stop and remove module
            for (IPlugin plugins : gamePlugins) {
                if (!updated.contains(plugins)) {
                    plugins.stop(gameData, world);
                    gamePlugins.remove(plugins);
                }
            }
        }

    };
    
    
}
