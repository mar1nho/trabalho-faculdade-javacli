import services.EventService;
import test.EventsTester;
import utils.Log;

public class Main {

    private static final EventsTester tester = new EventsTester();

    public static void main(String[] args) {
        Log.ascii();
        tester.run();
    }
}