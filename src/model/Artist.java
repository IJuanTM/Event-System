package model;

import java.util.*;

public class Artist implements Comparable<Artist> {
    private static final Scanner scanner = new Scanner(System.in);

    private final Integer artistId;
    private final String artistName;
    private Event artistEvent;

    public static ArrayList<Artist> artistArray = new ArrayList<>();

    public Artist(Integer artistId, String artistName, Event artistEvent) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistEvent = artistEvent;
        Collections.sort(artistArray);
    }

    /**
     * Artist methods
     */

    public static void listArtists() {
        System.out.println(Color.GREEN + "\nArtists in system: " + Color.RESET + "(" + artistArray.size() + ")");
        for (Artist artist : Artist.artistArray) System.out.println("Id: " + Color.YELLOW + artist.artistId + Color.RESET + "\tName: " + Color.PURPLE + artist.artistName + Color.RESET);
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static ArrayList<Artist> getArtistByEvent(Event event) {
        ArrayList<Artist> artistList = new ArrayList<>();
        for (Artist artist : artistArray) if (artist.artistEvent.getEventId().equals(event.getEventId())) artistList.add(artist);
        return artistList;
    }

    public static void printArtistInfo(Artist artist) {
        System.out.println("\nArtist info:");
        System.out.println("=========================");
        System.out.println(Color.YELLOW + "Id: " + Color.RESET + artist.artistId);
        System.out.println(Color.PURPLE + "Name: " + Color.RESET + artist.artistName);
        System.out.println(Color.RED + "Event: " + Color.RESET + artist.artistEvent.getEventName());
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void addArtist() {
        Event getEvent = null;
        System.out.println("\nAdd artist:");
        System.out.println("=========================");
        try {
            // New artistId
            Integer nextId = Collections.max(artistArray).artistId + 1;
            System.out.println(Color.YELLOW + "ArtistId: " + Color.RESET + nextId);

            // Input artistName
            System.out.print(Color.PURPLE + "ArtistName: " + Color.RESET);
            String inputName = scanner.nextLine();

            // Input eventId
            System.out.print(Color.RED + "EventId: " + Color.RESET);
            Integer inputEvent = scanner.nextInt();
            if (inputEvent == 0) throw new NullPointerException();
            scanner.nextLine();

            // Get event object by Id
            if (!(inputEvent >= Event.eventArray.size() + 1)) {
                for (Event event : Event.eventArray) if (event.getEventId().equals(inputEvent)) getEvent = event;
            } else throw new NoSuchElementException();

            // Push new artist to array
            artistArray.add(new Artist(nextId, inputName, getEvent));

            // Print success
            System.out.println(Color.GREEN + "\nArtist added!" + Color.RESET);

            // Print added artist
            for (Artist artist : artistArray) if (artist.artistId.equals(nextId)) printArtistInfo(artist);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        } catch (NullPointerException npe) {
            System.out.println(Color.RED + "\nThis is not a valid input! Try again." + Color.RESET);
        }
    }

    public static void removeArtist() {
        Artist getArtist = null;
        System.out.println("\nRemove artist:");
        System.out.println("=========================");
        try {
            // Input artistId
            System.out.print(Color.YELLOW + "ArtistId: " + Color.RESET);
            Integer inputId = scanner.nextInt();
            if (inputId == 0) throw new NullPointerException();
            scanner.nextLine();

            // Get artist object by Id
            if (!(inputId > Collections.max(artistArray).artistId)) {
                for (Artist artist : Artist.artistArray) if (artist.artistId.equals(inputId)) getArtist = artist;
            } else throw new NoSuchElementException();

            while (true) {
                try {
                    assert getArtist != null;
                    System.out.println("\nAre you sure you want to delete this artist:");
                    System.out.println("(" + Color.YELLOW + getArtist.artistId + Color.RESET + ", " +
                            Color.PURPLE + getArtist.artistName + Color.RESET + ", " +
                            Color.RED + getArtist.artistEvent.getEventName() + Color.RESET + ")");
                    System.out.println("------------");
                    System.out.println(Color.GREEN + "1) " + Color.RESET + "Yes");
                    System.out.println(Color.RED + "2) " + Color.RESET + "No");
                    System.out.println("------------");
                    System.out.print("Choose option: ");
                    int input = scanner.nextInt();
                    scanner.nextLine();
                    if (input == 1) {
                        // Remove ticket from array
                        artistArray.remove(getArtist);

                        // Print success
                        System.out.println(Color.GREEN + "\nArtist " + Color.YELLOW + inputId + Color.GREEN + " removed!" + Color.RESET);
                    } else if (input == 2) {
                        return;
                    } else System.out.println(Color.RED + "\nInvalid input, please try again." + Color.RESET);
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

    /**
     * Getters and setters
     */

    public Integer getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistEvent(Event artistEvent) {
        this.artistEvent = artistEvent;
    }

    /**
     * Override Comparable to compare values to array items
     */

    @Override
    public int compareTo(Artist other) {
        return artistId.compareTo(other.artistId);
    }
}