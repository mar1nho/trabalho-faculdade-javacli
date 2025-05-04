import cli.LoginCLI;
import test.EventsTester;

public class Main {

    private static final LoginCLI loginCLI = new LoginCLI();

    public static void main(String[] args) {
        Thread _init = new Thread(loginCLI);
        _init.start();
    }
}