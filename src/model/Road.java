package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import model.states.road.Single;
import org.json.JSONObject;
import util.Point;
import util.RoadState;
import util.Settings.RoadTypes;

/**
 *
 * @author uellington
 */
public class Road implements Observer, Serializable {

    protected String sprite;

    protected RoadState state;
    protected Sector[] sectors;

    protected final int positionX, positionY;
    protected final double height, width;

    public Road(String sprite, int posX, int posY, double h, double w) {
        this.sprite = sprite;
        this.positionX = posX;
        this.positionY = posY;
        this.height = h;
        this.width = w;
        this.sectors = new Sector[4];
        this.state = new Single();
    }

    public boolean isCriticalArea() {
        return this.state.isCriticalArea();
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

    public RoadTypes getType() {
        return this.state.getType();
    }

    public int getPostionY() {
        return this.positionY;
    }

    public int getPostionX() {
        return this.positionX;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void putUp() {
        this.state = this.state.putUp();
        this.updateOrientations();
    }

    public void putDown() {
        this.state = this.state.putDown();
        this.updateOrientations();
    }

    public void putLeft() {
        this.state = this.state.putLeft();
        this.updateOrientations();
    }

    public void putRight() {
        this.state = this.state.putRight();
        this.updateOrientations();

    }

    public Sector getNearestSector(Point point) {
        Point nearestPoint = Point.distance(point, this.getSectorPoints());
        return this.sectors[nearestPoint.getSector()];
    }

    public void setSectorPoint(int position, Sector sector) {
        this.sectors[position] = sector;
    }

    public Sector[] getSectors() {
        return this.sectors;
    }

    public Sector getSector(int position) {
        return this.sectors[position];
    }

    public Point[] getSectorPoints() {
        Point[] points = new Point[sectors.length];
        for (int i = 0; i < sectors.length; i++) {
            points[i] = sectors[i].getPoint();
        }
        return points;
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

//        jsonArray = new JSONObject();
//        jsonArray.accumulate("height", this.height);
//        jsonArray.accumulate("width", this.width);
//        jsonObject.accumulate("dimension", jsonArray);
        return jsonObject.toString();
    }

    public void load() {
        for (Sector sector : sectors) {
            sector.getPoint().load();
        }
    }

    private void updateOrientations() {
        for (int i = 0; i < sectors.length; i++) {
            sectors[i].setStartOrientation(this.getType().getOrientation(i));
            sectors[i].setIsCritical(this.state.isCriticalArea());
        }
    }
}
