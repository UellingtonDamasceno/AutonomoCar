package model;

import model.states.engine.SpeedingUP;
import util.EngineState;

/**
 *
 * @author Uellington Conceição
 */
public class Engine {

    private EngineState state;
    private int maxSpeed;
    public Engine(int maxSpeed) {
        this.state = new SpeedingUP(maxSpeed, (2 * maxSpeed));
        this.maxSpeed = maxSpeed;
    }

    public int getVelocity() {
        return this.state.getCurrentVelocity();
    }

    public void forceUp() {
        this.state = new SpeedingUP(this.maxSpeed, this.getVelocity());
    }

    public void setMaximumSpeed(int maximumSpeed) {
//        System.out.println("My: " + this.getVelocity());
//        System.out.println("Another: " + maximumSpeed);
        if (maximumSpeed < this.getVelocity()) {
            this.state = this.state.up(maximumSpeed);
//            System.out.println("Acelerando");
        } else {
//            System.out.println("Desacelerando");
            this.state = this.state.down(maximumSpeed);
        }
    }

    public int execute() {
        return this.state.execute();
    }
}
