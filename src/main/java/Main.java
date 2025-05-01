import services.EventService;

public class Main {
    public static void main(String[] args) {
        EventService eventService = new EventService();
        eventService.loadEventListAsModel().forEach(eventModel -> {
            System.out.println(eventModel.getName());
        });
    }
}