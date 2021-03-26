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

    public Event(Integer eventId, String eventName, Integer eventType, Stage eventStage, LocalDate eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStage = eventStage;
        this.eventDate = eventDate;
        Collections.sort(eventArray);
    }

    /**
     * Event methods
     */

    public static void eventMenu() {
        while (true) {
            try {
                System.out.println("\nEvents: (" + eventArray.size() + ")");
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
        System.out.println(Color.GREEN + "\nUpcoming events: " + Color.RESET + "(" + eventArray.size() + ")");
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
        for (Artist artist : artists) System.out.println(artist.getArtistId() + " " + artist.getArtistName());
        while (true) {
            try {
                System.out.println("\nOptions:");
                System.out.println("--------------------------------");
                System.out.println(Color.CYAN + "1)" + Color.RESET + " Add artist");
                System.out.println(Color.YELLOW + "0)" + Color.RESET + " Go back");
                System.out.println("--------------------------------");
                System.out.print("Choose option: ");
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input == 0) return;
                else if (input == 1) addArtistToEvent(event);
                else System.out.println(Color.RED + "\nInvalid input, please try again." + Color.RESET);
                return;
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    public static void addEvent() {
        Stage getStage = null;
        System.out.println("\nAdd event:");
        System.out.println("=========================");
        try {
            // New eventId
            Integer nextId = Collections.max(eventArray).eventId + 1;
            System.out.println(Color.YELLOW + "EventId: " + Color.RESET + nextId);

            // Input eventName
            System.out.print(Color.PURPLE + "EventName: " + Color.RESET);
            String inputName = scanner.nextLine();

            // Input eventType
            System.out.print(Color.GREEN + "EventType: " + Color.RESET);
            int inputType = scanner.nextInt();
            if (inputType == 0) throw new NullPointerException();
            scanner.nextLine();

            // Input eventStage
            System.out.print(Color.RED + "StageId: " + Color.RESET);
            Integer inputStage = scanner.nextInt();
            if (inputStage == 0) throw new NullPointerException();
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
            System.out.println(Color.GREEN + "\nEvent added!" + Color.RESET);

            // Print added event
            for (Event event : eventArray) if (event.eventId.equals(nextId)) printEventInfo(event);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        } catch (DateTimeParseException dtp) {
            System.out.println(Color.YELLOW + "\nWrong date format used! Use yyyy-MM-dd." + Color.RESET);
        } catch (NullPointerException npe) {
            System.out.println(Color.RED + "\nThis is not a valid input! Try again." + Color.RESET);
        }
    }

    public static void removeEvent() {
        Event getEvent = null;
        System.out.println("\nRemove event:");
        System.out.println("=========================");
        try {
            // Input eventId
            System.out.print(Color.YELLOW + "EventId: " + Color.RESET);
            Integer inputId = scanner.nextInt();
            if (inputId == 0) throw new NullPointerException();
            scanner.nextLine();

            // Get event object by Id
            if (!(inputId > Collections.max(eventArray).eventId)) {
                for (Event event : Event.eventArray) if (event.eventId.equals(inputId)) getEvent = event;
            } else throw new NoSuchElementException();

            while (true) {
                try {
                    assert getEvent != null;
                    System.out.println("\nAre you sure you want to delete this event:");
                    System.out.println("(" + Color.YELLOW + getEvent.eventId+ Color.RESET + ", " +
                            Color.PURPLE + getEvent.eventName + Color.RESET + ", " +
                            Color.RED + getEvent.eventType + Color.RESET + ")");
                    System.out.println("------------");
                    System.out.println(Color.GREEN + "1) " + Color.RESET + "Yes");
                    System.out.println(Color.RED + "2) " + Color.RESET + "No");
                    System.out.println("------------");
                    System.out.print("Choose option: ");
                    int input = scanner.nextInt();
                    scanner.nextLine();
                    if (input == 1) {
                        // Remove ticket from array
                        eventArray.remove(getEvent);

                        // Print success
                        System.out.println(Color.GREEN + "\nEvent " + Color.YELLOW + inputId + Color.GREEN + " removed!" + Color.RESET);
                    } else if (input == 2) return;
                    else System.out.println(Color.RED + "\nInvalid input, please try again." + Color.RESET);
                    return;
                } catch (InputMismatchException ime) {
                    System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                    scanner.nextLine();
                }
            }
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        } catch (NullPointerException npe) {
            System.out.println(Color.RED + "\nThis is not a valid input! Try again." + Color.RESET);
        }
    }

    public static void addArtistToEvent(Event event) {
        System.out.println("\nAdd artist:");
        System.out.println("=========================");
        try {
            // Input artistId
            System.out.print(Color.YELLOW + "ArtistId: " + Color.RESET);
            Integer inputArtist = scanner.nextInt();
            scanner.nextLine();

            // Set artist object by Id
            if (!(inputArtist >= Artist.artistArray.size() + 1)) {
                for (Artist artist : Artist.artistArray) if (artist.getArtistId().equals(inputArtist)) artist.setArtistEvent(event);
            } else throw new NoSuchElementException();

            // Print success
            System.out.println(Color.GREEN + "\nArtist added!" + Color.RESET);

            // Print added event
            printEventInfo(event);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        }
    }

    public String castTypeName() {
        return eventType.toString();
    }

    /**
     * Getters and setters
     */

    public Integer getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    /**
     * Override Comparable to compare values to array items
     */

    @Override
    public int compareTo(Event other) {
        return eventId.compareTo(other.eventId);
    }
}