package src;

public class AirplaneController {
    private final Airport airport;

    public AirplaneController(Airport airport) {
        this.airport = airport;
    }

    public void requestLanding(Airplane airplane, int laneId) {
        new Thread(() -> airport.startLanding(airplane, laneId)).start();
    }

    public void requestTakeoff(Airplane airplane, int laneId) {
        new Thread(() -> airport.startTakeoff(airplane, laneId)).start();
    }
}
