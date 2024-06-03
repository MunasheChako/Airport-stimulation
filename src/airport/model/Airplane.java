package src.airport.model;

import java.util.ArrayList;
import java.util.List;

public class Airplane {
    private int id;
    private String status;
    private List<String> statusLogs;

    public Airplane(int id) {
        this.id = id;
        this.status = "Idle";
        this.statusLogs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        statusLogs.add(status);
    }

    public List<String> getStatusLogs() {
        return statusLogs;
    }
}
