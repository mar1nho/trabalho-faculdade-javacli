package controllers;

import models.EventModel;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventsFileReader {

    private String[] swicthes = {"{", "}"};

    protected File file = new File("src/controllers/data/events.data");

    public List<EventModel> getEvents() {
        ArrayList<EventModel> events = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean isSwitch = false;

                for (String swicth : swicthes) {
                    if (line.contains(swicth)) {
                        isSwitch = true;
                        break;
                    }
                }
                if (!isSwitch) {
                    String[] data = line.split(";");
                    int dataLength = data.length;
                    for(int i  = 0; i < dataLength;) {
                        EventModel eventModel = new EventModel();

                        eventModel.setName(data[i]);
                        eventModel.setAddress(data[i+1]);
                        eventModel.setCategory(data[i+2]);
                        eventModel.setDescription(data[i+4]);
                        String date = data[i+3];


                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        LocalDateTime formatedDate = LocalDateTime.parse(date, formatter);
                        eventModel.setDate(formatedDate);
                        events.add(eventModel);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file, technical support needed. Please contact github.com/mar1nho for further details.");
            System.out.printf("Error: %s%n", e.getMessage());
            System.out.printf("Error: %s%n", e.getCause());
        }
        return events;
    }
}
