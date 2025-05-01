package cli;

import utils.Log;

public class LoginCLI {

    public void mainScreen(){
        Log.ascii();
        Log.info("Lorem ipsum dolor sit amet");
        Log.input("Username: ");
        String username = String.valueOf(Log.getUserInput());
        Log.input("Password: ");
        String password = String.valueOf(Log.getUserInput());
        System.out.println(username);
        System.out.println(password);
    }



}
