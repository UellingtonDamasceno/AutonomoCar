package model.states.road;

import java.io.Serializable;
import util.RoadState;
import util.Settings.RoadTypes;

/**
 *
 * @author uellington
 */
public class TRoad implements RoadState, Serializable {

    private RoadState lastState;

    public TRoad(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadTypes getType() {
        return RoadTypes.T;
    }

    @Override
    public RoadState putUp() {
        return new XRoad(this);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeDown() {
        return (lastState == null) ? new HRoadUp(null) : lastState;
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new TrunRight(null) : lastState;
    }

    @Override
    public RoadState removeRight() {
        return (lastState == null) ? new TrunLeft(null) : lastState;
    }

    @Override
    public boolean isCriticalArea() {
        return true;
    }
}
