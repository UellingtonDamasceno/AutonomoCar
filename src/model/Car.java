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

/**
 *
 * @author uellington
 */
public class Car extends Observable{
    
    private String sprite;
    private Point originPoint, currentPosition;
    
    private Car front, back;
    
    private Connection connection;
    
    public Car(String sprite, double ox, double oy){
        this.sprite = sprite;
        this.originPoint = new Point((int)ox, (int)oy);
        this.connection = new Connection();
    }
    
    public String getSprite(){
        return this.sprite;
    }
    
    public Point getOriginPoint(){
        return this.originPoint;
    }
    
    public void initialize() throws SocketException{
        this.connection.initialize();
    }
    
}
