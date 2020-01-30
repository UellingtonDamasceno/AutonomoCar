package model;

import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class Road implements Observer {

    private String sprite, name;

    private Road previous, next;

    private int positionX, positionY;
    private int height, width;

    public Road(String sprite, int posX, int posY, int h, int w) {
        this.sprite = sprite;
        this.positionX = posX;
        this.positionY = posY;
        this.height = h;
        this.width = w;
    }

    public void setSprite(String sprite){
        this.sprite = sprite;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public int hashCode() {
        String value = Integer.toString(this.height);
        value = Integer.toString(this.width) + value;
        value = Integer.toString(this.positionX) + value;
        value = value + Integer.toString(this.positionY) + value;
        return value.hashCode();

    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Road) {
            Road another = (Road) object;
            return (this.sprite.equals(another.sprite)
                    && this.hashCode() == another.hashCode());
        }
        return false;
    }

    public String toString() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonArray = new JSONObject();
        
        jsonObject.accumulate("Sprite", sprite.substring(sprite.lastIndexOf("/")));
//        jsonObject.accumulate("sprite", this.sprite);
        //jsonObject.accumulate("roadName", this.name);
        jsonArray.accumulate("x", this.positionX);
        jsonArray.accumulate("y", this.positionY);
        jsonObject.accumulate("position", jsonArray);

//        jsonArray = new JSONObject();
//        jsonArray.accumulate("height", this.height);
//        jsonArray.accumulate("width", this.width);
//        jsonObject.accumulate("dimension", jsonArray);

        return jsonObject.toString();
    }
}
