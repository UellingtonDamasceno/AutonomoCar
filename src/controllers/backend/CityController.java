package controllers.backend;

import model.City;

/**
 *
 * @author uellington
 */
public class CityController {
    private City city;
   
    
    public int getDx(){
        return this.city.getDx();
    }
    
    public int getDy(){
        return this.city.getDy();
    }
    
    public void createCity(int dx, int dy){
        this.city = new City(dx, dy);
    }
    
    public void loadRoads(String[][] infoRoads){
        
    }
    
}
