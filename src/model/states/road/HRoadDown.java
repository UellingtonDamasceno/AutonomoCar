/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.states.road;

import java.io.Serializable;
import java.util.List;
import util.RoadState;
import util.Settings;
import util.Settings.RoadTypes;

/**
 *
 * @author uellington
 */
public class HRoadDown implements RoadState, Serializable {

    private RoadState lastState;

    public HRoadDown(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadTypes getType() {
        return RoadTypes.HORIZONTAL;
    }
    
    @Override
    public RoadState putUp() {
        return new TrunRightInverse(this);
    }

    @Override
    public RoadState putDown() {
        return new TrunRight(this);
    }

    @Override
    public RoadState putLeft() {
        return new ConnectorHRoad(this);
    }

    @Override
    public RoadState putRight() {
        return new ConnectorHRoad(this);
    }

    @Override
    public RoadState removeUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new Single() : lastState;
    }

    @Override
    public RoadState removeRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCriticalArea() {
        return false;
    }
}
