package group14.map;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Portal;
import group14.common.gameobjects.Room;
import group14.common.gameobjects.Tile;
import group14.common.gameobjects.TileType;
import group14.common.gameobjects.components.AnimationFrame;
import group14.common.gameobjects.components.Animator;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class RoomPlugin implements IPlugin{

    // The tiles that can be on the map
    TileType[] tiles = {TileType.TILE1, TileType.TILE2, TileType.TILE3, TileType.TILE4, 
        TileType.TILE5, TileType.TILE6, TileType.TILE7, TileType.TILE8, 
        TileType.TILE9, TileType.TILE10, TileType.TILE11, TileType.TILE12,
        TileType.TILE7, TileType.TILE7, TileType.TILE7, TileType.TILE7,
        TileType.TILE7, TileType.TILE7, TileType.TILE7, TileType.TILE7};
    
    // the tile coordiates for the portals should be
    float[][] portalCoordinates = {{3, 10}, {3, 3}, {14, 3}, {14, 10}};
    
    public void createPortal(float x, float y, World world) {
        
        // to add animation and make it look like portal opens up, we need a closed portal image
        // as default image
        Portal portal = new Portal("portal_closed.png");
        Position portalPosition = new Position(x, y, 3.1415f / 2);
        
        Animator portalAnimator = new Animator("idle", portal);
        portalAnimator.addAnimation("idle", new AnimationFrame[]{new AnimationFrame("portal_closed.png", 1)});
        portalAnimator.addAnimation("opening", new AnimationFrame[]{new AnimationFrame("portal.png", 1)});
        
        portal.addComponent(portalPosition);
        portal.addComponent(portalAnimator);
        
        world.addEntity(portal);
        
    }
    
    @Override
    public void start(GameData gameData, World world) {
        // creating room with 16x16 pixels, 12 tiles height and 16 tiles width
        Room room = new Room(world, tiles, 16, 16, 12, 16);
        
        world.addEntity(room);
        
        
        // placing a portal on every coordinate that has been decided to have a portal
        for (float[] coordinates : portalCoordinates) {
            // times 16 * 3 to scale up
            // minus 8 * 3 to give the portals the same distance to the borders
            createPortal(coordinates[0] * 16 * 3 - 8 * 3, coordinates[1] * 16 * 3 - 8 * 3, world);
        } 
        
    }
   
    @Override
    public void stop(GameData gameData, World world) {
        
        // removing tile, room, and portal from world if room module is removed
        for (Entity tile : world.getEntities(Tile.class)) {
            world.removeEntity(tile);
        }
        for (Entity tile : world.getEntities(Room.class)) {
            world.removeEntity(tile);
        }
        for (Entity tile : world.getEntities(Portal.class)) {
            world.removeEntity(tile);
        }
        
    }
    
}