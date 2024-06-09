package src.airport.simulation;

import src.airport.model.Airplane;
import src.airport.model.Airport;
import src.airport.model.Lane;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirportSimulation {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Object fileWriteLock = new Object(); // Lock for file writing

    public static void main(String[] args) {
        Airport airport = new Airport(3); // 3 lanes

        List<Airplane> airplanes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) { // Start with ID 1 instead of 0
            String purpose = (i % 2 == 0) ? "Transporting goods" : "Transporting people";
            airplanes.add(new Airplane(i, purpose));
        }

        generateRandomActions(airport, airplanes); // Generate random actions for airplanes
    }

    public static void generateRandomActions(Airport airport, List<Airplane> airplanes) {
        Random random = new Random();

        for (Airplane airplane : airplanes) {
            // Generate random actions for each airplane
            new Thread(() -> {
                while (true) {
                    // Perform random action: start or land
                    int action = random.nextInt(2); // 0 for start, 1 for land

                    if (action == 0) { // Start airplane
                        for (Lane lane : airport.getLanes()) {
                            if (!lane.isOccupied()) { // Check if the lane is not occupied
                                lane.startAirplane(airplane);
                                logStatus(airplane, "Started on lane " + lane.getId());
                                break;
                            }
                        }
                    } else { // Land airplane
                        for (Lane lane : airport.getLanes()) {
                            if (!lane.isOccupied()) { // Check if the lane is not occupied
                                lane.landAirplane(airplane);
                                logStatus(airplane, "Landed on lane " + lane.getId());
                                break;
                            }
                        }
                    }

                    // Sleep for random time between actions
                    try {
                        Thread.sleep(random.nextInt(5000) + 1000); // Random time between 1 to 6 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void logStatus(Airplane airplane, String message) {
        // Get current timestamp
        LocalDateTime timestamp = LocalDateTime.now();

        // Get thread number
        long threadId = Thread.currentThread().getId();

        // Format timestamp
        String formattedTimestamp = timestamp.format(formatter);

        // Get exact names of objects involved
        String airplaneName = "Airplane " + airplane.getId();
        String purpose = airplane.getPurpose();

        // Construct status message with timestamp, thread number, airplane name, purpose, and message
        String statusMessage = String.format("[%s] Thread-%d: %s (%s) - %s", formattedTimestamp, threadId, airplaneName, purpose, message);

        // Log status to console
        System.out.println(statusMessage);

        // Log status to file
        synchronized (fileWriteLock) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("status.log", true))) {
                writer.println(statusMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
