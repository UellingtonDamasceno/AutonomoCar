/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author uellington
 */
public class City {
    private Road[][] city;
    
    public City(){
        this.city = new Road[10][10];
    }
    
    public void addRoad(Road road, int i, int j){
        this.city[i][j] = road;
    }
}
