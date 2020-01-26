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

    public void createCity(String name, int dx, int dy, int h, int w, int propotion) {
//        if ((dx % propotion == 0) && (dy % propotion == 0)) {
        this.city = new City(name, dx, dy, h, w);
//        }else{
//            System.out.println("CityController > createCity:: propoção invalida!");
//        }
    }

    public void putRoad(String sprite, int x, int y) {
        int roadHeight = this.city.calculateRoadHeight(y);
        int roadWidth = this.city.calculateRoadWidth(x);

        Road road = new Road(sprite, x, y, roadHeight, roadWidth);
        if (this.city.isEmpaty(x, y) || !this.city.getRoad(x, y).equals(road)) {
            this.city.addRoad(road, x, y);
            System.out.println(road);
        }
    }

    public void loadRoads(String[][] infoRoads) {

    }

    public Point getOriginPoint() {
        return new Point(7, 7);
    }
}
