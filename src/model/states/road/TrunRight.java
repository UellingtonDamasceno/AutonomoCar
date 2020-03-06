package model.states.road;

import java.io.Serializable;
import util.RoadState;
import util.Settings;

/**
 *
 * @author uellington
 */
public class TrunRight implements RoadState, Serializable {

    private RoadState lastState;

    public TrunRight(RoadState lastState) {
        this.lastState = lastState;
    }

        @Override
    public Settings.RoadTypes getType() {
        return Settings.RoadTypes.L_UP_RIGHT;
    }
    
    @Override
    public RoadState putUp() {
        return new TRightRoad(this);
    }

    @Override
    public RoadState putDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState putLeft() {
        return new TRoad(this);
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
        return (lastState == null) ? new HRoadUp(null) : lastState;
    }

    @Override
    public RoadState removeLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoadState removeRight() {
        return (lastState == null) ? new VRoadUp(null) : lastState;
    }

    @Override
    public boolean isCriticalArea() {
        return false;
    }
}
