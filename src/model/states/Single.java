package model.states;

import util.RoadState;

/**
 *
 * @author uellington
 */
public class Single implements RoadState {

    public Single(){
        System.out.println("IsSingle");
    }
    
    @Override
    public RoadState putUp() {
        return new Vertical(this);
    }

    @Override
    public RoadState putDown() {
        return new Vertical(this);
    }

    @Override
    public RoadState putLeft() {
        return new Horizontal(this);
    }

    @Override
    public RoadState putRight() {
        return new Horizontal(this);
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

}
