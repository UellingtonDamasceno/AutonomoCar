package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class TLeftRoad implements RoadState {
    private RoadState lastState;
    
    protected TLeftRoad(RoadState lastState) {
        this.lastState = lastState;
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
        return (lastState == null) ? new TrunLeftInverse(null): lastState;
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new Vertical(null) : lastState;
    }

    @Override
    public RoadState removeRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
