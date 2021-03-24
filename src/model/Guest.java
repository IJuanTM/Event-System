package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest {
    private static final Scanner scanner = new Scanner(System.in);

    private final Integer guestId;
    private final String guestName;

    public static ArrayList<Guest> guestArray = new ArrayList<>();

    public Guest(Integer guestId, String guestName) {
        this.guestId = guestId;
        this.guestName = guestName;
    }

    public static void listGuests() {
        System.out.println(Color.GREEN + "\nGuests: " + Color.RESET);
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
            Integer nextId = guestArray.size() + 1;
            System.out.println(Color.YELLOW + "GuestId: " + Color.RESET + nextId);

            // Input guestName
            System.out.print(Color.PURPLE + "GuestName: " + Color.RESET);
            String inputName = scanner.nextLine();

            // Push new guest to array
            guestArray.add(new Guest(nextId, inputName));

            // Print success
            System.out.println(Color.GREEN + "\nSuccess!" + Color.RESET);

            // Print added guest
            for (Guest guest : guestArray) if (guest.guestId.equals(nextId)) printGuestInfo(guest);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        }
    }

    public Integer getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }
}
