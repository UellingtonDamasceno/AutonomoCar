package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import model.states.Single;
import org.json.JSONObject;
import util.RoadState;
import util.Settings.RoadsTypes;

/**
 *
 * @author uellington
 */
public class Road implements Observer, Serializable {

    private String sprite;
    private RoadsTypes type;
    
    private RoadState state;
    
    private int positionX, positionY;
    private int height, width;

    public Road(String sprite, int posX, int posY, int h, int w){
        this(sprite, posX, posY, h, w, RoadsTypes.SINGLE);
    }
    
    public Road(String sprite, int posX, int posY, int h, int w, RoadsTypes type) {
        this.sprite = sprite;
        this.positionX = posX;
        this.positionY = posY;
        this.height = h;
        this.width = w;
        this.state = new Single();
    }

    public String getSprite() {
        return this.sprite;
    }
    
    public String getType(){
        return this.state.getType();
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setType(RoadsTypes type){
        this.type = type;
    }
    
    public void putUp(){
        this.state = this.state.putUp();
    }
    
    public void putDown(){
        this.state = this.state.putDown();
    }
    
    public void putLeft(){
        this.state = this.state.putLeft();
    }
    
    public void putRight(){
        this.state = this.state.putRight();
    }
    
    public void removeUp(){
        this.state = this.state.removeUp();
    }
    
    public void removeDown(){
        this.state = this.state.removeDown();
    }
    
    public void removeLeft(){
        this.state = this.state.removeLeft();
    }
    
    public void removeRight(){
        this.state = this.state.removeRight();
    }
    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public int hashCode() {
        String value = Integer.toString(this.height);
        value = Integer.toString(this.width) + value;
        value = Integer.toString(this.positionX) + value;
        value = value + Integer.toString(this.positionY) + value;
        return value.hashCode();

    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Road) {
            Road another = (Road) object;
            return (this.sprite.equals(another.sprite)
                    && this.hashCode() == another.hashCode());
        }
        return false;
    }

    public String toString() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonArray = new JSONObject();

        jsonObject.accumulate("Sprite", sprite.substring(sprite.lastIndexOf("/")));
//        jsonObject.accumulate("sprite", this.sprite);
        //jsonObject.accumulate("roadName", this.name);
        jsonArray.accumulate("x", this.positionX);
        jsonArray.accumulate("y", this.positionY);
        jsonObject.accumulate("position", jsonArray);

//        jsonArray = new JSONObject();
//        jsonArray.accumulate("height", this.height);
//        jsonArray.accumulate("width", this.width);
//        jsonObject.accumulate("dimension", jsonArray);
        return jsonObject.toString();
    }
}
