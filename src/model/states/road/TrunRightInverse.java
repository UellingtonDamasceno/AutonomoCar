package model.states.road;

import java.io.Serializable;
import util.RoadState;
import util.Settings;

/**
 *
 * @author uellington
 */
public class TrunRightInverse implements RoadState, Serializable {

    private RoadState lastState;

    public TrunRightInverse(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public Settings.RoadTypes getType() {
        return Settings.RoadTypes.L_DOWN_RIGHT;
    }

    @Override
    public RoadState putUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putDown() {
        return new TRightRoad(this);
    }

    @Override
    public RoadState putLeft() {
        return new TRoadInverse(this);
    }

    @Override
    public RoadState putRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeUp() {
        return (lastState == null) ? new HRoadDown(null) : lastState;
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
        return (lastState == null) ? new VRoadDown(null) : lastState;
    }

    @Override
    public boolean isCriticalArea() {
        return false;
    }
}
