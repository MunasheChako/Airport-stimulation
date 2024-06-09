package src.airport.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Airport {
    private final List<Airplane> airplanes;
    private final List<Lane> lanes;

    public Airport(int numberOfLanes) {
        this.airplanes = new ArrayList<>();
        this.lanes = new ArrayList<>();
        for (int i = 1; i <= numberOfLanes; i++) {
            lanes.add(new Lane(i));
        }
    }

    public synchronized void addAirplane(Airplane airplane) {
        if (airplane == null) {
            throw new IllegalArgumentException("Airplane cannot be null");
        }
        airplanes.add(airplane);
    }

    public synchronized Airplane getAirplaneById(int id) {
        for (Airplane airplane : airplanes) {
            if (airplane.getId() == id) {
                return airplane;
            }
        }
        return null;
    }

    public synchronized List<Airplane> getAllAirplanes() {
        return Collections.unmodifiableList(airplanes);
    }

    public synchronized List<Lane> getLanes() {
        return Collections.unmodifiableList(lanes);
    }

    public synchronized Lane getAvailableLane() {
        for (Lane lane : lanes) {
            if (!lane.isOccupied()) {
                return lane;
            }
        }
        return null; // No available lane
    }

    public synchronized void assignAirplaneToLane(Airplane airplane) {
        Lane availableLane = getAvailableLane();
        if (availableLane != null) {
            availableLane.assignAirplane(airplane);
        } else {
            System.out.println("No available lane for airplane with ID " + airplane.getId());
        }
    }
}
