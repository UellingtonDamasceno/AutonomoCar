package model;

import java.net.SocketException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import util.Point;
import util.Settings.Orientation;
import util.Settings.SpritesCars;

/**
 *
 * @author uellington
 */
public class Car extends Observable implements Runnable, Observer {

    private SpritesCars car;
    private String cityName;
    private Point originPoint, currentPosition;

    private Connection connection;

    private Vertex<Road> oldVertex, currentVertex, nextVertex;
    private Orientation front;
    private boolean stop;

    public Car(SpritesCars car, String cityName, Point origin) {
        this.car = car;
        this.cityName = cityName;
        this.originPoint = origin;
        this.stop = true;
        this.connection = new Connection();
    }

    public void initialize() throws SocketException {
        this.connection.initialize();
    }

    public String getSprite() {
        return car.getSmall();
    }

    public Color getColor(){
        return this.car.getColor();
    }
    
    public Point getOriginPosition() {
        return this.originPoint;
    }

    public Point getCurrentPosition() {
        return this.currentPosition;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setFront(Orientation front) {
        this.front = front;
    }

    public void setCurrentVertex(Vertex vertex) {
        this.currentVertex = vertex;
    }
    
    public void setOriginPoint(double x, double y) {
        this.setOriginPoint(new Point(x, y));
    }

    public void setOriginPoint(Point point) {
        this.originPoint = point;
    }

    public void setCurrentPosition(double x, double y) {
        this.currentPosition = new Point(x, y);
    }

    public void start() {
        if (stop) {
            stop = false;
            new Thread(this).start();
            System.out.println("Andando...");
            System.out.println("X: " + this.currentPosition.getX() + "y: " + this.currentPosition.getY());
        }
    }

    public void updatePosition(int x, int y) {
        this.currentPosition.setX(x);
        this.currentPosition.setY(y);

        this.setChanged();
        this.notifyObservers(currentPosition);

//        try {
//            this.connection.send(sprite);
//        } catch (IOException ex) {
//            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (OfflineException ex) {
//            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void run() {
        this.front = this.getNextOrientation();
        while (!stop) {
            int cx = (int) this.currentPosition.getX();
            int cy = (int) this.currentPosition.getY();
            switch (front) {
                case NORTH: {
                    this.updatePosition(cx, (cy -= 1));
                    break;
                }
                case SOUTH: {
                    this.updatePosition(cx, (cy += 1));
                    break;
                }
                case EAST: {
                    this.updatePosition((cx += 1), cy);
                    break;
                }
                case WEST: {
                    this.updatePosition((cx -= 1), cy);
                    break;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                this.stop = false;
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Orientation getNextOrientation() {
        Road currentRoad = this.currentVertex.get();
        int adjacent = this.currentVertex.degree();
        
        int random = Random.randomize(1, adjacent);
        
        Iterator<Vertex<Road>> iterator = currentVertex.iterator();
        Vertex<Road> nextVertex = currentVertex; //= NullVertex();

        for (int i = 0; i <= random; i++) {
            nextVertex = iterator.next();
        }

        this.nextVertex = nextVertex;
        return this.getOrientation(currentRoad, nextVertex.get());
    }

    private Orientation getOrientation(Road current, Road next) {
        int cx = current.getPostionX();
        int cy = current.getPostionY();
        int nx = next.getPostionX();
        int ny = next.getPostionY();

        Orientation orientation;

        if (nx > cx) {
            orientation = Orientation.EAST;
        } else if (nx < cx) {
            orientation = Orientation.WEST;
        } else if (ny > cy) {
            orientation = Orientation.SOUTH;
        } else {
            orientation = Orientation.NORTH;
        }
        return orientation;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
