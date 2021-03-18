package model;

public class Show extends Event {
    public Show(Integer eventId, String eventName, Integer eventType, Stage eventStage, String eventDate) {
        super(eventId, eventName, eventType, eventStage, eventDate);
    }

    @Override
    public String castTypeName() {
        return "Show\t";
    }
}
