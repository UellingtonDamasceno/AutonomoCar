package facade;

import controllers.backend.CarController;
import controllers.backend.CityController;
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
        this.cityController.createCity(10, 10);
    }
    
    public void selectCar(SpritesCars selectedCar){
        
//        this.carController.setCar(car);
    }
    
    public int getCityDimensionX(){
        return this.cityController.getDx();
    }
    
    public int getCityDimensionY(){
        return this.cityController.getDy();
    }
}
