/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class Point implements Serializable {

    private transient DoubleProperty px, py;
    private double x, y;
    private int sector;
    
    public Point() {
        this(0, 0, -1);
    }
    
    public Point(double x, double y){
        this(x, y, -1);
    }

    public Point(double x, double y, int sector) {
        this.px = new SimpleDoubleProperty(this, "x", x);
        this.py = new SimpleDoubleProperty(this, "y", y);
        this.x = x;
        this.y = y;
        this.sector = sector;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public DoubleProperty getDoublePropertyX() {
        return this.px;
    }

    public void setX(double x) {
        this.px.set(x);
        this.x = x;
    }

    public DoubleProperty getDoublePropertyY() {
        return this.py;
    }

    public void setY(double y) {
        this.py.set(y);
        this.y = y;
    }

    public int getSector(){
        return this.sector;
    }
    
    public void setSector(int sector){
        this.sector = sector;
    }
    
    public void load() {
        this.px = new SimpleDoubleProperty(x);
        this.py = new SimpleDoubleProperty(y);
    }

    public static double distance(Point a, Point b) {
        double diferenceX = Math.pow((b.x - a.x), 2);
        double diferenceY = Math.pow((b.y - a.y), 2);

        return (Math.sqrt((diferenceX + diferenceY)));
    }
    
    public static Point distance(Point point, Point[] points){
        Double distance = Double.MAX_VALUE;
        Point nearestPoint = points[0];
        for (Point anotherPoint : points) {
            double distanceCalculate = Point.distance(point, anotherPoint);
            if(distanceCalculate < distance){
                nearestPoint = anotherPoint;
                distance = distanceCalculate;
            }
        }
        return nearestPoint;
    }

    @Override
    public int hashCode() {
        String valueX = Double.toString(x);
        String valueY = Double.toString(y);

        valueX += (valueX + valueY).hashCode();
        valueY += (valueY + valueX).hashCode();
        
        return (valueX + valueY).hashCode();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.accumulate("x", x);
        json.accumulate("y", y);
        return json.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Point) {
            Point another = (Point) object;
            return (this.x == another.x && this.y == another.y);
        }
        return false;
    }
}
