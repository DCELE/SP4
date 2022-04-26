package group14.map;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Room;
import group14.common.gameobjects.TileType;
import static group14.common.gameobjects.TileType.TILE;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class RoomPlugin implements IPlugin{

    
    TileType[][] room = {{TILE, TILE, TILE,TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE, TILE},
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
    Room room = new Room(world, room, 16, 16);
    
    }
    
    
    
    @Override
    public void stop(GameData gameData, World world) {
    
    }
    
   
   
    
}