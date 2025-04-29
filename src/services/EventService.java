package services;

import models.EventModel;

import java.time.LocalDateTime;
import java.util.List;

public class EventService {

    private int nextId = 1;

    public EventModel createEvent(String name, String address, String category, LocalDateTime date, String description) {
        EventModel event = new EventModel(nextId, name, address, category, date, description);
        nextId++;
        return event;
    }

    public void adjustInitialId(List<EventModel> events) {
        for (EventModel e : events) {
            if (e.getId() >= nextId) {
                nextId = e.getId() + 1;
            }
        }
    }
}
