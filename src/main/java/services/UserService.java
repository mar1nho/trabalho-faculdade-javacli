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
}
