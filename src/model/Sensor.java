package model;

import util.Point;
import util.Settings.Orientation;

/**
 *
 * @author Uellington Conceição
 */
public class Sensor {

    private boolean walk;

    public boolean canWalk() {
        return this.walk;
    }

    public boolean hasCarInFront(Orientation orientation, Point origin, Point another) {
        switch (orientation) {
            case WEST:
                return ((origin.getY() == another.getY()) && (origin.getX() > another.getX()));
            case EAST: {
                return ((origin.getY() == another.getY()) && (origin.getX() < another.getX()));
            }
            case NORTH:
                return ((origin.getY() > another.getY()) && (origin.getX() == another.getX()));
            case SOUTH: {
                return ((origin.getY() < another.getY()) && (origin.getX() == another.getX()));
            }
            default: {
                System.out.println("Merda para caraio!");
                return false;
            }
        }
    }

    public double getFrontDistance(Point origin, Point another) {
        return Point.distance(origin, another);
    }

}
