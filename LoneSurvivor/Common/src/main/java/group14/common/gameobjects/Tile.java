/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects;

/**
 *
 * @author Dilara
 */
public class Tile extends Entity{

    // Tile is the entity that shows one tile. If there are 4 tiles in the room, then there will 
    // be 4 tile entities in the room
    
    
    // Every tile needs a tiletype
    TileType tileType;
    
    // Tile coordinate in the room
    int roomX;
    int roomY;
    
    public Tile(TileType tileType, int roomX, int roomY){
        super(tileType.getImage());
        this.tileType = tileType;
        this.roomX = roomX;
        this.roomY = roomY;
    }
    
    
    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public int getRoomX() {
        return roomX;
    }

    public void setRoomX(int roomX) {
        this.roomX = roomX;
    }

    public int getRoomY() {
        return roomY;
    }

    public void setRoomY(int roomY) {
        this.roomY = roomY;
    }
}
