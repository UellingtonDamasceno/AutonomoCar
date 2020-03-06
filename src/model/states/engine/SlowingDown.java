package model.states.engine;

import util.EngineState;

/**
 *
 * @author Uellington Conceição
 */
public class SlowingDown implements EngineState {

    private int minSpeed;
    private int currentSpeed;

    public SlowingDown(int minSpeed, int currentSpeed) {
        this.minSpeed = minSpeed;
        this.currentSpeed = currentSpeed;
    }

    @Override
    public int execute() {
        if (this.currentSpeed < this.minSpeed) {
            return currentSpeed++;
        } else {
            return currentSpeed;
        }
    }

    @Override
    public EngineState up(int maxSpeed) {
        return new SpeedingUP(maxSpeed, currentSpeed);
    }

    @Override
    public EngineState down(int minSpeed) {
        return this;
    }

    @Override
    public int getCurrentVelocity() {
        return this.currentSpeed;
    }
}
