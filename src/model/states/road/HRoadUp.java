package model.states.road;

import java.io.Serializable;
import java.util.List;
import util.RoadState;
import util.Settings;
import util.Settings.RoadTypes;

/**
 *
 * @author uellington
 */
public class HRoadUp implements RoadState, Serializable {

    private RoadState lastState;

    public HRoadUp(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadTypes getType() {
        return RoadTypes.HORIZONTAL;
    }

    @Override
    public RoadState putUp() {
        return new TrunLeftInverse(this);
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
    public boolean isCriticalArea() {
        return false;
    }
}
