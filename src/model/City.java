package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import model.exceptions.VertexNotExistException;
import org.json.JSONObject;
import util.Point;
import util.Settings.Orientation;

/**
 *
 * @author uellington
 */
public class City implements Serializable, Observer {

    private final String name;

    private final int dx, dy;
    private final double height, width;
    private final double propotionX, propotionY;
    private Road[][] city;
    private Graph<Sector, Orientation> graph;

    public City(String name, int dx, int dy, double h, double w) {
        this.name = name;

        this.dx = dx;
        this.dy = dy;

        this.height = h;
        this.width = w;

        this.propotionX = (w / dx);
        this.propotionY = (h / dy);

        this.graph = new Graph<>();
        this.city = new Road[dx][dy];
        this.initialize(dx, dy);
    }

    private void initialize(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.city[i][j] = new NullRoad(i, j);
            }
        }
    }

    public Graph getGraph() {
        return this.graph;
    }

    public String getName() {
        return this.name;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getPropotionX() {
        return this.propotionX;
    }

    public double getPropotionY() {
        return this.propotionY;
    }

    public void setRoad(Road road, int x, int y) {
        this.city[x][y] = road;
    }

    public Road getRoad(int x, int y) {
        return (x >= dx || x < 0 || y >= dy || y < 0) ? new NullRoad(x, y) : this.city[x][y];
    }
    
    public boolean isEmpty(int x, int y) {
        return this.city[x][y] instanceof NullRoad;
    }

    public boolean isRoad(int x, int y) {
        return !(this.getRoad(x, y) instanceof NullRoad);
    }

    public Road getRoad(Point point){
        int x = (int) ((point.getX()) / this.width);
        int y = (int) ((point.getY()) / this.height);
        
        System.out.println("O ponto: "+ point);
        System.out.println("Pertence a rua de coord: X " + x + " Y: "+ y);
        return this.getRoad(x, y);
    }
    
    public double calculateRoadHeight(int posY) {
        return this.propotionY * (posY) + 1;
    }

    public double calculateRoadWidth(int posX) {
        return this.propotionX * (posX + 1);
    }

    public void updateRoad(Sector a, Sector b) throws VertexNotExistException {
        this.graph.update(b, a);
    }

    public int numNullRoad() {
        int count = 0;
        for (int i = 0; i < dx; i++) {
            for (int j = 0; j < dy; j++) {
                if (city[i][j] instanceof NullRoad) {
                    count++;
                }
            }
        }
        return count;
    }

    public void showRoads() {
        this.graph.show();
    }

    @Override
    public String toString() {
        JSONObject city = new JSONObject();

        city.accumulate("name", name);
        city.accumulate("dx", dx);
        city.accumulate("dy", dy);
        city.accumulate("height", height);
        city.accumulate("width", width);

        return city.toString();
    }

    public void loadPoints() {
        for (int i = 0; i < dx; i++) {
            for (int j = 0; j < dy; j++) {
                if (!(city[i][j] instanceof NullRoad)) {
                    city[i][j].load();
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        
    }
}
