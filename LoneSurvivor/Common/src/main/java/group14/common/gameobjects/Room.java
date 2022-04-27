package group14.common.gameobjects;

import group14.common.game.World;
import group14.common.gameobjects.components.Position;

public class Room extends Entity{
    
    TileType[][] room = new TileType[0][0]; //Erstat denne linje pga AI
    Tile[][] room1 = new Tile[0][0];
    
    
    int tileWidth;
    int tileHeigth;
    
    public Room(World world, TileType[] room, int tileWidth, int tileHeigth, int heigth, int width) {        
        this.tileWidth = tileWidth;
        this.tileHeigth = tileHeigth;
        for (int row = 0; row < width; row++) { 
            for (int col = 0; col < heigth; col++) { 
                Tile tile = createTile(room[(int) (Math.random()*(room.length))], row, col);
                world.addEntity(tile);
            } 
        }
    
    }
    public Room(World world, TileType[][] room, int tileWidth, int tileHeigth) {        
        this.room = room;
        this.tileWidth = tileWidth;
        this.tileHeigth = tileHeigth;
        for (int row = 0; row < room.length; row++) { 
            for (int col = 0; col < room[row].length; col++) { 
                Tile tile = createTile(room[row][col], row, col);
                world.addEntity(tile);
            } 
        }
    
    }

    
    private Tile createTile(TileType tileType, int x, int y) {
        Tile tile = new Tile(tileType);
        Position position = new Position(x * tileHeigth * 3 + tileHeigth * 3/2, y * tileWidth * 3 + tileWidth * 3/2, 3.1415f / 2);
        tile.addComponent(position);
        return tile;
    }
}