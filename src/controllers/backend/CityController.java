package controllers.backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.City;
import model.NullRoad;
import model.Road;
import model.Sector;
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
        this.city = new City(name, dx, dy, h, w);
    }

    public void connectPoints(Road origin, Road destiny, int os, int sdq, Orientation orientation) {
        if (!(origin instanceof NullRoad) && !(destiny instanceof NullRoad)) {
            try {
                this.city.getGraph().put(origin.getSector(os), destiny.getSector(sdq), orientation);
            } catch (VertexEqualsException ex) {
                Logger.getLogger(CityController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EdgeExistException ex) {
                Logger.getLogger(CityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Road putRoad(String sprite, int x, int y) {
        double roadHeight = this.city.calculateRoadHeight(y);
        double roadWidth = this.city.calculateRoadWidth(x);

        Road road = new Road(sprite, x, y, roadHeight, roadWidth);
        this.calculateSectorPoints(road);

        if (this.city.isEmpty(x, y) || !this.city.getRoad(x, y).equals(road)) {
            this.city.setRoad(road, x, y);

            Road top = city.getRoad(x, y - 1);
            Road below = city.getRoad(x, y + 1);
            Road right = city.getRoad(x + 1, y);
            Road left = city.getRoad(x - 1, y);

            this.updateState(road, top, Orientation.NORTH);
            this.connectPoints(road, top, 1, 3, Orientation.NORTH);

            this.updateState(top, road, Orientation.SOUTH);
            this.connectPoints(top, road, 2, 0, Orientation.SOUTH);

            this.updateState(road, right, Orientation.EAST);
            this.connectPoints(road, right, 3, 2, Orientation.EAST);

            this.updateState(right, road, Orientation.WEST);
            this.connectPoints(right, road, 0, 1, Orientation.WEST);

            this.updateState(road, left, Orientation.WEST);
            this.connectPoints(road, left, 0, 1, Orientation.WEST);
            this.connectPoints(road, road, 1, 0, Orientation.WEST);

            this.updateState(left, road, Orientation.EAST);
            this.connectPoints(left, road, 3, 2, Orientation.EAST);
            this.connectPoints(road, road, 2, 3, Orientation.EAST);

            this.updateState(road, below, Orientation.SOUTH);
            this.connectPoints(road, below, 2, 0, Orientation.SOUTH);
            this.connectPoints(road, road, 0, 2, Orientation.SOUTH);

            this.updateState(below, road, Orientation.NORTH);
            this.connectPoints(below, road, 1, 3, Orientation.NORTH);
            this.connectPoints(road, road, 3, 1, Orientation.NORTH);
            
        }

        return road;
    }

    private void updateState(Road origin, Road destiny, Orientation orientation) {
        if (!(destiny instanceof NullRoad) && !(origin instanceof NullRoad)) {
            switch (orientation) {
                case NORTH: {
                    origin.putUp();
                    break;
                }
                case SOUTH: {
                    origin.putDown();
                    break;
                }
                case EAST: {
                    origin.putRight();
                    break;
                }
                case WEST: {
                    origin.putLeft();
                    break;
                }
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
        double coordX, coordY; //Cordenada x e y do ponto. 

        double propotionX = this.city.getPropotionX(); // Altura x de uma rua
        double propotionY = this.city.getPropotionY(); // Altura y de uma rua

        double offsetX = road.getPostionX();//Deslocamento em x da posição da rua.  
        double offsetY = road.getPostionY();//Deslocamento em y da posição da rua. 

        double x = (propotionX * offsetX);
        double y = (propotionY * offsetY);

        coordX = ((propotionX / 4) + x) + 10;
        coordY = ((propotionY / 4) + y) + 10;
        Point p1 = new Point(coordX, coordY, 0);

        coordX = ((3 * (propotionX / 4)) + x) - 10;
        coordY = ((propotionY / 4) + y) + 10;
        Point p2 = new Point(coordX, coordY, 1);

        coordX = ((propotionX / 4) + x) + 10;
        coordY = ((3 * (propotionY / 4)) + y) - 10;
        Point p3 = new Point(coordX, coordY, 2);

        coordX = (((3 * propotionX) / 4) + x) - 10;
        coordY = (((3 * propotionY) / 4) + y) - 10;
        Point p4 = new Point(coordX, coordY, 3);

        road.setSectorPoint(0, new Sector(0, p1));
        road.setSectorPoint(1, new Sector(1, p2));
        road.setSectorPoint(2, new Sector(2, p3));
        road.setSectorPoint(3, new Sector(3, p4));
    }
}
