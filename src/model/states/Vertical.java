package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class Vertical implements RoadState {

    private RoadState lastState;

    protected Vertical(RoadState lastState) {
        this.lastState = lastState;
        System.out.println("Current: vertical");
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
        return new TrunLeft(this);
    }

    @Override
    public RoadState putRight() {
        return new TrunRight(this);
    }

    @Override
    public RoadState removeUp() {
        return (lastState == null) ? new Single() : lastState;
    }

    @Override
    public RoadState removeDown() {
        return (lastState == null) ? new Single() : lastState;
    }

    @Override
    public RoadState removeLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
