package controllers;

import models.EventModel;
import services.EventService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EventsFileWriter {

    protected File file = new File("src/controllers/data/events.data");

    public void writeToFile(String formatedEvent) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.newLine();
            writer.write("{");
            writer.newLine();
            writer.write(formatedEvent);
            writer.newLine();
            writer.write("}");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
