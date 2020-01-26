package model;

import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class Road implements Observer {
        
    private String sprite, name;

    private Road previous, next;
    
    private int positionX, positionY;
    private int height, width;
    
    public Road(String sprite, int posX, int posY, int h, int w) {
        this.sprite = sprite;
        this.positionX = posX;
        this.positionY = posY;
        this.height = h;
        this.width = w;
    }

    public Road getNextRoad(){
        return this.next;
    }
    
    public Road getPreviousRoad(){
        return this.previous;
    }
    
    public void setNextRoad(Road next){
        this.next = next;
    }
    
    public void setPreviousRoad(Road previous){
        this.previous = previous;
    }
    
    @Override
    public void update(Observable o, Object arg) {

    }
    
    @Override
    public boolean equals(Object object){
      if(object instanceof Road){
          Road another = (Road) object;
          return (this.sprite.equals(another.name));
      }  
      return false;
    }
    
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonArray = new JSONObject();
        
        jsonObject.accumulate("sprite", this.sprite);
        jsonObject.accumulate("roadName", this.name);
        
        jsonArray.accumulate("x", this.positionX);
        jsonArray.accumulate("y", this.positionY);
        jsonObject.accumulate("position", jsonArray);
        
        jsonArray = new JSONObject();
        jsonArray.accumulate("height", this.height);
        jsonArray.accumulate("width", this.width);
        jsonObject.accumulate("dimension", jsonArray);
        
        return jsonObject.toString();
    }
}
