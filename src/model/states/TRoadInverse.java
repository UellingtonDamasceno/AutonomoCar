package model.states;

import java.io.Serializable;
import util.RoadState;

/**
 *
 * @author uellington
 */
public class TRoadInverse implements RoadState, Serializable {

    private RoadState lastState;

    public TRoadInverse(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadState putUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putDown() {
        return new XRoad(this);
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
        lastState = (lastState == null) ? new HRoadUp(null) : lastState;
        return lastState;
    }

    @Override
    public RoadState removeDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeLeft() {
        lastState = (lastState == null) ? new TrunRightInverse(null) : lastState;
        return lastState;
    }

    @Override
    public RoadState removeRight() {
        lastState = (lastState == null) ? new TrunLeftInverse(null) : lastState;
        return lastState;
    }

    @Override
    public String getType() {
        return "TRoadInverse";
    }

    @Override
    public boolean isCriticalArea() {
        return true;
    }
}
