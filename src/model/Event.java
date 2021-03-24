package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

abstract public class Event implements Comparable<Event> {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LocalDate date = LocalDate.now();

    private final Integer eventId;
    private final String eventName;
    private final Integer eventType;
    private final Stage eventStage;
    private final LocalDate eventDate;

    public static final ArrayList<Event> eventArray = new ArrayList<>();

    private static final ArrayList<Artist> artistArray = new ArrayList<>();

    public Event(Integer eventId, String eventName, Integer eventType, Stage eventStage, LocalDate eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStage = eventStage;
        this.eventDate = eventDate;
        Collections.sort(eventArray);
    }

    public static void eventMenu() {
        while (true) {
            try {
                System.out.println("\nEvents:");
                System.out.println("--------------------------------");
                for (Event event : Event.eventArray) System.out.println(Color.CYAN + event.eventId + ") " + Color.RESET + event.eventName);
                System.out.println(Color.YELLOW + "0) " + Color.RESET + "Go back");
                System.out.println("--------------------------------");
                System.out.print("Choose option: ");
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input == 0) return;
                else if (input <= eventArray.size()) {
                    for (Event event : eventArray) if (input == event.eventId) printEventInfo(event);
                } else System.out.println(Color.RED + "\nInvalid input, please try again." + Color.RESET);
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    public static void listEvents() {
        System.out.println(Color.GREEN + "\nUpcoming events: " + Color.RESET);
        for (Event event : Event.eventArray) {
            if (event.eventDate.isAfter(date)) System.out.println("Id: " + Color.YELLOW + event.eventId + Color.RESET + "\tDate: " +
                    Color.BLUE + event.eventDate + Color.RESET + "\tName: " + Color.PURPLE + event.eventName + Color.RESET);
        }
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void printEventInfo(Event event) {
        System.out.println("\nEvent info:");
        System.out.println("=========================");
        ArrayList<Artist> artists = Artist.getArtistByEvent(event);
        System.out.println(Color.YELLOW + "Id: " + Color.RESET + event.eventId);
        System.out.println(Color.PURPLE + "Name: " + Color.RESET + event.eventName);
        System.out.println(Color.GREEN + "Type: " + Color.RESET + event.castTypeName());
        System.out.println(Color.RED + "Stage: " + Color.RESET + event.eventStage.getStageName());
        System.out.println(Color.BLUE + "Date: " + Color.RESET + event.eventDate);
        if (artists.size() == 1) System.out.println(Color.CYAN + "\nArtist:" + Color.RESET);
        else System.out.println(Color.CYAN + "\nArtists:" + Color.RESET);
        for (Artist artist : artists) System.out.println("- " + artist.getArtistName());
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void addEvent() {
        Stage getStage = null;
        System.out.println("\nAdd event:");
        System.out.println("=========================");
        try {
            // New eventId
            Integer nextId = eventArray.size() + 1;
            System.out.println(Color.YELLOW + "EventId: " + Color.RESET + nextId);

            // Input eventName
            System.out.print(Color.PURPLE + "EventName: " + Color.RESET);
            String inputName = scanner.nextLine();

            // Input eventType
            System.out.print(Color.GREEN + "EventType: " + Color.RESET);
            int inputType = scanner.nextInt();
            scanner.nextLine();

            // Input eventStage
            System.out.print(Color.RED + "StageId: " + Color.RESET);
            Integer inputStage = scanner.nextInt();
            scanner.nextLine();

            // Get stage object by Id
            if (!(inputStage >= Stage.stageArray.size() + 1)) {
                for (Stage stage : Stage.stageArray) if (stage.getStageId().equals(inputStage)) getStage = stage;
            } else throw new NoSuchElementException();

            // Input eventDate
            System.out.print(Color.BLUE + "EventDate: " + Color.RESET);
            LocalDate inputDate = LocalDate.parse(scanner.nextLine());

            // Push new event to array by type
            if (inputType == 1) eventArray.add(new Festival(nextId, inputName, inputType, getStage, inputDate));
            else if (inputType == 2) eventArray.add(new Gig(nextId, inputName, inputType, getStage, inputDate));
            else if (inputType == 3) eventArray.add(new Show(nextId, inputName, inputType, getStage, inputDate));
            else throw new NoSuchElementException();

            // Print success
            System.out.println(Color.GREEN + "\nSuccess!" + Color.RESET);

            // Print added event
            for (Event event : eventArray) if (event.eventId.equals(nextId)) printEventInfo(event);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        } catch (DateTimeParseException dtp) {
            System.out.println(Color.YELLOW + "\nWrong date format used! Use yyyy-MM-dd." + Color.RESET);
        }
    }

    public void addArtistToArray(Artist artist) {
        artistArray.add(artist);
    }

    public String castTypeName() {
        return eventType.toString();
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public int compareTo(Event other) {
        return eventId.compareTo(other.eventId);
    }
}
