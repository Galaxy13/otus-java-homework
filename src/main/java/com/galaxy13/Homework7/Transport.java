package com.galaxy13.Homework7;

import java.util.List;

import static com.galaxy13.Homework7.Terrain.FOREST;
import static com.galaxy13.Homework7.Terrain.SWAMP;

enum TransportTypes {
    LEGS(new Terrain[]{}, 2),
    CAR(new Terrain[]{FOREST, SWAMP}, 1),
    HORSE(new Terrain[]{SWAMP}, 5),
    BICYCLE(new Terrain[]{SWAMP}, 2),
    ATV(new Terrain[]{}, 10);

    private final List<Terrain> restrictions;
    private final int consumption;

    TransportTypes(Terrain[] restrictions, int consumption) {
        this.restrictions = List.of(restrictions);
        this.consumption = consumption;
    }

    public List<Terrain> getRestrictions() {
        return restrictions;
    }

    public int getConsumption() {
        return consumption;
    }
}

public class Transport {
    final private TransportTypes transportType;
    private int abstractResource;

    public Transport(int abstractResource, String transportType) throws Exception {
        this.abstractResource = abstractResource;
        switch (transportType) {
            case "legs" -> this.transportType = TransportTypes.LEGS;
            case "car" -> this.transportType = TransportTypes.CAR;
            case "horse" -> this.transportType = TransportTypes.HORSE;
            case "bicycle" -> this.transportType = TransportTypes.BICYCLE;
            case "atv" -> this.transportType = TransportTypes.ATV;
            default -> throw new Exception("Unsupported vehicle");
        }
    }

    public void move(int distance, Terrain terrainType) {
        if (this.transportType.getRestrictions().contains(terrainType)) {
            System.out.println("Restricted type of terrain.");
        } else if (abstractResource - distance * transportType.getConsumption() < 0) {
            System.out.println("Not enough gas or forces to reach destination point");
        } else {
            this.abstractResource -= transportType.getConsumption() * distance;
            System.out.println("Distance successfully finished");
        }
    }

    public String getTransportType() {
        return transportType.name();
    }
}
