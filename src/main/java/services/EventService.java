package services;

import controllers.EventsFileReader;
import controllers.EventsFileWriter;
import models.EventModel;
import utils.Log;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    // Função para criar/ler os eventos no arquivo, recebe um model como parâmetro.
    public void createEvent(EventModel eventModel){
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = eventModel.getDate().format(formatter);
        String line = String.format("%s;%s;%s;%s;%s",
                eventModel.getName(),
                eventModel.getAddress(),
                eventModel.getCategory(),
                formattedDate,
                eventModel.getDescription()
        );
        eventsFileWriter.writeToFile(line);
        Log.info(String.format("Criado evento com nome %s", eventModel.getName()));
    }
}
