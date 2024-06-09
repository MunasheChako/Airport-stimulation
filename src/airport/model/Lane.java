package src.airport.model;

public class Lane {
    private final int id;
    private Airplane currentAirplane;

    public Lane(int id) {
        this.id = id;
        this.currentAirplane = null;
    }

    public int getId() {
        return id;
    }

    public Airplane getCurrentAirplane() {
        return currentAirplane;
    }

    public synchronized boolean isOccupied() {
        return currentAirplane != null;
    }

    public synchronized void assignAirplane(Airplane airplane) {
        if (airplane == null) {
            throw new IllegalArgumentException("Airplane cannot be null");
        }
        this.currentAirplane = airplane;
        airplane.setStatus("Assigned to Lane " + id);
        System.out.println("Airplane with ID " + airplane.getId() + " assigned to Lane " + id);
    }

    public synchronized void releaseAirplane() {
        if (currentAirplane != null) {
            currentAirplane.setStatus("Released from Lane " + id);
            System.out.println("Airplane with ID " + currentAirplane.getId() + " released from Lane " + id);
            this.currentAirplane = null;
        } else {
            System.out.println("No airplane currently assigned to Lane " + id);
        }
    }

    public synchronized void startAirplane(Airplane airplane) {
        if (!isOccupied() || currentAirplane.getId() == airplane.getId()) {
            assignAirplane(airplane);
            airplane.setStatus("Started on lane " + id);
            System.out.println("Airplane with ID " + airplane.getId() + " started on Lane " + id);
        } else {
            System.out.println("Lane " + id + " is currently occupied by another airplane.");
        }
    }

    public synchronized void landAirplane(Airplane airplane) {
        if (!isOccupied() || currentAirplane.getId() == airplane.getId()) {
            assignAirplane(airplane);
            airplane.setStatus("Landed on lane " + id);
            System.out.println("Airplane with ID " + airplane.getId() + " landed on Lane " + id);
        } else {
            System.out.println("Lane " + id + " is currently occupied by another airplane.");
        }
    }
}
