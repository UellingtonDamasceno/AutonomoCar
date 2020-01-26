package model;

import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class City {

    private String name;

    private int dx, dy;
    private double height, width;

    private int propotionX, propotionY;
    private Road[][] city;

    public City(String name, int dx, int dy, int h, int w) {
        this.name = name;

        this.dx = dx;
        this.dy = dy;

        this.height = h;
        this.width = w;

        this.propotionX = (w / dx);
        this.propotionY = (h / dy);

        this.city = new Road[dx][dy];

        System.out.println(this);

    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public void addRoad(Road road, int x, int y) {
        this.city[x][y] = road;
    }

    public Road getRoad(int x, int y) {
        return this.city[x][y];
    }

    public boolean isEmpaty(int x, int y) {
        return this.city[x][y] == null;
    }

    public int calculateRoadHeight(int posY) {
        return this.propotionY * (posY + 1);
    }

    public int calculateRoadWidth(int posX) {
        return this.propotionX * (posX + 1);
    }

    @Override
    public String toString() {
        JSONObject city = new JSONObject();

        city.accumulate("name", name);
        city.accumulate("dx", dx);
        city.accumulate("dy", dy);
        city.accumulate("height", height);
        city.accumulate("width", width);

        return city.toString();
    }

}
