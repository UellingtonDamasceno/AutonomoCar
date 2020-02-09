/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author uellington
 */
public class Point implements Serializable{

    private double x, y;

    public Point() {
        this(0, 0);
    }
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
    public static double distance(Point a, Point b){
        double diferenceX = Math.pow((b.getX() - a.getX()), 2);
        double diferenceY = Math.pow((b.getY() - a.getY()), 2);
        
        return (Math.sqrt((diferenceX + diferenceY)));
    }
    
    @Override
    public String toString(){
        return "X: "+ x + " Y: "+ y;
    }
}
