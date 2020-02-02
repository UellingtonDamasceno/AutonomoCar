package model.states;

import java.io.Serializable;
import util.RoadState;

/**
 *
 * @author uellington
 */
public class HRoadUp implements RoadState, Serializable {

    private RoadState lastState;
    
    public HRoadUp(RoadState lastState){
        this.lastState = lastState;
    }
    
    @Override
    public RoadState putUp() {
        return new TrunRightInverse(this);
    }

    @Override
    public RoadState putDown() {
        return new TrunLeft(this);
    }

    @Override
    public RoadState putLeft() {
        return new ConnectorHRoad(this);
    }

    @Override
    public RoadState putRight() {
        return new ConnectorHRoad(this);
    }

    @Override
    public RoadState removeUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new Single() : lastState;
    }

    @Override
    public RoadState removeRight() {
        return (lastState == null) ? new Single() : lastState;
    }

    @Override
    public String getType() {
        return "HRoadUp";
    }

    
}
