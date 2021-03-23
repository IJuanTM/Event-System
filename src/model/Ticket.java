package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ticket implements Comparable<Ticket> {
    private static final Scanner scanner = new Scanner(System.in);

    private final Integer ticketId;
    private final Guest ticketGuest;
    private final Event ticketEvent;

    public static ArrayList<Ticket> ticketArray = new ArrayList<>();

    public Ticket(Integer ticketId, Guest ticketGuest, Event ticketEvent) {
        this.ticketId = ticketId;
        this.ticketGuest = ticketGuest;
        this.ticketEvent = ticketEvent;
        Collections.sort(ticketArray);
    }

    public static void ticketMenu() {
        while (true) {
            System.out.println("\nTickets:");
            System.out.println("=========================");
            for (Ticket ticket : Ticket.ticketArray) System.out.println(Color.CYAN + ticket.ticketId + ") " + Color.RESET + ticket.ticketGuest.getGuestName());
            System.out.println(Color.YELLOW + "0) " + Color.RESET + "Go back");
            System.out.println("=========================");
            System.out.print("Choose option: ");
            int input = scanner.nextInt();
            if (input == 0) return;
            else if (input <= ticketArray.size()) {
                for (Ticket ticket : ticketArray) if (input == ticket.ticketId) printTicketInfo(ticket);
            } else System.out.println(Color.RED + "\nInvalid input, please try again." + Color.RESET);
        }
    }

    public static void listTickets() {
        System.out.println(Color.GREEN + "\nTickets: " + Color.RESET);
        for (Ticket ticket : Ticket.ticketArray) System.out.println("Id: " + Color.YELLOW + ticket.ticketId + Color.RESET + "\tGuest: " + Color.PURPLE + ticket.ticketGuest.getGuestName() + Color.RESET);
    }

    public static void printTicketInfo(Ticket ticket) {
        System.out.println(Color.YELLOW + "\nId: " + Color.RESET + ticket.ticketId);
        System.out.println(Color.PURPLE + "Guest: " + Color.RESET + ticket.ticketGuest.getGuestName());
        System.out.println(Color.RED + "Event: " + Color.RESET + ticket.ticketEvent.getEventName());
        System.out.println(Color.BLUE + "Date: " + Color.RESET + ticket.ticketEvent.getEventDate());
        System.out.print(Color.YELLOW + "\nBack to menu (ENTER): " + Color.RESET);
        scanner.nextLine();
        scanner.nextLine();
    }

    @Override
    public int compareTo(Ticket other) {
        return ticketId.compareTo(other.ticketId);
    }
}
