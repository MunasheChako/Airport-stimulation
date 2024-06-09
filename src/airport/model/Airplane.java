package src.airport.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Airplane {
    private final int id;
    private String status;
    private String purpose;
    private final List<String> statusLogs;

    public Airplane(int id, String purpose) {
        this.id = id;
        this.status = "Idle";
        this.purpose = purpose;
        this.statusLogs = new ArrayList<>();
        this.statusLogs.add(this.status);
    }

    public int getId() {
        return id;
    }

    public synchronized String getStatus() {
        return status;
    }

    public synchronized void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        this.status = status;
        statusLogs.add(status);
    }

    public synchronized List<String> getStatusLogs() {
        return Collections.unmodifiableList(statusLogs);
    }

    public String getPurpose() {
        return purpose;
    }
}
