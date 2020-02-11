package model;

import java.util.Iterator;
import java.util.Observable;
import model.exceptions.VertexNotExistException;
import util.Point;
import util.Random;
import util.Settings.Orientation;

/**
 *
 * @author Uellington Conceição
 */
public class GPS extends Observable {

    private Vertex<Road> currentVertex;

    private Orientation currentOrientation, nextOrientation;
    private Point currentPosition, nextPoint;
    
    private Road currentRoad;
    
    private Graph<Road, Transition> map;

    private Orientation front;

    private Car car;

    public GPS(Car car) {
        this.car = car;
    }

    public void loadInitialSettings() {
        try {
            Road nextRoad = this.getNextRoad(currentVertex);

            Edge<Vertex<Road>, Transition> edge = this.map.getEdge(this.currentVertex.get(), nextRoad);

            currentVertex = this.map.getVertex(nextRoad);
            this.front = edge.getWeight().getOrientation();

            this.nextPoint = edge.getWeight().getPoint();

        } catch (VertexNotExistException ex) {
            System.err.println("Não era para isso acontecer, olhar loadInitialSettings");
        }
    }

    public void setMap(Graph<Road, Transition> map) {
        this.map = map;
    }

    public void setCurrentVertex(Vertex vertex) {
        this.currentVertex = vertex;
    }

    public void setCurrentPosition(Point origin) {
        this.currentPosition = origin;
    }

    public Point getCurrentPosition() {
        return this.currentPosition;
    }

    public Point getNextPosition() {
        return this.nextPoint;
    }
    
    public Orientation getFront() {
        return this.front;
    }

    private Road getNextRoad(Vertex<Road> current) {
        int adjacent = current.degree();
        Iterator<Vertex<Road>> iterator = current.iterator();
        Vertex<Road> vertex = null;
        int random = Random.randomize(0, adjacent);
        for (int i = 0; i <= random; i++) {
            vertex = iterator.next();
        }
        return vertex.get();
    }

    public void updatePosition(double x, double y) {
        this.currentPosition.setX(x);
        this.currentPosition.setY(y);
        this.setChanged();
        this.notifyObservers(this.currentPosition);
    }

    public Orientation getOrientation(Point current, Point next) {
        double cx = current.getX().get();
        double cy = current.getY().get();
        double nx = next.getX().get();
        double ny = next.getY().get();

        Orientation orientation;

        if (nx > cx) {
            orientation = Orientation.EAST;
        } else if (nx < cx) {
            orientation = Orientation.WEST;
        } else if (ny > cy) {
            orientation = Orientation.SOUTH;
        } else {
            orientation = Orientation.NORTH;
        }
        return orientation;
    }

}
