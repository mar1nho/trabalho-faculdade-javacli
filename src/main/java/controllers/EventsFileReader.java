package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventsFileReader {

    private final File file = new File("src/main/resources/data/events.data");

    public List<String> getEventsRawFromFile() {
        ArrayList<String> eventsLine = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                eventsLine.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file, technical support needed. Please contact github.com/mar1nho for further details.");
            System.out.printf("Error: %s%n", e.getMessage());
            System.out.printf("Error: %s%n", e.getCause());
            return eventsLine;
        }
        return eventsLine;
    }
}
