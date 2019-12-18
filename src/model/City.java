package model;

/**
 *
 * @author uellington
 */
public class City {

    private int dx, dy;
    private Road[][] city;

    public City(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        this.city = new Road[dx][dy];
    }

    public int getDx() {
        return this.dx;
    }
    
    public int getDy(){
        return this.dy;
    }

    public void addRoad(Road road, int i, int j) {
        this.city[i][j] = road;
    }

}
