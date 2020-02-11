package model;

import java.io.Serializable;
import util.Point;
import util.Settings.Orientation;

/**
 *
 * @author Uellington Conceição
 */
public class Transition implements Serializable{
    private final Point point;
    private final Orientation orientation;
    
    public Transition(Orientation orientation, Point point){
        this.point = point;
        this.orientation = orientation;
    }
    
    public Point getPoint(){
        return this.point;
    }
    
    public Orientation getOrientation(){
        return this.orientation;
    }
    
    @Override
    public String toString(){
        return "P: "+ point.toString() + " || O: " + orientation;
    }
}
