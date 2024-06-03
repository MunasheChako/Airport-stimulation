package src;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport();

        Lane lane1 = new Lane(1);
        Lane lane2 = new Lane(2);

        airport.addLane(lane1);
        airport.addLane(lane2);

        Airplane airplane1 = new Airplane("A1");
        Airplane airplane2 = new Airplane("A2");

        AirplaneController controller = new AirplaneController(airport);

        controller.requestLanding(airplane1, 1);
        controller.requestTakeoff(airplane2, 2);
        controller.requestLanding(airplane1, 1);
        controller.requestTakeoff(airplane2, 2);

        // Ensure main thread waits for all operations to complete
        try {
            Thread.sleep(5000); // Wait for a longer duration than the longest operation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
 
