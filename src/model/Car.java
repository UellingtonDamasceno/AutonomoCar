package model;

import middleware.Connection;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import model.exceptions.EdgeNotExistException;
import model.exceptions.OfflineException;
import model.exceptions.VertexNotExistException;
import org.json.JSONObject;
import util.Point;
import static util.Settings.Orientation.EAST;
import static util.Settings.Orientation.NORTH;
import static util.Settings.Orientation.SOUTH;
import static util.Settings.Orientation.WEST;
import util.Settings.SpritesCars;

/**
 *
 * @author uellington
 */
public class Car implements Runnable, Observer {

    private boolean initializade;
    private boolean online;

    private final SpritesCars sprite;

    private GPS gps;
    private Engine engine;
    private Sensor sersor;

    public Car(SpritesCars sprite) {
        this.sprite = sprite;

        this.gps = new GPS(this);
        this.engine = new Engine(sprite.getMaximumVelocity());
        this.sersor = new Sensor();
        this.initializade = false;
        this.online = false;
    }

    public void initialize(Vertex<Sector> vertex, Point point) throws VertexNotExistException,
            EdgeNotExistException,
            SocketException,
            UnknownHostException,
            IOException,
            OfflineException {

        if (!initializade) {
            this.gps.initialize(vertex, point);
            this.initializade = true;
            Connection.getInstance().setOnline(true);
            JSONObject message = new JSONObject();
            message.accumulate("command", "POST/CAR");
            message.accumulate("type", this.sprite.getStringColor());
            message.accumulate("positionX", this.gps.getCurrentPoint().getX());
            message.accumulate("positionY", this.gps.getCurrentPoint().getY());
            message.accumulate("id", this.getId());
            Connection.getInstance().send(message.toString());
        } else {
            System.out.println("Carro já inicializado");
        }
    }

    public void addObserver(Observer observer) {
        this.gps.addObserver(observer);
    }

    public void setMap(Graph graph) {
        this.gps.setMap(graph);
    }
    
    public boolean isFront(Point point){
       return this.sersor.hasCarInFront(this.gps.getFront(), this.gps.getCurrentPoint(), point);
    }

    public void setOrigin(Point point) {
        this.gps.setCurrentSector(point);
    }

    public String getSprite() {
        return sprite.getSmall();
    }

    public Color getColor() {
        return this.sprite.getColor();
    }

    public Point getCurrentPosition() {
        return this.gps.getCurrentPoint();
    }

    public String getId() {
        return Connection.getInstance().getIp();
    }

    public void setPosition(Point point) {
        this.gps.setPosition(point);
    }

    public void setStop(boolean stop) {
        try {
            JSONObject message = new JSONObject();
            message.accumulate("command", "DISCONNECT");
            message.accumulate("id", this.getId());
            Connection.getInstance().send(message.toString());

            this.engine.setMaximumSpeed(this.sprite.getMaximumVelocity() * 2);
            this.initializade = false;
            this.online = stop;
            Connection.getInstance().setOnline(false);
        } catch (IOException | OfflineException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() throws IOException, OfflineException {
        if (!online) {
//            JSONObject message = new JSONObject();
//            message.accumulate("command", "START");
//            message.accumulate("id", this.getId());
//            Connection.getInstance().send(message.toString());

            new Thread(this).start();
            this.online = true;
        }
    }

    public void updatePosition(double x, double y) {
        try {
            this.gps.updatePosition(x, y);

            JSONObject message = new JSONObject();
            message.accumulate("command", "UPDATE/POSITION");
            message.accumulate("id", this.getId());
            message.accumulate("positionX", x);
            message.accumulate("positionY", y);
            message.accumulate("velocity", this.engine.getVelocity());
            message.accumulate("orientation", this.gps.getFront());

            Connection.getInstance().send(message.toString());
        } catch (IOException | OfflineException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        double cx;
        double cy;
        while (online) {
            cx = this.gps.getCurrentPoint().getX();
            cy = this.gps.getCurrentPoint().getY();
            this.gps.updatePosition(cx, cy);
            switch (this.gps.getFront()) {
                case NORTH: {
                    this.updatePosition(cx, (cy - 1));
                    break;
                }
                case SOUTH: {
                    this.updatePosition(cx, (cy + 1));
                    break;
                }
                case EAST: {
                    this.updatePosition((cx + 1), cy);
                    break;
                }
                case WEST: {
                    this.updatePosition((cx - 1), cy);
                    break;
                }
            }
            try {
                Thread.sleep(this.engine.execute());
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        this.setStop(false);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o1 instanceof JSONObject) {
//            System.out.println("Recebeu uma nova atualização!");
            this.process((JSONObject) o1);
        } else {
            System.err.println("Aguardava um JSONObject e recebeu um: " + o1);
            System.out.println("Obsrvable: " + o);
        }
    }

    private void process(JSONObject message) {
        String command = message.getString("command");
//        System.out.println(message.toString());
        switch (command) {
            case "NEXT/IS/CRITICAL": {
                JSONObject jsonPoint = new JSONObject(message.getString("point"));
                break;
            }
        }
    }

    public double calculateDistance(Point point) {
        return this.sersor.getFrontDistance(this.gps.getCurrentPoint(), point);
    }

    public void setMaxVelocity(int velocity) {
        this.engine.setMaximumSpeed(velocity);
    }

    public void restoreVelocity() {
        this.engine.forceUp();
    }
}
