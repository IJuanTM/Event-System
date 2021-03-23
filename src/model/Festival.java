package model;

import java.time.LocalDate;

public class Festival extends Event {
    public Festival(Integer eventId, String eventName, Integer eventType, Stage eventStage, LocalDate eventDate) {
        super(eventId, eventName, eventType, eventStage, eventDate);
    }

    @Override
    public String castTypeName() {
        return "Festival";
    }
}
