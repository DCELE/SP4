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
    // TileType is enum because the values for tile1, tile2 etc. will always look the same
    
    // the different images of tiles
    TILE1(0, "tile000.png"),
    TILE2(0, "tile001.png"),
    TILE3(0, "tile002.png"),
    TILE4(0, "tile003.png"),
    TILE5(0, "tile004.png"),
    TILE6(0, "tile005.png"),
    TILE7(0, "tile006.png"),
    TILE8(0, "tile007.png"),
    TILE9(0, "tile008.png"),
    TILE10(0, "tile009.png"),
    TILE11(0, "tile010.png"),
    TILE12(0, "tile011.png");
    
    
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
