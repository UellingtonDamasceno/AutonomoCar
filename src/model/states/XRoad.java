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
public class XRoad implements RoadState {
    private RoadState lastState;
    
    public XRoad(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadState putUp() {
        throw new UnsupportedOperationException("Cruzamento"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putDown() {
        throw new UnsupportedOperationException("Cruzamento"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putLeft() {
        throw new UnsupportedOperationException("Cruzamento"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putRight() {
        throw new UnsupportedOperationException("Cruzamento"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeUp() {
        return new TRoad(this);
    }

    @Override
    public RoadState removeDown() {
        return new TRoadInverse(this);
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new TRightRoad(null) : lastState;
    }

    @Override
    public RoadState removeRight() {
        return (lastState == null) ? new TLeftRoad(null) : lastState;
    }
    
}
