/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.ai;

import group14.common.game.World;
import group14.common.gameobjects.Entity;
import group14.common.gameobjects.components.Position;
import group14.common.services.ICollisionChecker;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author frederikkelan
 */
public class RandomEngine {
    
    private ICollisionChecker collisionChecker;
    private final int randomSteps = 50;
    private int randomcount = 0;
    private float randomPathX;
    private float randomPathY;
    
    public RandomEngine(ICollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }
    
    public void setCollisionEngine(ICollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }
    
    public Position randomMove(World world, Entity e) {
        Position position = e.getComponent(Position.class);

        if (randomcount == 0) {
            setNewcourse();
        }

        float x = position.getX() + randomPathX;
        float y = position.getY() + randomPathY;

        while (this.collisionChecker != null
                && !this.collisionChecker.isPositionFree(world, e, x, y)) {
            setNewcourse();
            x = position.getX() + randomPathX;
            y = position.getY() + randomPathY;
        }
        randomcount -= 1;

        return new Position(x, y);
    }
    
    private void setNewcourse() {
        ArrayList<Position> directions = new ArrayList<Position>();

        // go north
        directions.add(new Position(0, 0 + 1));
        // go south
        directions.add(new Position(0, 0 - 1));
        // go east
        directions.add(new Position(0 + 1, 0));
        // go west
        directions.add(new Position(0 - 1, 0));
        // go north east
        directions.add(new Position(0 + 1, 0 + 1));
        // go north west
        directions.add(new Position(0 - 1, 0 + 1));
        // go south east
        directions.add(new Position(0 + 1, 0 - 1));
        // go south west
        directions.add(new Position(0 - 1, 0 - 1));
        
        Collections.shuffle(directions);
        randomPathX = directions.get(0).getX();
        randomPathY = directions.get(0).getY();
        randomcount = randomSteps;
    }
    
}
