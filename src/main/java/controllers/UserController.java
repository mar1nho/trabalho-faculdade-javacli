package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.UserModel;
import utils.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final String filePath = "src/main/java/controllers/data/user-data.json";
    public boolean saveUserToJSON(UserModel user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<UserModel> users = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<UserModel>>() {}.getType();
            users = gson.fromJson(reader, listType);

            if (users == null) {
                users = new ArrayList<>();
            }
        } catch (IOException e) {
            Log.info("Error reading the file.");
        }
        for (UserModel exist : users) {
            if (exist.getUsername().equalsIgnoreCase(user.getUsername())) {
                return false;
            }
        }
        users.add(user);

        for (UserModel exist : users) {
            String neigh =  exist.getAddress()[0][0].toUpperCase();
            String city = exist.getAddress()[0][1].toUpperCase();
            String[][] r = new String[][] {new String[]{neigh}, new String[]{city}};
            exist.setAddress(r);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(users, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UserModel> loadAllUsers() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<UserModel>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
