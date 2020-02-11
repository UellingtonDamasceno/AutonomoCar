package model.states;

import java.io.Serializable;
import util.RoadState;

/**
 *
 * @author uellington
 */
public class VRoadUp implements RoadState, Serializable {

    private RoadState lastState;

    protected VRoadUp(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadState putUp() {
        return new ConnectorVRoad(this);
    }

    @Override
    public RoadState putDown() {
        return new ConnectorVRoad(this);
    }

    @Override
    public RoadState putLeft() {
        return new TrunLeftInverse(this);
    }

    @Override
    public RoadState putRight() {
        return new TrunRightInverse(this);
    }

    @Override
    public RoadState removeUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeDown() {
        return (lastState == null) ? new Single() : lastState;
    }

    @Override
    public RoadState removeLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType() {
        return "VRoadUp";
    }

    @Override
    public boolean isCriticalArea() {
        return false;
    }
}
