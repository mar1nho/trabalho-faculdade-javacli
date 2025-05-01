package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserModel {
    private String username;
    private String password;
    private Map<Integer, String> eventsConfirmed;

    public UserModel(String username, String password, Map<Integer, String> eventsConfirmed) {
        this.username = username;
        this.password = password;
        this.eventsConfirmed = eventsConfirmed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Integer, String> getEventsConfirmed() {
        return eventsConfirmed;
    }

    public void setEventsConfirmed(Map<Integer, String>eventsConfirmed) {
        this.eventsConfirmed = eventsConfirmed;
    }
}
