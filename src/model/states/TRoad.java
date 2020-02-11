package model.states;

import java.io.Serializable;
import util.RoadState;

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
    public String getType() {
        return "TRoad";
    }

    @Override
    public boolean isCriticalArea() {
        return true;
    }
}
