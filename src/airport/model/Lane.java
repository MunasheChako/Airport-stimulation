package src.airport.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lane {
    private Lock lock = new ReentrantLock();
    private Airplane currentAirplane;

    public void startAirplane(Airplane airplane) {
        lock.lock();
        try {
            // Check if the lane is available
            if (currentAirplane == null) {
                currentAirplane = airplane;
                System.out.println("Aircraft " + airplane.getId() + " started on lane.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void landAirplane(Airplane airplane) {
        lock.lock();
        try {
            // Check if the lane is available
            if (currentAirplane == null) {
                currentAirplane = airplane;
                System.out.println("Aircraft " + airplane.getId() + " landed on lane.");
            }
        } finally {
            lock.unlock();
        }
    }
}
