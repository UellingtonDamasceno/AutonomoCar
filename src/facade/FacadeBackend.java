package facade;

import controllers.backend.CarController;
import controllers.backend.CityController;
import java.awt.Point;
import model.Car;
import util.Settings.SpritesCars;

/**
 *
 * @author uellington
 */
public class FacadeBackend {
    private static FacadeBackend facade;
    
    private CarController carController;
    private CityController cityController;
    
    private FacadeBackend(){
        this.carController = new CarController();
        this.cityController = new CityController();
    }
    
    public synchronized static FacadeBackend getInstance(){
        return (facade == null) ? facade = new FacadeBackend() : facade;
    }

    public void initialize(){
        //this.cityController.createCity("Xina",15, 15, 10.0, 10.0, 3);
    }

    public void createCity(String name, int height, int width, int x, int y, int prop) {
        this.cityController.createCity(name, x, y, height, width, prop);
    }
    public void selectCar(SpritesCars selectedCar){
        Point originPoint = this.cityController.getOriginPoint();
        Car car = this.carController.createCar(selectedCar, originPoint);
        this.carController.setCar(car);
    }
    
    public String getSelectedCar(){
        return this.carController.getCarSprite();
    }
    
    public int getCityDimensionX(){
        return this.cityController.getDx();
    }
    
    public int getCityDimensionY(){
        return this.cityController.getDy();
    }
    
    public double getCityHeight(){
        return this.cityController.getHeight();
    }
    
    public double getCityWidth(){
        return this.cityController.getWidth();
    }
    
    public void putRoad(String spriteRoad, int x, int y){
        this.cityController.putRoad(spriteRoad, x, y);
    }

}
