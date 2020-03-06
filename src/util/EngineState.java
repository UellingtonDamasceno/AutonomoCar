package util;

/**
 *
 * @author Uellington Conceição
 */
public interface EngineState {
    public int execute();
    public int getCurrentVelocity();
    public EngineState up(int max);
    public EngineState down(int min);
}
