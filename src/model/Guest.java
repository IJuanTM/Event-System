package model;

import java.util.*;

public class Guest implements Comparable<Guest> {
    private static final Scanner scanner = new Scanner(System.in);

    private final Integer guestId;
    private final String guestName;

    public static ArrayList<Guest> guestArray = new ArrayList<>();

    public Guest(Integer guestId, String guestName) {
        this.guestId = guestId;
        this.guestName = guestName;
    }

    /**
     * Guest methods
     */

    public static void listGuests() {
        System.out.println(Color.GREEN + "\nGuests coming: " + Color.RESET + "(" + guestArray.size() + ")");
        for (Guest guest : Guest.guestArray) System.out.println("Id: " + Color.YELLOW + guest.guestId + Color.RESET + "\tName: " + Color.PURPLE + guest.guestName + Color.RESET);
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void printGuestInfo(Guest guest) {
        System.out.println("\nGuest info:");
        System.out.println("=========================");
        System.out.println(Color.YELLOW + "Id: " + Color.RESET + guest.guestId);
        System.out.println(Color.PURPLE + "Name: " + Color.RESET + guest.guestName);
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void addGuest() {
        System.out.println("\nAdd guest:");
        System.out.println("=========================");
        try {
            // New guestId
            Integer nextId = Collections.max(guestArray).guestId + 1;
            System.out.println(Color.YELLOW + "GuestId: " + Color.RESET + nextId);

            // Input guestName
            System.out.print(Color.PURPLE + "GuestName: " + Color.RESET);
            String inputName = scanner.nextLine();

            // Push new guest to array
            guestArray.add(new Guest(nextId, inputName));

            // Print success
            System.out.println(Color.GREEN + "\nGuest added!" + Color.RESET);

            // Print added guest
            for (Guest guest : guestArray) if (guest.guestId.equals(nextId)) printGuestInfo(guest);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        }
    }

    public static void removeGuest() {
        Guest getGuest = null;
        System.out.println("\nRemove guest:");
        System.out.println("=========================");
        try {
            // Input guestId
            System.out.print(Color.YELLOW + "GuestId: " + Color.RESET);
            Integer inputId = scanner.nextInt();
            scanner.nextLine();

            // Get guest object by Id
            if (!(inputId > Collections.max(guestArray).guestId)) {
                for (Guest guest : Guest.guestArray) if (guest.guestId.equals(inputId)) getGuest = guest;
            } else throw new NoSuchElementException();

            // Remove guest from array
            guestArray.remove(getGuest);

            // Print success
            System.out.println(Color.GREEN + "\nGuest " + Color.YELLOW + inputId + Color.GREEN + " removed!" + Color.RESET);

        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        }
    }

    /**
     * Getters and setters
     */

    public Integer getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    /**
     * Override Comparable to compare values to array items
     */

    @Override
    public int compareTo(Guest other) {
        return guestId.compareTo(other.guestId);
    }
}