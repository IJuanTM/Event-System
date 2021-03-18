package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract public class Event implements Comparable<Event> {
    private static final Scanner scanner = new Scanner(System.in);

    private final Integer eventId;
    private final String eventName;
    private final Integer eventType;
    private final Stage eventStage;
    private final String eventDate;

    public static final ArrayList<Event> eventArray = new ArrayList<>();

    public Event(Integer eventId, String eventName, Integer eventType, Stage eventStage, String eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStage = eventStage;
        this.eventDate = eventDate;

        Collections.sort(eventArray);
    }

    public static void eventMenu() {
        while (true) {
            System.out.println("\nEvents:");
            System.out.println("=================================");
            for (Event event : Event.eventArray) System.out.println(Color.CYAN + event.eventId + ") " + Color.RESET + event.eventName);
            System.out.println(Color.YELLOW + "0) " + Color.RESET + "Go back");
            System.out.println("=================================");
            System.out.print("Choose option: ");
            int input = scanner.nextInt();
            if (input == 0) return;
            else if (input <= eventArray.size()) {
                for (Event event : eventArray) if (input == event.eventId) event.printEventInfo(event);
            } else System.out.println(Color.RED + "\nInvalid input, please try again." + Color.RESET);
        }
    }

    public static void listEvents() {
        System.out.println(Color.GREEN + "\nUpcoming events: " + Color.RESET);
        for (Event event : Event.eventArray) System.out.println("Id: " + Color.YELLOW + event.eventId + Color.RESET + "\tDate: " + Color.BLUE + event.eventDate + Color.RESET + "\tName: " + Color.PURPLE + event.eventName + Color.RESET);
    }

    public void printEventInfo(Event event) {
        ArrayList<Artist> artists = Artist.getArtistByEvent(event);
        System.out.println(Color.YELLOW + "\nId: " + Color.RESET + eventId);
        System.out.println(Color.PURPLE + "Name: " + Color.RESET + eventName);
        System.out.println(Color.GREEN + "Type: " + Color.RESET + castTypeName());
        System.out.println(Color.RED + "Stage: " + Color.RESET + eventStage.getStageName());
        System.out.println(Color.BLUE + "Date: " + Color.RESET + eventDate);
        System.out.println(Color.CYAN + "\nArtist(s):" + Color.RESET);
        for (Artist artist : artists) System.out.println("- " + artist.getArtistName());
    }

    public String castTypeName() {
        return eventType.toString();
    }

    public Integer getEventId() {
        return this.eventId;
    }

    @Override
    public int compareTo(Event other) {
        return eventId.compareTo(other.eventId);
    }
}
