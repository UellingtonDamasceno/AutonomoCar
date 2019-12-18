package model;

import com.sun.javafx.geom.Area;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author uellington
 */
public class Road implements Observer{
    private List<Car> cars;
    private Area area;
    
    public Road(){
        this.cars = new LinkedList();
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
