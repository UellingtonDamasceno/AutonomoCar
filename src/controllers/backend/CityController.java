package controllers.backend;

import java.awt.Point;
import model.City;
import model.Road;

/**
 *
 * @author uellington
 */
public class CityController {

    private City city;

    public int getDx() {
        return this.city.getDx();
    }

    public int getDy() {
        return this.city.getDy();
    }

    public double getHeight() {
        return this.city.getHeight();
    }

    public double getWidth() {
        return this.city.getWidth();
    }

    public void createCity(String name, int dx, int dy, double h, double w, int propotion) {
//        if ((dx % propotion == 0) && (dy % propotion == 0)) {
        this.city = new City(name, dx, dy, h, w, propotion);
//        }else{
//            System.out.println("CityController > createCity:: propoção invalida!");
//        }
    }

    public void putRoad(String sprite, int x, int y) {
        Road road = new Road(sprite, x, y);
        if (this.city.isEmpaty(x, y)) {
            this.city.addRoad(road, x, y);
        }else if(this.city.getRoad(x, y).equals(road)){
        }
    }

    public void loadRoads(String[][] infoRoads) {

    }

    public Point getOriginPoint() {
        return new Point(7, 7);
    }
}
