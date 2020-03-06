package util;

import util.Settings.RoadTypes;

/**
 *
 * @author uellington
 */
public interface RoadState {
    
    public RoadTypes getType();
    
    public RoadState putUp();
    public RoadState putDown();
    public RoadState putLeft();
    public RoadState putRight();

    public RoadState removeUp();
    public RoadState removeDown();
    public RoadState removeLeft();
    public RoadState removeRight();
    
    public boolean isCriticalArea();
}
