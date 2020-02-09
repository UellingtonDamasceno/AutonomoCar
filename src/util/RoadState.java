package util;

/**
 *
 * @author uellington
 */
public interface RoadState {
    
    public String getType();
    
    public RoadState putUp();
    public RoadState putDown();
    public RoadState putLeft();
    public RoadState putRight();

    public RoadState removeUp();
    public RoadState removeDown();
    public RoadState removeLeft();
    public RoadState removeRight();
    
//    public Iterator<Double> getRight(Road road);
//    public Iterator<Double> getLeft(Road road);
}
