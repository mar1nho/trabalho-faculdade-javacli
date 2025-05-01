package services;


import controllers.UserController;
import models.UserModel;

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

    public boolean loginAuthentication(String username, String password){
        boolean hasResult = false;
        List<UserModel> users = userController.loadAllUsers();
        for(UserModel user : users){
            hasResult = user.getUsername().equals(username) && user.getPassword().equals(password);
        }
        return hasResult;
    }
}
