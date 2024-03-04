package com.galaxy13.Homework7;

public class Human {
    private final String name;
    private Transport currentTransport = new Transport(100, "legs");
    private Transport tempTransport = this.currentTransport;

    public Human(String name) throws Exception {
        this.name = name;
    }

    public void enterVehicle(Transport transport) {
        if (currentTransport.getTransportType().equals("LEGS")) {
            this.tempTransport = this.currentTransport;
            this.currentTransport = transport;
            System.out.printf("Human entered %s%n", currentTransport.getTransportType());
        } else {
            System.out.println("Human is already in transport");
        }
    }

    public void getOutOfVehicle() {
        if (currentTransport.getTransportType().equals("LEGS")) {
            System.out.println("Human is not in vehicle");
        } else {
            this.currentTransport = this.tempTransport;
            System.out.println("Human exited vehicle");
        }
    }

    public void moveOnTerrain(int distance, Terrain terrain) {
        currentTransport.move(distance, terrain);
    }
}
