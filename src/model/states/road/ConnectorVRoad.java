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
public class ConnectorVRoad implements RoadState, Serializable {

    private RoadState lastState;

    public ConnectorVRoad(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadTypes getType() {
        return RoadTypes.VERTICAL;
    }

    @Override
    public RoadState putUp() {
        return new ConnectorVRoad(this);
    }

    @Override
    public RoadState putDown() {
        return new ConnectorVRoad(this);
    }

    @Override
    public RoadState putLeft() {
        return new TLeftRoad(this);
    }

    @Override
    public RoadState putRight() {
        return new TRightRoad(this);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
