package cli;

import models.UserModel;
import services.UserService;
import utils.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class LoginCLI {

    private final UserService userService;

    private final String[] states = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};

    public LoginCLI() {
        userService = new UserService();
    }

    public void mainScreen(){
        Log.ascii();
        Log.info("Olá, seja bem-vindo ao 'TáRolando?'");
        Log.info("Escolha entre as opções abaixo:");
        Log.info("1. Entrar");
        Log.info("2. Registrar");
        Log.input(" ");
        String response = Log.getUserInput();
        switch(response){
            case "1"->{
                loginScreen();
            }
            case "2"->{
                registerScreen();
            }
            default -> {
                Log.info("Nenhuma opção reconhecida na sua resposta...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mainScreen();
            }
        }
    }

    private void registerScreen(){
        Log.ascii();
        Log.info("Iremos agora criar uma conta com apenas 2 informações!");
        Log.input("Username: ");
        String usr = Log.getUserInput();
        Log.input("Password: ");
        String pwd = Log.getUserInput();
        Log.input("Bairro: ");
        String neighborhood = Log.getUserInput();
        Log.input("Cidade (UF): ");
        String city = Log.getUserInput();

        boolean isValid = false;

        for (String s : states) {
            if (city.equalsIgnoreCase(s)) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            Log.info("Você deve inserir o código da UF do seu Estado:");
            Log.info(Arrays.toString(states));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            registerScreen();
        }



        if(usr.isEmpty() || pwd.isEmpty() || neighborhood.isEmpty() || city.isEmpty()){
            Log.info("Informações em branco, retornando ao registro, insira informações nos campos!!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            registerScreen();
        }


        if(userService.saveUser(new UserModel(usr,pwd, new HashMap<>(),new String[][]{{neighborhood.trim(), city.trim()}}))){
            Log.info("Parabéns, conta criada com sucesso!");
            Log.info("Retornando ao menu.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mainScreen();
        } else {
            Log.info("Usuário inválido, tente novamente!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            registerScreen();
        }
    }

    public void loginScreen(){
        Log.ascii();
        Log.info("Por favor, entre com suas credenciais abaixo.");
        Log.input("Username: ");
        String usr = Log.getUserInput();
        Log.input("Password: ");
        String pwd = Log.getUserInput();

        boolean _auth_ = userService.loginAuthentication(usr,pwd);
        if(_auth_){
            Optional<UserModel> user = userService.getAllUsers()
                    .stream()
                    .filter(u -> usr.equals(u.getUsername()))
                    .findFirst();
            if(user.isPresent()){
                UserScreenCLI userScreen = new UserScreenCLI(user.get());
                userScreen._initialize();
            }
        }
    }

}
