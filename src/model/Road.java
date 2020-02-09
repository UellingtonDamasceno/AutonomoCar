package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import model.states.Single;
import org.json.JSONObject;
import util.Point;
import util.RoadState;

/**
 *
 * @author uellington
 */
public class Road implements Observer, Serializable {

    private String sprite;

    private RoadState state;

    private int positionX, positionY;
    private double height, width;
    private Point[] sectorPoints;

    public Road(String sprite, int posX, int posY, double h, double w) {
        this.sprite = sprite;
        this.positionX = posX;
        this.positionY = posY;
        this.height = h;
        this.width = w;
        this.state = new Single();
        this.calculateSectorPosition();
        this.sectorPoints = new Point[4];
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public String getSprite() {
        return this.sprite;
    }

    public String getType() {
        return this.state.getType();
    }

    public int getPostionY() {
        return this.positionX;
    }

    public int getPostionX() {
        return this.positionY;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    private void calculateSectorPosition() {
        if (!(this instanceof NullRoad)) {

        }
    }

    public void putUp() {
        this.state = this.state.putUp();
    }

    public void putDown() {
        this.state = this.state.putDown();
    }

    public void putLeft() {
        this.state = this.state.putLeft();
    }

    public void putRight() {
        this.state = this.state.putRight();
    }

    public void removeUp() {
        this.state = this.state.removeUp();
    }

    public void removeDown() {
        this.state = this.state.removeDown();
    }

    public void removeLeft() {
        this.state = this.state.removeLeft();
    }

    public void removeRight() {
        this.state = this.state.removeRight();
    }

    public void setSectorPoint(Point[] points){
        this.sectorPoints = points;
    }
    
    public void setSectorPoint(int position, Point point) {
        this.sectorPoints[position] = point;
    }

    public Point[] getSectors() {
        return this.sectorPoints;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public int hashCode() {
        String value = Double.toString(this.height);
        value = Double.toString(this.width) + value;
        value = Integer.toString(this.positionX) + value;
        value = value + Integer.toString(this.positionY) + value;
        return value.hashCode();

    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Road) {
            Road another = (Road) object;
            return (this.sprite.equals(another.sprite)
                    && this.hashCode() == another.hashCode());
        }
        return false;
    }

    public String toString() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonArray = new JSONObject();

//        jsonObject.accumulate("Sprite", sprite.substring(sprite.lastIndexOf("/")));
//        jsonObject.accumulate("sprite", this.sprite);
        //jsonObject.accumulate("roadName", this.name);
        jsonArray.accumulate("x", this.positionX);
        jsonArray.accumulate("y", this.positionY);
        jsonObject.accumulate("position", jsonArray);

        jsonArray = new JSONObject();
        jsonArray.accumulate("height", this.height);
        jsonArray.accumulate("width", this.width);
        jsonObject.accumulate("dimension", jsonArray);
        return jsonObject.toString();
    }

}
