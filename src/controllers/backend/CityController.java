package controllers.backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.City;
import model.NullRoad;
import model.Road;
import model.exceptions.EdgeExistException;
import model.exceptions.VertexEqualsException;
import model.exceptions.VertexNotExistException;
import util.Point;
import util.Settings.DefaultFile;
import util.Settings.Orientation;

/**
 *
 * @author uellington
 */
public class CityController {

    private City city;

    public void setCity(City city) {
        this.city = city;
    }

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

    public String getLocationToSave() {
        String directory = DefaultFile.CITIES_DIRECTORY.get() + this.city.getName();
        return directory + DefaultFile.DEFAULT_EXTENSION.get();
    }

    public String getLocationToSave(String cityName) {
        return DefaultFile.CITIES_DIRECTORY.get().concat(cityName);
    }

    public void createCity(String name, int dx, int dy, int h, int w, int propotion) {
//        if ((dx % propotion == 0) && (dy % propotion == 0)) {
        this.city = new City(name, dx, dy, h, w);
//        }else{
//            System.out.println("CityController > createCity:: propoção invalida!");
//        }
    }

    public Road putRoad(String sprite, int x, int y) {
        double roadHeight = this.city.calculateRoadHeight(y);
        double roadWidth = this.city.calculateRoadWidth(x);

        Road road = new Road(sprite, x, y, roadHeight, roadWidth);
        this.calculateSectorPoints(road);

        if (this.city.isEmpty(x, y) || !this.city.getRoad(x, y).equals(road)) {
            this.city.setRoad(road, x, y);

            Road top = city.getRoad(x, y - 1);
            Road buttom = city.getRoad(x, y + 1);
            Road right = city.getRoad(x + 1, y);
            Road left = city.getRoad(x - 1, y);

            this.connectRoads(road, top, Orientation.NORTH);
            this.connectRoads(road, right, Orientation.EAST);
            this.connectRoads(road, left, Orientation.WEST);
            this.connectRoads(road, buttom, Orientation.SOUTH);
        }

        return road;
    }

    private void connectRoads(Road newRoad, Road oldRoad, Orientation orientation) {
        try {
            if (!(oldRoad instanceof NullRoad)) {
                this.city.connectRoads(newRoad, oldRoad);
                switch (orientation) {
                    case NORTH: {
                        newRoad.putUp();
                        oldRoad.putDown();
                        break;
                    }
                    case SOUTH: {
                        newRoad.putDown();
                        oldRoad.putUp();
                        break;
                    }
                    case EAST: {
                        newRoad.putRight();
                        oldRoad.putLeft();
                        break;
                    }
                    case WEST: {
                        newRoad.putLeft();
                        oldRoad.putRight();
                        break;
                    }
                }
            }
        } catch (VertexEqualsException | EdgeExistException e) {
            try {
                this.city.updateRoad(newRoad, oldRoad);
            } catch (VertexNotExistException ex1) {
                Logger.getLogger(CityController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void updateSprite(String sprite, int x, int y) {
        this.city.getRoad(x, y).setSprite(sprite);
    }

    public void removeRoad(int x, int y) {
        try {
            Road road = this.city.getRoad(x, y);
            this.city.getGraph().remove(road);
            this.city.setRoad(new NullRoad(x, y), x, y);
        } catch (VertexNotExistException ex) {
            Logger.getLogger(CityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void calculateSectorPoints(Road road) {
        double coordX, coordY;

        double propotionX = this.city.getPropotionX();
        double propotionY = this.city.getPropotionY();

        double offsetX = road.getPostionX();
        double offsetY = road.getPostionY();

        double x = (propotionX * offsetX);
        double y = (propotionY * offsetY);

        Point p2 = new Point();
        Point p3 = new Point();

        coordY = ((propotionX / 4) + x) - 50;
        coordX = ((propotionY / 4) + y) - 5;
        Point p1 = new Point(coordX, coordY);

        p2.setY(coordY);
        p3.setX(coordX);

        coordY = (((3 * propotionX) / 4) + x) - 50;
        coordX = (((3 * propotionY) / 4) + y) - 5;

        p2.setX(coordX);
        p3.setY(coordY);

        Point p4 = new Point(coordX, coordY);

        road.setSectorPoint(0, p1);
        road.setSectorPoint(1, p2);
        road.setSectorPoint(2, p3);
        road.setSectorPoint(3, p4);
    }
}
