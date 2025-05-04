package utils;

public class Sleep {
    public static void seconds(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
