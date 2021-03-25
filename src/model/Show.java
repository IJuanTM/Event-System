package model;

import java.time.LocalDate;

public class Show extends Event {
    public Show(Integer eventId, String eventName, Integer eventType, Stage eventStage, LocalDate eventDate) {
        super(eventId, eventName, eventType, eventStage, eventDate);
    }

    /**
     * Override typeName value
     */

    @Override
    public String castTypeName() {
        return "Show";
    }
}