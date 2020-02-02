package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class TRightRoad implements RoadState {
    private RoadState lastState;
    
    public TRightRoad(RoadState lastState) {
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
        return new XRoad(this);
    }

    @Override
    public RoadState putRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeUp() {
        return (lastState == null) ? new TrunRight(null) : lastState;
    }

    @Override
    public RoadState removeDown() {
        return (lastState == null) ? new TurnRightInverse(null) : lastState;
    }

    @Override
    public RoadState removeLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeRight() {
        return (lastState == null) ? new Vertical(null) : lastState;
    }
    
}
