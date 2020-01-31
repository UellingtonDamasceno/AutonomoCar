/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.net.SocketException;
import java.util.Observable;
import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class Car extends Observable{
    
    private String sprite;
    private String cityName;
    private Point originPoint, currentPosition;
  
    private Car front, back;
    
    private Connection connection;
    
    public Car(String sprite, String cityName, Point origin){
        this.sprite = sprite;
        this.cityName = cityName;
        this.originPoint = origin;
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
    
    public void process(String command){
        
    }
    
    public void process(JSONObject command){
        
    }
    
}
