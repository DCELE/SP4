/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.enemy;

import group14.common.game.GameData;
import static group14.common.game.Input.DOWN;
import static group14.common.game.Input.LEFT;
import static group14.common.game.Input.RIGHT;
import static group14.common.game.Input.UP;
import group14.common.game.World;
import group14.common.gameobjects.Enemy;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.AIBrain;
import group14.common.services.IUpdate;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Dilara
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})
public class EnemyController implements IUpdate {

    @Override
    public void update(GameData gameData, World world) {
    
        for (Entity enemy : world.getEntities(Enemy.class)) {
            AIBrain enemyAI = enemy.getComponent(AIBrain.class);
            enemyAI.update(enemy, gameData);
       
        }
    }
    
}
