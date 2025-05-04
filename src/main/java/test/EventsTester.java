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

    private final UserService userService = new UserService();
    private final LoginCLI loginCLI = new LoginCLI();
    private final EventService eventService = new EventService();;

    public EventsTester() {
    }

    @Override
    public void run() {


    }

}
