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
    
    TileType tileType;
    
    public Tile(TileType tileType){
        super(tileType.getImage());
        this.tileType = tileType;
    }
    
}
