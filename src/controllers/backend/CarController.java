package controllers.backend;

import java.util.HashMap;
import java.util.Map;
import model.Car;
import util.Point;
import util.Settings.SpritesCars;
import static util.Settings.SpritesCars.DODGE;
import static util.Settings.SpritesCars.FERRARI;
import static util.Settings.SpritesCars.JAGUAR;
import static util.Settings.SpritesCars.MERCEDES;
import static util.Settings.SpritesCars.SKYLINE;

/**
 *
 * @author uellington
 */
public class CarController {

    private Car mainCar;
    private Map<String, Point> anotherCars;

    public CarController() {
        this.anotherCars = new HashMap<>();
    }

    public void setMainCar(Car car) {
        this.mainCar = car;
    }

    public Car getMainCar() {
        return this.mainCar;
    }

    public Point putNewCar(String id, Point another) {
        this.anotherCars.put(id, another);
        return another;
    }

    public void removeCar(String id) {
        this.anotherCars.remove(id);
    }

    public Car createCar(SpritesCars selectedCar) {
        Car another;
        switch (selectedCar) {
            case DODGE: {
                another = new Car(selectedCar);
                break;
            }
            case SKYLINE: {
                another = new Car(selectedCar);
                break;
            }
            case FERRARI: {
                another = new Car(selectedCar);
                break;
            }
            case MERCEDES: {
                another = new Car(selectedCar);
                break;
            }
            case JAGUAR: {
                another = new Car(selectedCar);
                break;
            }
            default: {
                another = new Car(selectedCar);
                break;
            }
        }

        return another;
    }

    public void updatePosition(String id, Point point, int velocity) {
        Point car = this.anotherCars.get(id);
        car.setX(point.getX());
        car.setY(point.getY());
        if (this.mainCar.isFront(point)) {
            double distance = this.mainCar.calculateDistance(point);
            if (distance < 55) {
                this.mainCar.setMaxVelocity(velocity);
            }
//            else if (distance > 70) {
//                this.mainCar.restoreVelocity();
//            }
//        } else {
//            this.mainCar.restoreVelocity();
//        }
        }
    }
}
