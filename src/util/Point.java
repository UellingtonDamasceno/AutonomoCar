/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author uellington
 */
public class Point implements Serializable {

    private transient DoubleProperty px, py;
    private double x, y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        this.px = new SimpleDoubleProperty(this, "x", x);
        this.py = new SimpleDoubleProperty(this, "y", y);
        this.x = x;
        this.y = y;
    }

    public DoubleProperty getX() {
        return this.px;
    }

    public void setX(double x) {
        this.px.set(x);
        this.x = x;
    }

    public DoubleProperty getY() {
        return this.py;
    }

    public void setY(double y) {
        this.py.set(y);
        this.y = y;
    }

    public void load() {
        this.px = new SimpleDoubleProperty(x);
        this.py = new SimpleDoubleProperty(y);
    }

    public static double distance(Point a, Point b) {
        double diferenceX = Math.pow((b.px.get() - a.px.get()), 2);
        double diferenceY = Math.pow((b.py.get() - a.py.get()), 2);

        return (Math.sqrt((diferenceX + diferenceY)));
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y;
    }
    
    @Override
    public boolean equals(Object object){
        if(object instanceof Point){
            Point another = (Point) object;
            return (this.x == another.x && this.y == another.y);
        }
        return false;
    }
}
