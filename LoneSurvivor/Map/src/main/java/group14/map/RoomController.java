package group14.map;


import group14.common.game.GameData;
import group14.common.gameobjects.Room;



import group14.common.game.World;
import group14.common.gameobjects.Entity;



import group14.common.services.IUpdate;


import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})

public class RoomController implements IUpdate {

    @Override
    public void update(GameData gameData, World world) {
        //System.out.println(world.getEntities(Player.class).size());
        for (Entity room : world.getEntities(Room.class)) {
        Entity currentRoom = findCurrentRoom(gameData, world);
        if (currentRoom == null) {
            Entity newRoom = RoomPlugin.createRoom(gameData, 600,400);
            world.addEntity(newRoom);
        }
//        for (Entity newRoom : world.getEntities(Room.class)) {
//            
//        }
    }
        
    }     
private Entity findCurrentRoom(GameData gameData, World world) {
    for (Entity room : world.getEntities(Room.class)) {
   {
                return room;
            }
        }
        return null;
    }
}


 