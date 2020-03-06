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
public class ConnectorHRoad implements RoadState, Serializable {

    private RoadState lastState;

    public ConnectorHRoad(RoadState lastState) {
        this.lastState = lastState;
    }

    @Override
    public RoadTypes getType() {
        return RoadTypes.HORIZONTAL;
    }

    @Override
    public RoadState putUp() {
        return new TRoadInverse(this);
    }

    @Override
    public RoadState putDown() {
        return new TRoad(this);
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
