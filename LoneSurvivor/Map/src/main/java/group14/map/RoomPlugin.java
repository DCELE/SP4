package group14.map;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Room;
import group14.common.gameobjects.Tile;
import group14.common.gameobjects.TileType;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class RoomPlugin implements IPlugin{

    
//    TileType[][] roomTiles = {{TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
//    {TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE}};
    
    TileType[] tiles = {TileType.TILE1, TileType.TILE2, TileType.TILE3, TileType.TILE4, 
        TileType.TILE5, TileType.TILE6, TileType.TILE7, TileType.TILE8, 
        TileType.TILE9, TileType.TILE10, TileType.TILE11, TileType.TILE12,
        TileType.TILE7, TileType.TILE7, TileType.TILE7, TileType.TILE7,
        TileType.TILE7, TileType.TILE7, TileType.TILE7, TileType.TILE7};
    
    
    @Override
    public void start(GameData gameData, World world) {
        //Room room = new Room(world, roomTiles, 16, 16);
        Room room = new Room(world, tiles, 16, 16, 12, 16);
        
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