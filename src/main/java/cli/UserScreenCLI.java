package cli;

import models.EventModel;
import models.UserModel;
import services.EventService;
import utils.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserScreenCLI {

    private EventService eventService;

    private UserModel mainUser;

    public UserScreenCLI(UserModel user) {
        this.mainUser = user;
        this.eventService = new EventService();
    }

    public void _initialize(){
        Runnable runnable = () -> {
            Log.ascii();
            Log.info("Welcome to the user screen");
            testCalculate();
        };
        runnable.run();
    }


    public void testCalculate() {
        List<EventModel> eventModelList = eventService.loadEventListAsModel();

        LocalDateTime now = LocalDateTime.now();
        List<LocalDateTime> eventsTime = new ArrayList<>();
        eventModelList.forEach(eventModel -> {
            eventsTime.add(eventModel.getDate());
        });

        long pastEvents = eventsTime.stream()
                .filter(dataEvento -> dataEvento.isBefore(now))
                .count();

        long futureEvents = eventsTime.stream()
                .filter(dataEvento -> dataEvento.isAfter(now))
                .count();

        Log.info("Total of ended events since launch: " + pastEvents);
        Log.info("Future events coming: " + futureEvents);
    }
}
