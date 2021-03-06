/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group14.common.gameobjects;

import group14.common.gameobjects.components.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author Dilara
 */
public class Entity {

    private UUID id;
    private HashMap<Class, Component> components; // contains the components an entity can have

    private String image;
    private float roomHeight;
    private float roomWidth;
   

    public Entity() {
        this.id = UUID.randomUUID();
        this.components = new HashMap<>();
    }

    public Entity(String image) {
        this();
        this.image = image;
    }

    public String getId() {
        return id.toString();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public float getRoomHeight(){
        return roomHeight;
    }
    
     public void setRoomHeight(float roomHeight) {
        this.roomHeight = roomHeight;
    }
    
    public float getRoomWidth(){
        return roomWidth;
    }
    
    public void setRoomWidth(float roomWidth) {
        this.roomWidth = roomWidth;
    }

  

    public void addComponent(Component component) {
        this.components.put(component.getClass(), component);
    }

    public void removeComponent(Class componentClass) {
        if (this.components.containsKey(componentClass)) {
            this.components.remove(componentClass);
        }
    }

    public <O extends Component> O getComponent(Class componentClass) {
        return (O) this.components.get(componentClass);
    }

    public ArrayList<Component> getComponents() {
        return new ArrayList<Component>(this.components.values());
    }
    
    public boolean hasComponent(Class componentClass) {
        return this.components.containsKey(componentClass);
    }

}