package model.states.road;

import java.io.Serializable;
import util.RoadState;
import util.Settings.RoadTypes;

/**
 *
 * @author uellington
 */
public class VRoadDown implements RoadState, Serializable {

    private RoadState lastState;

    public VRoadDown(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadTypes getType() {
        return RoadTypes.VERTICAL;
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
        return new TrunLeft(this);
    }

    @Override
    public RoadState putRight() {
        return new TrunRight(this);
    }

    @Override
    public RoadState removeUp() {
        return (lastState == null) ? new Single() : lastState;
    }

    @Override
    public RoadState removeDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public boolean isCriticalArea() {
        return false;
    }

}
