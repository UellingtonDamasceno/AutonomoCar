package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class TRoadInverse implements RoadState {
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
        lastState = (lastState == null) ? new Horizontal(null) : lastState;
        return lastState;
    }

    @Override
    public RoadState removeDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeLeft() {
        lastState = (lastState == null) ? new TurnRightInverse() : lastState;
        return lastState;
    }

    @Override
    public RoadState removeRight() {
        lastState = (lastState == null) ? new TrunLeftInverse() : lastState;
        return lastState;
    }
       
}
