package group14.common.gameobjects;

import group14.common.game.World;
import group14.common.gameobjects.components.Position;

public class Room extends Entity{
    
    Tile[][] room = new Tile[0][0];
    
    
    int tileWidth;
    int tileHeigth;
    int roomWidth;
    int roomHeigth;
    
    public Room(World world, TileType[] room, int tileWidth, int tileHeigth, int roomHeigth, int roomWidth) {        
        this.tileWidth = tileWidth;
        this.tileHeigth = tileHeigth;
        this.roomHeigth = roomHeigth;
        this.roomWidth = roomWidth;
        this.room = new Tile[roomWidth][roomHeigth];
        for (int row = 0; row < roomHeigth; row++) { 
            for (int col = 0; col < roomWidth; col++) { 
                Tile tile = createTile(room[(int) (Math.random()*(room.length))], col, row);
                this.room[col][row] = tile;
                world.addEntity(tile);
            } 
        }
    
    }
    
    private Tile createTile(TileType tileType, int x, int y) {
        Tile tile = new Tile(tileType);
        Position position = new Position(x * tileWidth * 3 + tileWidth * 3/2, y * tileHeigth * 3 + tileHeigth * 3/2, 3.1415f / 2);
        tile.addComponent(position);
        return tile;
    }
    
    public Tile getTile(int x, int y) {
        
        if (x < 0 || x > tileWidth * 3 * this.roomWidth) {
            return null;
        }
        if (y < 0 || y > tileHeigth * 3 * this.roomHeigth) {
            return null;
        }
        int tileX = (int) (x / (tileWidth * 3 + tileWidth * 3)) + 1; // find the right tile on colomn
        int tileY = (int) (y / (tileHeigth * 3 + tileHeigth * 3)) + 1; // find the right tile on row
        
        return this.room[tileX][tileY];
    }
}