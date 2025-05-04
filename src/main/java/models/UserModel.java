package models;

import java.util.Map;

public class UserModel {
    private String username;
    private String password;
    private String[][] address;
    private Map<Integer, String> eventsConfirmed;

    public UserModel(String username, String password, Map<Integer, String> eventsConfirmed, String[][] address) {
        this.username = username;
        this.password = password;
        this.eventsConfirmed = eventsConfirmed;
        this.address = address;
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

    public String[][] getAddress() {
        return address;
    }

    public void setAddress(String[][] address) {
        this.address = address;
    }

    public Map<Integer, String> getEventsConfirmed() {
        return eventsConfirmed;
    }

    public void setEventsConfirmed(Map<Integer, String>eventsConfirmed) {
        this.eventsConfirmed = eventsConfirmed;
    }

    public String getAddressesString() {
        String neighbourhood = address[0][0];
        String city = address[1][0];
        return String.format("%s-%s", neighbourhood, city);
    }

}
