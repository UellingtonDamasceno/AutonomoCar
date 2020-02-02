/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.io.IOException;
import java.net.SocketException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.exceptions.OfflineException;
import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class Car extends Observable {

    private String sprite;
    private String cityName;
    private Point originPoint, currentPosition;

    private Connection connection;

    public Car(String sprite, String cityName, Point origin) {
        this.sprite = sprite;
        this.cityName = cityName;
        this.originPoint = origin;
        this.connection = new Connection();
    }

    public void initialize() throws SocketException {
        this.connection.initialize();
    }

    public String getSprite() {
        return this.sprite;
    }

    public Point getOriginPosition() {
        return this.originPoint;
    }
    
    public Point getCurrentPosition(){
        return this.currentPosition;
    }
    
    public void setOriginPoint(double x, double y){
        this.setOriginPoint(new Point((int)x, (int)y));
    }
    
    public void setOriginPoint(Point point){
        this.originPoint = point;
    }
    
    public void updatePosition(int x, int y){
        this.currentPosition.translate(x, y);
        try {
            this.connection.send(sprite);
        } catch (IOException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OfflineException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void process(String command) {

    }

    public void process(JSONObject command) {

    }

}
