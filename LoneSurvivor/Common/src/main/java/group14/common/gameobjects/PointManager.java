/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects;

/**
 *
 * @author Dilara
 */
public class PointManager extends Entity{
    
    int point = 0;

    public int getPoint() {
        return point;
    }
    
    // Increase points with 1
    public void increment() {
        point += 1;
    }
}
