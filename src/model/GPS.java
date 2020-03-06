package model;

import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.exceptions.EdgeNotExistException;
import model.exceptions.VertexNotExistException;
import org.json.JSONObject;
import util.Point;
import util.Settings.Orientation;

/**
 *
 * @author Uellington Conceição
 */
public class GPS extends Observable {

    private Vertex<Sector> cVertex, nVertex;

    private Point currentPoint, interfacePoint;
    private Sector nextSector;

    private Graph<Sector, Orientation> map;
    private Orientation front;

    private int limit;

    public GPS(Car car) {
        this.addObserver(car);
    }

    public void initialize(Vertex<Sector> vertex, Point point) throws VertexNotExistException, EdgeNotExistException {

        this.currentPoint = point;
        this.cVertex = vertex;

        this.front = vertex.get().getOrientation();
        this.nVertex = this.getNextVertex(vertex);
        this.criticalZoneAlert();
        this.nextSector = nVertex.get();

        this.interfacePoint = new Point((point.getX() - 5), (point.getY() - 50));
        this.limit = 0;
    }

    public void setMap(Graph<Sector, Orientation> map) {
        this.map = map;
    }

    public void setCurrentSector(Point current) {
        this.currentPoint = current;
    }

    public Point getCurrentPoint() {
        return this.currentPoint;
    }

    public Orientation getFront() {
        return this.front;
    }

    public void updatePosition(double x, double y) {
        this.currentPoint.setX(x);
        this.currentPoint.setY(y);

        interfacePoint.setX(nextSector.getPoint().getX() - 5);
        interfacePoint.setY(nextSector.getPoint().getY() - 50);

        if (currentPoint.equals(interfacePoint)) {
            try {
                this.front = this.getNextOrientation();
            } catch (VertexNotExistException | EdgeNotExistException ex) {
                Logger.getLogger(GPS.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Orientation getNextOrientation() throws VertexNotExistException, EdgeNotExistException {
        Orientation nextOrientation;

        this.nVertex = this.getNextVertex(cVertex);

        this.criticalZoneAlert();

        Edge<Vertex<Sector>, Orientation> edge = this.map.getEdge(cVertex.get(), nVertex.get());
        nextOrientation = edge.getWeight();
        nextSector = nVertex.get();

        cVertex = nVertex;

//        System.out.println("Next sector: " + nextSector);
//        System.out.println("Next orientaiton: " + nextOrientation);
        return nextOrientation;
    }

    private Vertex<Sector> getNextVertex(Vertex<Sector> current) throws VertexNotExistException, EdgeNotExistException {
        int adjacent = current.degree();
        Vertex<Sector> nextVertex = null;
        Iterator<Vertex<Sector>> iterator = current.iterator();

        if (current.get().isCritical()) {
            if (adjacent > 1 && this.limit < 2) {
                int randomize = new java.util.Random().nextInt(2);
                nextVertex = iterator.next();
                for (int i = 0; i < randomize; i++) {
                    nextVertex = iterator.next();
                }
//                System.out.println("É critico, tem dois caminho e já sorteou: " + limit + " vezes");
            } else if (this.limit == 2) {
//                System.out.println("É critico, já sorteou o máximo e deve pegar: ");
                nextVertex = iterator.next();
//                System.out.println("Vertex tirado: " + nextVertex);
                nextVertex = current.getAnother(nextVertex);
//                System.out.println("Next vertex: " + nextVertex);
                Orientation nextOrientation = this.getNextOrientation(current.get(), nextVertex.get());
                nextVertex = this.map.getVertex(current.get(), nextOrientation);
                this.limit = 0;
            } else {
                nextVertex = current.iterator().next();
//                System.out.println("É critico, mas só tem apenas um caminho");
            }
            this.limit++;
//            System.out.println("Limit add: " + limit);

        } else {
            if (adjacent == 1) {
                nextVertex = current.iterator().next();
//                System.out.println("Não é critico e só tem um caminho.");
            } else {
                nextVertex = map.getVertex(current.get(), current.get().getOrientation());
//                System.out.println("Não é critico, mas tem dois caminhos.");
            }
            this.limit = 0;
//            System.out.println("Limit zerado: " + limit);
        }
        return nextVertex;
    }

    public Orientation getNextOrientation(Sector current, Sector next) throws EdgeNotExistException {
        Edge<Vertex<Sector>, Orientation> edge;
//        System.out.println("DE: " + current);
//        System.out.println("PARA: " + next);
        edge = this.map.getEdge(current, next);
        return edge.getWeight();
    }

    public void criticalZoneAlert() {
        if (!(this.cVertex.get().isCritical()) && nVertex.get().isCritical()) {
            JSONObject internalMessage = new JSONObject();
            internalMessage.accumulate("command", "NEXT/IS/CRITICAL");
            internalMessage.accumulate("point", nVertex.get().getPoint().toString());
            this.setChanged();
            this.notifyObservers(internalMessage);
        }
    }

    public void setPosition(Point point) {
        this.currentPoint.getDoublePropertyX().set(point.getX());
        this.currentPoint.getDoublePropertyY().set(point.getY());
    }
}
