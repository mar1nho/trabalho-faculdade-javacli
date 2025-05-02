package cli;

import models.EventModel;
import models.UserModel;
import services.EventService;
import utils.Log;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserScreenCLI {

    private List<EventModel> events;
    private final EventService eventService;
    private UserModel mainUser;

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

        Log.info("Total of ended events since launch: " + pastEvents);
        Log.info("Future events coming: " + futureEvents + "\n");
        Log.info("Escolha uma opção abaixo:");
        Log.info("[1] Meus Eventos");
        Log.info("[2] Próximos Eventos");
        Log.info("[3] Eventos Passados");
        Log.info("[4] Próximo de mim");
        Log.input("");
        String res = Log.getUserInput();

        switch (res) {
            case "1", "4": Log.info("NOT IMPLEMENTED");break;
            case "2": showEvents();  break;
            case "3": pastEvents(); break;
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
                Log.info(String.format("Evento: %s   -   Endereço: %s   -   Data: %s", eventModel.getName(), eventModel.getAddress(), formattedDateTime));
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
                Log.info(String.format("Evento: %s   -   Endereço: %s   -   Data: %s", eventModel.getName(), eventModel.getAddress(), formattedDateTime));
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
