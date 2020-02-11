package model.states;

import java.io.Serializable;
import util.RoadState;

/**
 *
 * @author uellington
 */
public class Single implements RoadState, Serializable {

    @Override
    public RoadState putUp() {
        return new VRoadUp(this);
    }

    @Override
    public RoadState putDown() {
        return new VRoadDown(this);
    }

    @Override
    public RoadState putLeft() {
        return new HRoadUp(this);
    }

    @Override
    public RoadState putRight() {
        return new HRoadDown(this);
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
    public String getType() {
        return "Single";
    }

    @Override
    public boolean isCriticalArea() {
        return false;
    }
}
