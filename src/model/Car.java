/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.util.Observable;

/**
 *
 * @author uellington
 */
public class Car extends Observable{
    
    private String sprite;
    private Point originPoint, currentPosition;
    
    public Car(String sprite, double ox, double oy){
        this.sprite = sprite;
        this.originPoint = new Point((int)ox, (int)oy);
    }
    
    public String getSprite(){
        return this.sprite;
    }
    
    public int getOriginX(){
        return (int) this.originPoint.x;
    }
    
    public int getOriginY(){
        return (int) this.originPoint.y;
    }
    
    
    
}
