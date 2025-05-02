package cli;

import services.UserService;
import utils.Log;

public class LoginCLI {

    public void mainScreen(){
        Log.ascii();
        Log.info("Olá, entre abaixo:");
        Log.input("Usuário: ");
        String username = Log.getUserInput();
        Log.input("Senha: ");
        String password = Log.getUserInput();
        UserService userService = new UserService();
        boolean result = userService.loginAuthentication(username, password);
        System.out.println(result);
    }



}
