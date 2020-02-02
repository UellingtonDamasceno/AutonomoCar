package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class TRoad implements RoadState {

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
        return (lastState == null) ? new Horizontal(null) : lastState;
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new TrunRight(null) : lastState;
    }

    @Override
    public RoadState removeRight() {
        return (lastState == null) ? new TrunLeft(null) : lastState;
    }

}
