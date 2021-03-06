package group14.common.gameobjects;

import group14.common.game.World;
import group14.common.gameobjects.components.Position;

public class Room extends Entity{
    
    // All the tiles that are in the room
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
        
        // Making the room
        // Getting the height and width of the room and adding tiles
        for (int row = 0; row < roomHeigth; row++) { 
            for (int col = 0; col < roomWidth; col++) { 
                // Randomize the tile types in the room
                Tile tile = createTile(room[(int) (Math.random()*(room.length))], col, row);
                this.room[col][row] = tile;
                world.addEntity(tile);
            } 
        }
    
    }
    
    // creating just one (1) tile
    private Tile createTile(TileType tileType, int x, int y) {
        Tile tile = new Tile(tileType, x, y);
        
        // Tile times 1.5 to set the tiles in the middle
        // Tile times 3 to scale up sizes
        // Create position for one tile
        Position position = new Position(x * tileWidth * 3 + tileWidth * 3/2, y * tileHeigth * 3 + tileHeigth * 3/2, 3.1415f / 2);
        
        // Add position to one tile
        tile.addComponent(position);
        return tile;
    }
    
    // Getting one single tile from the whole room
    public Tile getRoomTile(int x, int y) {
        
        // Checking if we are outside of the room
        if (x < 0 || x >= roomWidth) {
            return null;
        }
        if (y < 0 || y >= roomHeigth) {
            return null;
        }
        return this.room[x][y];
    }
    
    // Getting one single tile from the whole room by pixels
    // Calculating which tile based on pixels
    public Tile getTile(int x, int y) {
        
        // Checking if we are outside of the room
        // times 3 to have the same scale as the rest of the game
        // times roomwidth to get the end of the room
        if (x < 0 || x >= tileWidth * 3 * this.roomWidth) {
            return null;
        }
        if (y < 0 || y >= tileHeigth * 3 * this.roomHeigth) {
            return null;
        }
        
        // Getting the coordinates of the tile we want to find
        int tileX = (int) (x / (tileWidth * 3)); // find the right tile on colomn
        int tileY = (int) (y / (tileHeigth * 3)); // find the right tile on row
        
        // Getting tile from room based on the coordinates and returning it
        return this.room[tileX][tileY];
    }
    
    
}