package model.states;

import java.io.Serializable;
import util.RoadState;

/**
 *
 * @author uellington
 */
public class XRoad implements RoadState, Serializable{

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
        return (lastState == null) ? new TRoad(this) : lastState;
    }

    @Override
    public RoadState removeDown() {
        return (lastState == null) ? new TRoadInverse(this) : lastState;
    }

    @Override
    public RoadState removeLeft() {
        return (lastState == null) ? new TRightRoad(null) : lastState;
    }

    @Override
    public RoadState removeRight() {
        return (lastState == null) ? new TLeftRoad(null) : lastState;
    }

    @Override
    public String getType() {
        return "XRoad";
    }

}
