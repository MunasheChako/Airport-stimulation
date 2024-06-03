package src.airport.model;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private List<Lane> lanes;
    private List<AirTrafficController> controllers;

    public Airport(int numLanes, int numControllers) {
        lanes = new ArrayList<>();
        controllers = new ArrayList<>();

        for (int i = 0; i < numLanes; i++) {
            lanes.add(new Lane());
        }

        for (int i = 0; i < numControllers; i++) {
            controllers.add(new AirTrafficController());
        }
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public List<AirTrafficController> getControllers() {
        return controllers;
    }
}
