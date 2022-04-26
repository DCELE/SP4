/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package group14.common.gameobjects;

/**
 *
 * @author Dilara
 */
public enum TileType {
    
    
    
    TILE(0, "tile.png");
    
    
    private int id;
    private String image;
    
    TileType(int id, String image){
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
   
    
    
    
    
    
}
