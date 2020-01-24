package model;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author uellington
 */
public class Road implements Observer {
        
    private String sprite, name;

    private Road previous, next;
    
    private int positionX, positionY;
    
    public Road(String sprite, int posX, int posY) {
        this.sprite = sprite;
        this.positionX = posX;
        this.positionY = posY;
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
}
