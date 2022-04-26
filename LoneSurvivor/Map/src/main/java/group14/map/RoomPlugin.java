package group14.map;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Room;
import group14.common.gameobjects.Tile;
import group14.common.gameobjects.TileType;
import static group14.common.gameobjects.TileType.TILE;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class RoomPlugin implements IPlugin{

    
    TileType[][] roomTiles = {{TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE}};
    
    
    @Override
    public void start(GameData gameData, World world) {
        Room room = new Room(world, roomTiles, 16, 16);
        world.addEntity(room);
    }
   
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity tile : world.getEntities(Tile.class)) {
            world.removeEntity(tile);
        }
        for (Entity tile : world.getEntities(Room.class)) {
            world.removeEntity(tile);
        }
        
    }
    
}