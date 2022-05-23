/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.player;

import group14.common.game.GameData;
import static group14.common.game.Input.DOWN;
import static group14.common.game.Input.LEFT;
import static group14.common.game.Input.RIGHT;
import static group14.common.game.Input.SPACE;
import static group14.common.game.Input.UP;
import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.Player;
import group14.common.gameobjects.components.Health;
import group14.common.gameobjects.components.Movement;
import group14.common.gameobjects.components.Position;
import group14.common.services.IUpdate;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})

public class PlayerController implements IUpdate {

    @Override
    public void update(GameData gameData, World world) {
        
        for (Entity player : world.getEntities(Player.class)) {
            Position position = player.getComponent(Position.class);
            Movement movement = player.getComponent(Movement.class);

            movement.setLeft(gameData.getInput().isDown(LEFT));
            movement.setRight(gameData.getInput().isDown(RIGHT));
            movement.setUp(gameData.getInput().isDown(UP));
            movement.setDown(gameData.getInput().isDown(DOWN));

        }
    }
    
}
