/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.gameengine.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.PointManager;
import group14.common.gameobjects.Tile;
import group14.common.gameobjects.components.Component;
import group14.common.gameobjects.components.Health;
import group14.common.services.IPlugin;
import group14.common.services.IUpdate;
import group14.gameengine.Game;
import group14.gameengine.managers.GameInputProcessor;
import java.util.Collection;
import java.util.List;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author Dilara
 */
public class PlayState extends GameState{
// the gaming screen
    
    public PlayState(Game game) {
        super(game);
        Gdx.input.setInputProcessor(new GameInputProcessor(game.gameData));
        game.result.addLookupListener(lookupListener);

    }
    
    
    @Override
    public void onOpen() {
        // when the screen opens then we will open every IPlugin
        
        game.gameData.setIsGameOver(false);
    Collection<? extends IPlugin> updated = game.result.allInstances();

            for (IPlugin plugins : updated) {

                    plugins.start(game.gameData, game.world);
                    game.gamePlugins.add(plugins);
                
            }

    }

    @Override
    public void onClose() {
        // when closing the screen then the plugins will stop
        
        game.gameData.getInput().resetKeys();
            game.result.removeLookupListener(lookupListener);
             for (IPlugin plugins : game.gamePlugins) {

                    plugins.stop(game.gameData, game.world);
                    game.gamePlugins.remove(plugins);
                
            }
    }

    @Override
    public void render() {
    
        // deleting everything that is on the screen and then start again
        
        game.gameData.setDeltaTime(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.gameData.getInput().update();
        for (IUpdate update : game.lookup.lookupAll(IUpdate.class)) {
            update.update(game.gameData, game.world);
        }
        // Updates all components in all entities
        for (Entity entity : game.world.getEntities()){
            for (Component components : entity.getComponents()) {
                components.update(entity, game.gameData, game.world);
            }
        }
        
        draw();
        
        game.batch.begin();
        
        // Point text on the screen with updating points
        String text = "Point: ";
        for (Entity point : game.world.getEntities(PointManager.class)) {
            PointManager pointManager = (PointManager) point;
            text += pointManager.getPoint();
            
        }
        
        GlyphLayout layout = new GlyphLayout(game.font, text);
        float fontX = game.gameData.getSceneWidth() / 2 - layout.width / 2;

        game.font.draw(game.batch, text, fontX, game.gameData.getSceneHeight() - 50);
        
        game.batch.end();
        
        if (game.gameData.getIsGameOver()) {
            game.switchState(new EndState(game));
        }
       
    }
    
    private void draw() {
    game.spriteBatch.begin();
    for (Entity entity : game.world.getEntities(Tile.class)) {
            game.assetController.drawEntity(entity, this.game.spriteBatch);
    }
    for (Entity entity : game.world.getEntities()) {
        if (!entity.getClass().equals(Tile.class)){
            game.assetController.drawEntity(entity, this.game.spriteBatch);
        }
    }
    game.spriteBatch.end();
    for (Entity entity : game.world.getEntities(Player.class)) {
            if (entity.hasComponent(Health.class)) {
                game.assetController.drawHealth(entity);
            }
        }
    }
    
    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IPlugin> updated = game.result.allInstances();

            for (IPlugin plugins : updated) {
                // Newly installed modules
                if (!game.gamePlugins.contains(plugins)) {
                    plugins.start(game.gameData, game.world);
                    game.gamePlugins.add(plugins);
                }
            }

            // Stop and remove module
            for (IPlugin plugins : game.gamePlugins) {
                if (!updated.contains(plugins)) {
                    plugins.stop(game.gameData, game.world);
                    game.gamePlugins.remove(plugins);
                }
            }
        }

    };
    
    
}
