package model;

public class Festival extends Event {
    public Festival(Integer eventId, String eventName, Integer eventType, Stage eventStage, String eventDate) {
        super(eventId, eventName, eventType, eventStage, eventDate);
    }

    @Override
    public String castTypeName() {
        return "Festival";
    }
}
