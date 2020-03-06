package model.states.road;

import java.io.Serializable;
import util.RoadState;
import util.Settings.RoadTypes;

/**
 *
 * @author uellington
 */
public class TLeftRoad implements RoadState, Serializable {

    private RoadState lastState;

    protected TLeftRoad(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadTypes getType() {
        return RoadTypes.T_LEFT;
    }

    @Override
    public RoadState putUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putRight() {
        return new XRoad(this);
    }

    @Override
    public RoadState removeUp() {
        return (lastState == null) ? new TrunLeft(null) : lastState;
    }

    @Override
    public RoadState removeDown() {
        return (lastState == null) ? new TrunLeftInverse(null) : lastState;
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new VRoadUp(null) : lastState;
    }

    @Override
    public RoadState removeRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCriticalArea() {
        return true;
    }
}
