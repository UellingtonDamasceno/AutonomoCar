package model;

import java.io.Serializable;
import org.json.JSONObject;
import util.Point;
import util.Settings.Orientation;

/**
 *
 * @author Uellington Conceição
 */
public class Sector implements Serializable {

    private final int sector;
    private final Point point;
    private boolean isCritical;
    private Orientation startOrientation;

    public Sector(Point point) {
        this(-1, point);
    }

    public Sector(int sector, Point point) {
        this.sector = sector;
        this.point = point;
    }

    public int getSector() {
        return this.sector;
    }

    public Point getPoint() {
        return this.point;
    }

    public Orientation getOrientation() {
        return this.startOrientation;
    }

    public void setStartOrientation(Orientation orientation) {
        this.startOrientation = orientation;
    }

    public void setIsCritical(boolean isCritical) {
        this.isCritical = isCritical;
    }
    
    public boolean isCritical(){
        return this.isCritical;
    }

    public boolean isOpposite(Sector another) {
        return this.isOpposite(another.getOrientation());
    }

    public boolean isOpposite(Orientation another) {
        return this.startOrientation.getOppositeDirection().equals(another.getDirection());
    }

    @Override
    public int hashCode() {
        return this.point.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sector) {
            Sector another = (Sector) obj;
            return this.hashCode() == another.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();

        json.accumulate("sectorNumber", sector);
        json.accumulate("point", point);
        json.put("orientation", this.startOrientation.getDirection());

        return json.toString();
    }

}
