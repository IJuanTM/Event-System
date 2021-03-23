package model;

import java.util.ArrayList;

public class Guest {
    private final Integer guestId;
    private final String guestName;

    public static ArrayList<Guest> guestArray = new ArrayList<>();

    public Guest(Integer guestId, String guestName) {
        this.guestId = guestId;
        this.guestName = guestName;
    }

    public Integer getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }
}
