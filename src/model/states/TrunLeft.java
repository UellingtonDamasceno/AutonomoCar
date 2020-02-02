package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class TrunLeft implements RoadState {
    private RoadState lastState;
    
    protected TrunLeft(RoadState lastState){
        this.lastState = lastState;
    }
    
    @Override
    public RoadState putUp() {
        return new TLeftRoad(this);
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
        return new TRoad(this);
    }

    @Override
    public RoadState removeUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeDown() {
        return (lastState == null) ? new Horizontal(null) : lastState;
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
