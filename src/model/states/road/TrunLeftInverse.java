/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.states.road;

import java.io.Serializable;
import util.RoadState;
import util.Settings;

/**
 *
 * @author uellington
 */
public class TrunLeftInverse implements RoadState, Serializable {

    private RoadState lastState;

    public TrunLeftInverse(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public Settings.RoadTypes getType() {
        return Settings.RoadTypes.L_DOWN_LEFT;
    }

    @Override
    public RoadState putUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putDown() {
        return new TLeftRoad(this);
    }

    @Override
    public RoadState putLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putRight() {
        return new TRoadInverse(this);
    }

    @Override
    public RoadState removeUp() {
        return (lastState == null) ? new HRoadUp(null) : lastState;
    }

    @Override
    public RoadState removeDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new VRoadDown(null) : lastState;
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
