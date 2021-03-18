package model;

public class Gig extends Event {
    public Gig(Integer eventId, String eventName, Integer eventType, Stage eventStage, String eventDate) {
        super(eventId, eventName, eventType, eventStage, eventDate);
    }

    @Override
    public String castTypeName() {
        return "Gig\t";
    }
}
