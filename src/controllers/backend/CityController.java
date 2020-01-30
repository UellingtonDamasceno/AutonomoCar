package controllers.backend;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.City;
import model.NullRoad;
import model.Road;
import model.exceptions.EdgeExistException;
import model.exceptions.VertexEqualsException;
import model.exceptions.VertexNotExistException;

/**
 *
 * @author uellington
 */
public class CityController {

    private City city;

    public City getCity() {
        return this.city;
    }
    
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

    public Road putRoad(String sprite, int x, int y) {
        int roadHeight = this.city.calculateRoadHeight(y);
        int roadWidth = this.city.calculateRoadWidth(x);

        Road road = new Road(sprite, x, y, roadHeight, roadWidth);

        if (this.city.isEmpty(x, y) || !this.city.getRoad(x, y).equals(road)) {
            this.city.addRoad(road, x, y);

            Road top = city.getRoad(x, y - 1);
            Road buttom = city.getRoad(x, y + 1);
            Road right = city.getRoad(x + 1, y);
            Road left = city.getRoad(x - 1, y);

            this.connectRoads(road, top);
            this.connectRoads(road, right);
            this.connectRoads(road, left);
            this.connectRoads(road, buttom);
        }
        
        return road;
    }

    private void connectRoads(Road a, Road b) {
        boolean error = false;
        try {
            if (!(b instanceof NullRoad)) {
                this.city.connectRoads(a, b);
            }
        } catch (VertexEqualsException e) {
            error = true;
        } catch (EdgeExistException ex) {
            //System.out.println("Edge Exist");
            error = true;
        }

        if (error) {
            try {
                this.city.updateRoad(a, b);
                //this.city.showRoads();
            } catch (VertexNotExistException ex1) {
                Logger.getLogger(CityController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void updateSprite(String sprite, int x, int y) {
        this.city.getRoad(x, y).setSprite(sprite);
    }

}
