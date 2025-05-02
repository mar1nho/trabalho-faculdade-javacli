package services;


import controllers.UserController;
import models.UserModel;

import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

public class UserService {
    private final UserController userController;

    public UserService() {
        this.userController = new UserController();
    }

    public boolean saveUser(UserModel userModel){
        return userController.saveUserToJSON(userModel);
    }

    public List<UserModel> getAllUsers(){
        return userController.loadAllUsers();
    }

    public boolean loginAuthentication(String username, String password) {
        List<UserModel> users = userController.loadAllUsers();
        for (UserModel user : users) {
            if (user.getUsername().trim().equals(username.trim()) && user.getPassword().trim().equals(password.trim())) {
                return true;
            }
        }
        return false;
    }
}
