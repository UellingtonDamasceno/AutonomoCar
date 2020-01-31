package model;

import util.Settings.SpritesCity;

/**
 *
 * @author uellington
 */
public class NullRoad extends Road{
    
    public NullRoad(int posX, int posY) {
        super(SpritesCity.NULL_ROAD.get(), posX, posY, posX, posY);
    }
    
}
