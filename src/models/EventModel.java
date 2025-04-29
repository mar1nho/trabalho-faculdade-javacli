package models;

import java.time.LocalDateTime;

public class EventModel {
    private int id;
    private String name;
    private String address;
    private String category;
    private LocalDateTime date;
    private String description;

    public EventModel(int id, String name, String address, String category, LocalDateTime date, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public EventModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String categoria) {
        this.category = categoria;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
