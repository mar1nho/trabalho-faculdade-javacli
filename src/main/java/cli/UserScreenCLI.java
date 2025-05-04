package cli;

import models.EventModel;
import models.UserModel;
import services.EventService;
import utils.Log;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserScreenCLI {

    private final List<EventModel> events;
    private final EventService eventService;
    private final UserModel mainUser;

    public UserScreenCLI(UserModel user) {
        this.mainUser = user;
        this.eventService = new EventService();
        this.events = eventService.loadEventListAsModel();
    }

    public void _initialize(){
        Runnable runnable = () -> {
            Log.ascii();
            Log.info("Welcome to the user screen\n");
            eventsGeneralStats();
        };
        runnable.run();
    }


    public void eventsGeneralStats() {

        LocalDateTime now = LocalDateTime.now();
        List<LocalDateTime> eventsTime = new ArrayList<>();
        events.forEach(eventModel -> {
            eventsTime.add(eventModel.getDate());
        });

        long pastEvents = eventsTime.stream()
                .filter(dataEvento -> dataEvento.isBefore(now))
                .count();

        long futureEvents = eventsTime.stream()
                .filter(dataEvento -> dataEvento.isAfter(now))
                .count();

        Log.info("Total de eventos encerrados desde o lançamento: " + pastEvents);
        Log.info("Total de eventos próximos: " + futureEvents + "\n");
        Log.info("Escolha uma opção abaixo:\n");
        Log.info("[1] Meus Eventos");
        Log.info("[2] Próximos Eventos");
        Log.info("[3] Eventos Passados");
        Log.info("[4] Próximo de mim\n");
        Log.input("");
        String res = Log.getUserInput();

        switch (res) {
            case "1": Log.info("NOT IMPLEMENTED");break;
            case "2": showEvents();  break;
            case "3": pastEvents(); break;
            case "4": nextToMe(); break;
        }
    }

    public void nextToMe() {
        Log.ascii();
        Log.info("Username: " + mainUser.getUsername());

        String neighborhood = mainUser.getAddressesString().split("-")[0].trim();
        Log.info("Próximos ao bairro de " + neighborhood + "\n");

        List<EventModel> events = eventService.loadEventListAsModel();
        Set<String> processedEvents = new HashSet<>();

        for (EventModel event : events) {
            String eventKey = event.getName() + " - " + event.getAddress() + " - " + event.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            if (event.getDate().isAfter(LocalDateTime.now()) &&
                    event.getAddress().toLowerCase().contains(neighborhood.toLowerCase()) &&
                    !processedEvents.contains(eventKey)) {

                Log.info(eventKey);
                processedEvents.add(eventKey);
            }
        }
    }



    public void showEvents(){
        System.out.println(" ");
        Log.info("PRÓXIMOS EVENTOS\n");
        for (EventModel eventModel : events) {
            if (eventModel.getDate().isAfter(LocalDateTime.now())) {
                LocalDateTime dateTime = eventModel.getDate(); // Supondo que seja LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String formattedDateTime = dateTime.format(formatter);
                Log.info(String.format("ID: %d  -  Evento: %s   -   Endereço: %s   -   Data: %s",eventModel.getId(), eventModel.getName(), eventModel.getAddress(), formattedDateTime));
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _initialize();
    }

    public void pastEvents(){
        System.out.println(" ");
        Log.info("EVENTOS ENCERRADOS\n");
        for (EventModel eventModel : events) {
            if (eventModel.getDate().isBefore(LocalDateTime.now())) {
                LocalDateTime dateTime = eventModel.getDate(); // Supondo que seja LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String formattedDateTime = dateTime.format(formatter);
                Log.info(String.format("ID: %d  -  Evento: %s   -   Endereço: %s   -   Data: %s",eventModel.getId(), eventModel.getName(), eventModel.getAddress(), formattedDateTime));
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _initialize();
    }

}
