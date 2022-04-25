package group14.map;

import group14.common.game.GameData;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Room;
import group14.common.gameobjects.components.Position;
import group14.common.services.IPlugin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class RoomPlugin implements IPlugin{

    @Override
    public void start(GameData gameData, World world) {
    Entity room = createRoom(gameData);
    world.addEntity(room);
    
   
    }
    
        private Entity createRoom(GameData gameData) {
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        return createRoom(gameData, x, y);
    }

    protected static Entity createRoom(GameData gameData, float x, float y) {
        float wallWidth = 32f;
        float startX = x - gameData.getSceneWidth() / 2 + wallWidth;
        float startY = y - gameData.getSceneHeight() / 2 + wallWidth;
        float endX = x + gameData.getSceneWidth() / 2 - wallWidth;
        float endY = y + gameData.getSceneHeight() / 2 - wallWidth;
        float[][] doors = new float[4][4];

        doors[0][0] = (((endX) - (startX)) / 2) + startX - wallWidth / 2f;
        doors[0][1] = startY;
        doors[0][2] = doors[0][0] + wallWidth;
        doors[0][3] = startY;

        Entity room = new Room("room.png", gameData.getSceneHeight(),gameData.getSceneWidth());
        Position roomPosition = new Position(gameData.getSceneWidth()/2, gameData.getSceneHeight()/2, 3.1415f / 2);
        
        
        room.addComponent(roomPosition);
        return room;
    }
    
    
    

    @Override
    public void stop(GameData gameData, World world) {
    
    }
    
   
   
    
}