/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class TurnRightInverse implements RoadState {

    private RoadState lastState;

    public TurnRightInverse(RoadState lastState) {
        this.lastState = lastState;
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
        return (lastState == null) ? new Horizontal(null) : lastState;
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
        return (lastState == null) ? new Vertical(null) : lastState;
    }

}
