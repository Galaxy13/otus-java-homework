package com.galaxy13;

import com.galaxy13.Homework7.Human;
import com.galaxy13.Homework7.Terrain;
import com.galaxy13.Homework7.Transport;

public class Main {
    public static void main(String[] args) throws Exception {
        Human human = new Human("John");
        Transport car = new Transport(200, "car");
        Transport horse = new Transport(150, "horse");
        human.moveOnTerrain(10, Terrain.PLAIN);

        human.enterVehicle(car);
        human.enterVehicle(horse);
        human.getOutOfVehicle();

        human.enterVehicle(horse);
        human.moveOnTerrain(100, Terrain.SWAMP);
        human.moveOnTerrain(20, Terrain.FOREST);

        human.getOutOfVehicle();
        human.enterVehicle(car);
        human.moveOnTerrain(50, Terrain.PLAIN);
    }
}