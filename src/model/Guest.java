package model;

import java.util.ArrayList;
import java.util.Collections;

public class Guest implements Comparable<Guest> {
    private final Integer guestId;
    private final String guestName;
    private final Event guestEvent;

    public static ArrayList<Guest> guestArray = new ArrayList<>();

    public Guest(Integer guestId, String guestName, Event guestEvent) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestEvent = guestEvent;

        Collections.sort(guestArray);
    }

    public static void listGuests() {
        System.out.println(Color.GREEN + "\nGuestlist: " + Color.RESET);
        for (Guest guest : Guest.guestArray) System.out.println("Id: " + Color.YELLOW + guest.guestId + Color.RESET + "\tName: " + Color.PURPLE + guest.guestName + Color.RESET);
    }

    public Integer getGuestId() {
        return this.guestId;
    }

    public String getGuestName() {
        return this.guestName;
    }

    public Event getGuestEvent() {
        return this.guestEvent;
    }

    @Override
    public int compareTo(Guest other) {
        return guestId.compareTo(other.guestId);
    }
}
