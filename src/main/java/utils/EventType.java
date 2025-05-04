package utils;

public enum EventType {
    FESTA,
    EVENTO,
    ESPORTIVO,
    SHOW,
    ESTUDO,
    ENCONTRO;

    public static EventType[] getAllEventTypes() {
        return values();
    }
}
