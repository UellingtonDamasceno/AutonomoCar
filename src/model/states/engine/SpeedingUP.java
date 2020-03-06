package model.states.engine;

import util.EngineState;

/**
 *
 * @author Uellington Conceição
 */
public class SpeedingUP implements EngineState {
    
    private int maxSpeed;
    private int currentSpeed;
    
    public SpeedingUP(int max, int currentSpeed){
        this.maxSpeed = max;
        this.currentSpeed = currentSpeed;
    }
    
    @Override
    public int execute() {
        if(this.currentSpeed > maxSpeed){
            return currentSpeed--;
        }else{
            return currentSpeed;
        }
    }

    @Override
    public EngineState up(int max) {
        return this;
    }

    @Override
    public EngineState down(int min) {
        return new SlowingDown(min, currentSpeed);
    }

    @Override
    public int getCurrentVelocity() {
        return this.currentSpeed;
    }

}
