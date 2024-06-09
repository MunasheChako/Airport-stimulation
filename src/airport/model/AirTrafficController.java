package src.airport.model;

import java.util.ArrayList;
import java.util.List;

public class AirTrafficController {

    private final Airport airport;
    private final List<Lane> lanes;

    public AirTrafficController(Airport airport, int numberOfLanes) {
        if (airport == null) {
            throw new IllegalArgumentException("Airport cannot be null");
        }
        this.airport = airport;
        this.lanes = new ArrayList<>();
        for (int i = 1; i <= numberOfLanes; i++) {
            lanes.add(new Lane(i));
        }
    }

    public void serveAirplane(Airplane airplane) {
        if (airplane == null) {
            throw new IllegalArgumentException("Airplane cannot be null");
        }
        airplane.setStatus("Served");
        System.out.println("Airplane with ID " + airplane.getId() + " is now served.");
    }

    public void assignAirplaneToLane(int airplaneId, int laneId) {
        Airplane airplane = airport.getAirplaneById(airplaneId);
        Lane lane = getLaneById(laneId);
        if (airplane != null && lane != null) {
            lane.assignAirplane(airplane);
        } else {
            System.out.println("Invalid airplane ID or lane ID");
        }
    }

    public void releaseAirplaneFromLane(int laneId) {
        Lane lane = getLaneById(laneId);
        if (lane != null) {
            lane.releaseAirplane();
        } else {
            System.out.println("Invalid lane ID");
        }
    }

    private Lane getLaneById(int laneId) {
        for (Lane lane : lanes) {
            if (lane.getId() == laneId) {
                return lane;
            }
        }
        return null;
    }

    public void authorizeTakeOff(int airplaneId) {
        Airplane airplane = airport.getAirplaneById(airplaneId);
        if (airplane != null) {
            airplane.setStatus("Taking Off");
            System.out.println("Airplane with ID " + airplane.getId() + " is authorized for takeoff.");
        } else {
            System.out.println("Airplane with ID " + airplaneId + " not found.");
        }
    }

    public void authorizeLanding(int airplaneId) {
        Airplane airplane = airport.getAirplaneById(airplaneId);
        if (airplane != null) {
            airplane.setStatus("Landing");
            System.out.println("Airplane with ID " + airplane.getId() + " is authorized for landing.");
        } else {
            System.out.println("Airplane with ID " + airplaneId + " not found.");
        }
    }

    public void logAirplaneStatus(int airplaneId) {
        Airplane airplane = airport.getAirplaneById(airplaneId);
        if (airplane != null) {
            System.out.println("Status logs for Airplane ID " + airplane.getId() + ": " + airplane.getStatusLogs());
        } else {
            System.out.println("Airplane with ID " + airplaneId + " not found.");
        }
    }
}
