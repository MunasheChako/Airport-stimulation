package src;


import java.util.ArrayList;
import java.util.List;

public class Airport {
    private final List<Lane> lanes;

    public Airport() {
        this.lanes = new ArrayList<>();
    }

    public void addLane(Lane lane) {
        lanes.add(lane);
    }

    public void removeLane(int laneId) {
        lanes.removeIf(lane -> lane.getLaneId() == laneId);
    }

    public int getNumLanes() {
        return lanes.size();
    }

    public void startLanding(Airplane airplane, int laneId) {
        for (Lane lane : lanes) {
            if (lane.getLaneId() == laneId) {
                lane.getLock().lock();
                try {
                    if (lane.isFree()) {
                        lane.setFree(false);
                        System.out.println("Airplane " + airplane.getAirplaneId() + " is landing on lane " + laneId);
                        // Simulate landing duration
                        Thread.sleep(2000);
                        lane.setFree(true);
                        System.out.println("Lane " + laneId + " is now free.");
                    } else {
                        System.out.println("Lane " + laneId + " is currently occupied.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lane.getLock().unlock();
                }
                return;
            }
        }
        System.out.println("Lane " + laneId + " not found.");
    }

    public void startTakeoff(Airplane airplane, int laneId) {
        for (Lane lane : lanes) {
            if (lane.getLaneId() == laneId) {
                lane.getLock().lock();
                try {
                    if (lane.isFree()) {
                        lane.setFree(false);
                        System.out.println("Airplane " + airplane.getAirplaneId() + " is taking off from lane " + laneId);
                        // Simulate takeoff duration
                        Thread.sleep(2000);
                        lane.setFree(true);
                        System.out.println("Lane " + laneId + " is now free.");
                    } else {
                        System.out.println("Lane " + laneId + " is currently occupied.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lane.getLock().unlock();
                }
                return;
            }
        }
        System.out.println("Lane " + laneId + " not found.");
    }
}
