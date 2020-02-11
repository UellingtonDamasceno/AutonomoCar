package model;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import util.Point;
import util.Settings.Orientation;
import static util.Settings.Orientation.EAST;
import static util.Settings.Orientation.NORTH;
import static util.Settings.Orientation.SOUTH;
import static util.Settings.Orientation.WEST;
import util.Settings.SpritesCars;

/**
 *
 * @author uellington
 */
public class Car implements Runnable, Observer{

    private SpritesCars car;
    private final String cityName;

    private final Connection connection; // pode ser que dê erro o fato dela ser final!!! Faça o teste!!

    private GPS gps;

    private boolean stop;

    public Car(SpritesCars car, String cityName) {
        this.car = car;
        this.cityName = cityName;
        this.stop = true;
        this.connection = new Connection();
        this.gps = new GPS(this);
        this.addObserver(this);
    }

    public void initialize() {
//        this.connection.initialize();
        this.gps.loadInitialSettings();
    }

    public void addObserver(Observer observer) {
        this.gps.addObserver(observer);
    }

    public void setOriginVertex(Vertex vertex) {
        this.gps.setCurrentVertex(vertex);
    }

    public void setMap(Graph graph) {
        this.gps.setMap(graph);
    }

    public void setOriginPoint(Point spawnPoint) {
        this.gps.setCurrentPosition(spawnPoint);
    }

    public String getSprite() {
        return car.getSmall();
    }

    public Color getColor() {
        return this.car.getColor();
    }

    public Point getCurrentPosition() {
        return this.gps.getCurrentPosition();
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void start() {
        if (stop) {
            stop = false;
            new Thread(this).start();
        }
    }

    public void updatePosition(double x, double y) {
        this.gps.updatePosition(x, y);

//        try {
//            this.connection.send(sprite);
//        } catch (IOException ex) {
//            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (OfflineException ex) {
//            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void up() {
        double cx = this.gps.getCurrentPosition().getX().get();
        double cy = this.gps.getCurrentPosition().getY().get();
        this.updatePosition(cx, (cy -= 1));
    }

    private void down() {
        double cx = this.gps.getCurrentPosition().getX().get();
        double cy = this.gps.getCurrentPosition().getY().get();
        this.updatePosition(cx, (cy += 1));

    }

    private void trunRight() {
        double cx = this.gps.getCurrentPosition().getX().get();
        double cy = this.gps.getCurrentPosition().getY().get();
        this.updatePosition((cx += 1), cy);
    }

    private void trunLeft() {
        double cx = this.gps.getCurrentPosition().getX().get();
        double cy = this.gps.getCurrentPosition().getY().get();
        this.updatePosition((cx -= 1), cy);
    }

    @Override
    public void run() {
        Orientation front = this.gps.getFront();
        Point nextPoint, currentPoint;
        while (!stop) {
            switch (front) {
                case NORTH: {
                    this.up();
                    break;
                }
                case SOUTH: {
                    this.down();
                    break;
                }
                case EAST: {
                    this.trunRight();
                    break;
                }
                case WEST: {
                    this.trunLeft();
                    break;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                this.stop = false;
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
            nextPoint = this.gps.getNextPosition();
            currentPoint = this.getCurrentPosition();
            front = this.gps.getOrientation(currentPoint, nextPoint);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        
    }
}
