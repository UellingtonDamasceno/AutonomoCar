package controllers.backend;

import java.awt.Point;
import model.Car;
import util.Settings.SpritesCars;

/**
 *
 * @author uellington
 */
public class CarController {
    private Car car;
    
    public void setCar(Car car){
        this.car = car;
    }
    
    public Car getCar() {
        return this.car;
    }

    public Car createCar(SpritesCars selectedCar, String cityName) {
        switch(selectedCar){
            default:{
                return new Car(selectedCar.getSmall(), cityName, new Point());
            }
        }
    }
    
    
}
