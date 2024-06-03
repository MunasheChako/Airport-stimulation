package src;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lane {
    private final int laneId;
    private final Lock lock;
    private boolean isFree;

    public Lane(int laneId) {
        this.laneId = laneId;
        this.lock = new ReentrantLock();
        this.isFree = true;
    }

    public int getLaneId() {
        return laneId;
    }

    public Lock getLock() {
        return lock;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
