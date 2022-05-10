/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.gameengine.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Position;

/**
 *
 * @author Dilara
 */
public class AssetController {
    public final AssetManager assetManager;
    private ShapeRenderer sr;

    public AssetController() {
        assetManager = new AssetManager();
        loadAssets();
        assetManager.finishLoading();
        this.sr = new ShapeRenderer();
    }

    private void loadAssets() {      
        assetManager.load("assets/player.png", Texture.class);
        assetManager.load("assets/player_open_mouth.png", Texture.class);
        assetManager.load("assets/enemy.png", Texture.class);
        assetManager.load("assets/room.png", Texture.class);
        assetManager.load("assets/tile.png", Texture.class);
        assetManager.load("assets/weapon.png", Texture.class);
        assetManager.load("assets/tile000.png", Texture.class);
        assetManager.load("assets/tile001.png", Texture.class);
        assetManager.load("assets/tile002.png", Texture.class);
        assetManager.load("assets/tile003.png", Texture.class);
        assetManager.load("assets/tile004.png", Texture.class);
        assetManager.load("assets/tile005.png", Texture.class);
        assetManager.load("assets/tile006.png", Texture.class);
        assetManager.load("assets/tile007.png", Texture.class);
        assetManager.load("assets/tile008.png", Texture.class);
        assetManager.load("assets/tile009.png", Texture.class);
        assetManager.load("assets/tile010.png", Texture.class);
        assetManager.load("assets/tile011.png", Texture.class);
        assetManager.load("assets/portal.png", Texture.class);
        

        
    }

    public Texture getAsset(String name) {
        return assetManager.get(name, Texture.class);
    }
    
    public void drawEntity(Entity entity, SpriteBatch spriteBatch) {
        if (entity.getImage() != null) {
            Texture texture = this.assetManager.get("assets/" + entity.getImage());
            Sprite sprite = new Sprite(texture);
            Position position = entity.getComponent(Position.class);

            sprite.setScale(3);
            if (position.getRadians() > Math.PI / 2 || position.getRadians() < -(Math.PI / 2)) {
                sprite.flip(true, false);
            }

            float x = position.getX() - sprite.getWidth() / 2;
            float y = position.getY() - sprite.getHeight() / 2;
            sprite.setPosition(x, y);
            sprite.draw(spriteBatch);

        }
    }
}
