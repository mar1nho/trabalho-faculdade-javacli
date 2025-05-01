package test;

import cli.LoginCLI;
import models.EventModel;
import models.UserModel;
import services.EventService;
import services.UserService;
import utils.EventType;
import utils.Log;

import java.time.LocalDateTime;
import java.util.*;

public class EventsTester implements Runnable {

    private UserService userService;
    private LoginCLI loginCLI = new LoginCLI();

    public EventsTester() {
        this.userService = new UserService();
    }

    @Override
    public void run() {
        /*UserService userService = new UserService();
        Map<Integer, String> events = new HashMap<>();
        UserModel userModel = new UserModel("medina", "121199", events);
        if (userService.saveUser(userModel)){
            Log.info("Sucesso ao salvar login!");
        } else {
            Log.info("Erro ao salvar login!");
            Log.info("Username já em uso.");
        }*/
/*
        List<UserModel> list = userService.getAllUsers();
        list.forEach(u -> {
            System.out.println(u.getUsername());
        });*/
        loginCLI.mainScreen();
    }

}
