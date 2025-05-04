package services;

import controllers.EventsFileReader;
import controllers.EventsFileWriter;
import models.EventModel;
import utils.EventType;
import utils.Log;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EventService {

    private static final String[] switches = {"{", "}"};
    private final List<EventModel> eventList = new ArrayList<>();
    private final List<String> eventsRaw;
    private final EventsFileWriter eventsFileWriter = new EventsFileWriter();

    public EventService() {
        EventsFileReader eventsFileReader = new EventsFileReader();
        eventsRaw = eventsFileReader.getEventsRawFromFile();
    }
    public List<EventModel> loadEventListAsModel(){
        int idCount = 0;
        for (String rawEvent : eventsRaw) {
            boolean isSwitch = false;
            for (String switchEvent : switches) {
                if (rawEvent.contains(switchEvent)) {
                    isSwitch = true;
                    break;
                }
            }
            if (!isSwitch) {
                String[] data = rawEvent.split(";");
                if (data.length >= 5) {
                    EventModel eventModel = new EventModel();
                    eventModel.setId(idCount++);
                    eventModel.setName(data[0]);
                    eventModel.setAddress(data[1]);
                    eventModel.setCategory(data[2]);
                    eventModel.setDescription(data[4]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime formatedDate = LocalDateTime.parse(data[3], formatter);
                    eventModel.setDate(formatedDate);

                    this.eventList.add(eventModel);
                }
            }

        }
        eventList.sort(Comparator.comparing(EventModel::getId));
        return eventList;
    }

    public boolean startEventCreation(Runnable backToStats) {
        String name = null, description = null, category = null, address = null, dateInput = null;

        // Nome
        while (true) {
            Log.input("Nome do evento: ");
            name = Log.getUserInput();
            if (name.equals("0")) {
                backToStats.run();
                return false;
            }
            if (!name.trim().isEmpty()) break;
            Log.info("Nome inválido. Tente novamente.");
        }

        // Descrição
        while (true) {
            Log.input("Descrição do evento: ");
            description = Log.getUserInput();
            if (description.equals("0")) {
                backToStats.run();
                return false;
            }
            if (!description.trim().isEmpty()) break;
            Log.info("Descrição inválida. Tente novamente.");
        }

        // Tipo de evento
        while (true) {
            Log.info("Tipos de Eventos disponíveis: " + java.util.Arrays.toString(EventType.values()));
            Log.input("Insira o tipo desejado (ex: SHOW, ESTUDO, FESTA): ");
            category = Log.getUserInput();
            if (category.equals("0")) {
                backToStats.run();
                return false;
            }

            boolean validCategory = false;
            for (EventType type : EventType.values()) {
                if (category.equalsIgnoreCase(type.name())) {
                    validCategory = true;
                    break;
                }
            }

            if (validCategory) break;
            Log.info("Tipo inválido. Exemplo válido: SHOW, ESTUDO, FESTA");
        }

        // Endereço
        while (true) {
            Log.input("Insira o endereço no formato Rua:Bairro ou Cidade:ESTADO (ex: Rua Augusta:Liberdade:SP): ");
            address = Log.getUserInput();
            if (address.equals("0")) {
                backToStats.run();
                return false;
            }

            String[] parts = address.split(":");
            if (parts.length == 3) break;
            Log.info("Endereço inválido. Use o formato: Rua:Bairro:Cidade");
        }

        // Data
        LocalDateTime date;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        while (true) {
            Log.input("Insira a data do evento (formato: dd/MM/yyyy HH:mm): ");
            dateInput = Log.getUserInput();
            if (dateInput.equals("0")) {
                backToStats.run();
                return false;
            }

            try {
                date = LocalDateTime.parse(dateInput, inputFormatter);
                break;
            } catch (DateTimeParseException e) {
                Log.info("Data inválida. Exemplo: 25/12/2025 19:30");
            }
        }

        // Tudo validado — criar evento
        EventModel event = new EventModel(name, address, category.toUpperCase(), date, description);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = date.format(outputFormatter);

        String line = String.format("%s;%s;%s;%s;%s",
                event.getName(),
                event.getAddress(),
                event.getCategory(),
                formattedDate,
                event.getDescription()
        );

        eventsFileWriter.writeToFile(line);
        Log.info(String.format("✅ Evento criado: %s", event.getName()));
        return true;
    }

}
