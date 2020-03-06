package model;

import model.states.road.Single;
import util.Settings.SpritesCity;

/**
 *
 * @author uellington
 */
public class NullRoad extends Road {

    public NullRoad(int posX, int posY) {
        super(SpritesCity.NULL_ROAD.get(), posX, posY, posX, posY);
    }

    @Override
    public int getPostionX() {
        return 0;
    }

    @Override
    public int getPostionY() {
        return 0;
    }

}
