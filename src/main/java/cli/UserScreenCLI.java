package cli;

import models.EventModel;
import models.UserModel;
import services.EventService;
import utils.Log;
import utils.Sleep;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserScreenCLI {

    private List<EventModel> events;
    private final EventService eventService;
    private final UserModel mainUser;
    private List<LocalDateTime> eventsTime;

    public UserScreenCLI(UserModel user) {
        this.mainUser = user;
        this.eventService = new EventService();
    }

    public void _initialize(){
        Runnable runnable = () -> {
            eventsTime = new ArrayList<>();
            events = new ArrayList<>();
            events = eventService.loadEventListAsModel();

            events.forEach(eventModel -> {
                eventsTime.add(eventModel.getDate());
            });
            eventsGeneralStats();
        };
        runnable.run();
    }


    public void eventsGeneralStats() {
        Log.ascii();
        LocalDateTime now = LocalDateTime.now();

        long pastEvents = eventsTime.stream()
                .filter(dataEvento -> dataEvento.isBefore(now))
                .count();

        long futureEvents = eventsTime.stream()
                .filter(dataEvento -> dataEvento.isAfter(now))
                .count();

        Log.info("Total de eventos encerrados desde o lançamento: " + pastEvents);
        Log.info("Total de eventos próximos: " + futureEvents + "\n");
        Sleep.seconds(2000);
        Log.info("Escolha uma opção abaixo:\n");
        Log.info("[1] Meus Eventos");
        Log.info("[2] Próximos Eventos");
        Log.info("[3] Eventos Passados");
        Log.info("[4] Próximo de mim");
        Log.info("[5] Cadastrar Evento\n");
        Log.input("");
        String res = Log.getUserInput();

        switch (res) {
            case "1": Log.info("NOT IMPLEMENTED");break;
            case "2": showEvents();  break;
            case "3": pastEvents(); break;
            case "4": nextToMe(); break;
            case "5": registerEvent(); break;
            case "admin": test(); break;
        }
    }

    public void test(){
        events.forEach(eventModel -> {
           System.out.println(eventModel.getName());
        });
    }

    private void registerEvent() {
        Sleep.seconds(2);
        Log.ascii();
        Log.info("Registrando evento\n");

        boolean created = eventService.startEventCreation(this::eventsGeneralStats);
        if (created) {
            Log.info("✅ Evento registrado com sucesso!");
            Sleep.seconds(1000);
            eventsGeneralStats();
        }
    }



    private void nextToMe() {
        Log.ascii();
        Log.info("Username: " + mainUser.getUsername());

        String neighborhood = mainUser.getNeighbourhood();
        Log.info("Próximos ao bairro de " + neighborhood + "\n");
        Set<String> processedEvents = new HashSet<>();

        for (EventModel event : events) {
            String[] address = event.getAddress().split(":");
            String street = address[0];
            String neighborhoodName = address[1];
            String city = address[2];
            String address_formated = String.format("%s - %s / %s", street, neighborhoodName, city);

            String eventKey = event.getName() + " - " + address_formated + " - " + event.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            if (event.getDate().isAfter(LocalDateTime.now()) &&
                    event.getAddress().toLowerCase().contains(neighborhood.toLowerCase()) &&
                    !processedEvents.contains(eventKey)) {

                Log.info(eventKey);
                processedEvents.add(eventKey);
            }
        }
        Sleep.seconds(4000);
        eventsGeneralStats();
    }



    private void showEvents(){
        System.out.println(" ");
        Log.info("PRÓXIMOS EVENTOS\n");
        for (EventModel eventModel : events) {
            if (eventModel.getDate().isAfter(LocalDateTime.now())) {
                LocalDateTime dateTime = eventModel.getDate(); // Supondo que seja LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                String formattedDateTime = dateTime.format(formatter);
                String[] address = eventModel.getAddress().split(":");
                String street = address[0];
                String neighborhoodName = address[1];
                String city = address[2];
                String address_formated = String.format("%s - %s / %s", street, neighborhoodName, city);

                Log.info(String.format("ID: %d  -  Evento: %s   -   Endereço: %s   -   Data: %s",eventModel.getId(), eventModel.getName(), address_formated, formattedDateTime));
            }
        }
        Sleep.seconds(2000);
        eventsGeneralStats();
    }

    private void pastEvents(){
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
        Sleep.seconds(2000);
        eventsGeneralStats();
    }

}
