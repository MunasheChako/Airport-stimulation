package src.airport.simulation;

import src.airport.model.Airplane;
import src.airport.model.Airport;
import src.airport.model.Lane;

import java.util.ArrayList;
import java.util.List;

public class AirportSimulation {
    public static void main(String[] args) {
        Airport airport = new Airport(3, 5); // 3 lanes, 5 controllers

        List<Airplane> airplanes = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // 10 airplanes
            airplanes.add(new Airplane(i));
        }

        for (Airplane airplane : airplanes) {
            // Start a thread for each airplane
            new Thread(() -> airplaneStartLandLoop(airport, airplane)).start();
        }
    }

    public static void airplaneStartLandLoop(Airport airport, Airplane airplane) {
        while (true) {
            // Perform actions like starting or landing
            try {
                Thread.sleep(1000); // Simulated time for actions
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Example: Start the airplane
            for (Lane lane : airport.getLanes()) {
                lane.startAirplane(airplane);
                airplane.setStatus("Started on lane " + lane.hashCode());
            }

            // Example: Land the airplane
            for (Lane lane : airport.getLanes()) {
                lane.landAirplane(airplane);
                airplane.setStatus("Landed on lane " + lane.hashCode());
            }
        }
    }
}
