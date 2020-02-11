package controllers.backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.City;
import model.NullRoad;
import model.Road;
import model.Transition;
import model.Vertex;
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
            
            Transition northSouth = new Transition(Orientation.NORTH, top.getSector(3));
            Transition southNorth = new Transition(Orientation.SOUTH, road.getSector(0));
            Transition eastWest = new Transition(Orientation.EAST, road.getSector(2));
            Transition westEast = new Transition(Orientation.WEST, road.getSector(1));
            
            Transition iEastWest = new Transition(Orientation.WEST, left.getSector(1));
            Transition iWestEast = new Transition(Orientation.EAST, road.getSector(2));
            Transition iSouthNorth = new Transition(Orientation.SOUTH, buttom.getSector(0));
            Transition iNorthSouth = new Transition(Orientation.NORTH, road.getSector(3));
            
            this.connectRoads(road, top, northSouth);
            this.connectRoads(top, road, southNorth);
            
            this.connectRoads(road, right, eastWest);
            this.connectRoads(right, road, westEast);
            
            this.connectRoads(road, left, iEastWest);
            this.connectRoads(left, road, iWestEast);
            
            this.connectRoads(road, buttom, iSouthNorth);
            this.connectRoads(buttom, road, iNorthSouth);
        }

        return road;
    }

    private void connectRoads(Road de, Road para, Transition transition) {
        try {
            if (!(para instanceof NullRoad) && !(de instanceof NullRoad)) {
                this.city.connectRoads(de, para, transition);
                switch (transition.getOrientation()) {
                    case NORTH: {
                        de.putUp();
                        break;
                    }
                    case SOUTH: {
                        de.putDown();
                        break;
                    }
                    case EAST: {
                        de.putRight();
                        break;
                    }
                    case WEST: {
                        de.putLeft();
                        break;
                    }
                }
            }
        } catch (VertexEqualsException | EdgeExistException e) {
            System.out.println("Deu erro!");
            try {
                this.city.updateRoad(de, para);
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
        double coordX, coordY; //Cordenada x e y do ponto. 

        double propotionX = this.city.getPropotionX(); // Altura x de uma rua
        double propotionY = this.city.getPropotionY(); // Altura y de uma rua

        double offsetX = road.getPostionX();//Deslocamento em x da posição da rua.  
        double offsetY = road.getPostionY();//Deslocamento em y da posição da rua. 

        double x = (propotionX * offsetX);
        double y = (propotionY * offsetY);

        coordX = ((propotionX / 4) + x) + 5;
        coordY = ((propotionY / 4) + y) + 10;
        Point p1 = new Point(coordX, coordY);

        coordX = ((3 * (propotionX / 4)) + x) - 10;
        coordY = ((propotionY / 4) + y) + 10;
        Point p2 = new Point(coordX, coordY);

        coordX = ((propotionX / 4) + x) + 5;
        coordY = ((3 * (propotionY / 4)) + y) - 13;
        Point p3 = new Point(coordX, coordY);

    
        coordX = (((3 * propotionX) / 4) + x) - 10;
        coordY = (((3 * propotionY) / 4) + y) - 13;
        Point p4 = new Point(coordX, coordY);

        road.setSectorPoint(0, p1);
        road.setSectorPoint(1, p2);
        road.setSectorPoint(2, p3);
        road.setSectorPoint(3, p4);
    }
}
