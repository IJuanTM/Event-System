package model;

import java.util.*;

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

    /**
     * Ticket methods
     */

    public static void ticketMenu() {
        while (true) {
            try {
                System.out.println("\nTickets:");
                System.out.println("------------------------");
                for (Ticket ticket : Ticket.ticketArray) System.out.println(Color.CYAN + ticket.ticketId + ") " + Color.RESET + ticket.ticketGuest.getGuestName());
                System.out.println(Color.YELLOW + "0) " + Color.RESET + "Go back");
                System.out.println("------------------------");
                System.out.print("Choose option: ");
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input == 0) return;
                else if (input <= ticketArray.size()) {
                    for (Ticket ticket : ticketArray) if (input == ticket.ticketId) printTicketInfo(ticket);
                } else System.out.println(Color.RED + "\nInvalid input, please try again." + Color.RESET);
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    public static void printTicketInfo(Ticket ticket) {
        System.out.println("\nTicket info:");
        System.out.println("=========================");
        System.out.println(Color.YELLOW + "Id: " + Color.RESET + ticket.ticketId);
        System.out.println(Color.PURPLE + "Guest: " + Color.RESET + ticket.ticketGuest.getGuestName());
        System.out.println(Color.RED + "Event: " + Color.RESET + ticket.ticketEvent.getEventName());
        System.out.println(Color.BLUE + "Date: " + Color.RESET + ticket.ticketEvent.getEventDate());
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void addTicket() {
        Event getEvent = null;
        Guest getGuest = null;
        System.out.println("\nAdd ticket:");
        System.out.println("=========================");
        try {
            // New ticketId
            Integer nextId = Collections.max(ticketArray).ticketId + 1;
            System.out.println(Color.YELLOW + "TicketId: " + Color.RESET + nextId);

            // Input guestId
            System.out.print(Color.PURPLE + "GuestId: " + Color.RESET);
            Integer inputGuest = scanner.nextInt();
            scanner.nextLine();

            // Get guest object by Id
            if (!(inputGuest >= Guest.guestArray.size() + 1)) {
                for (Guest guest : Guest.guestArray) if (guest.getGuestId().equals(inputGuest)) getGuest = guest;
            } else throw new NoSuchElementException();

            // Input eventId
            System.out.print(Color.RED + "EventId: " + Color.RESET);
            Integer inputEvent = scanner.nextInt();
            scanner.nextLine();

            // Get event object by Id
            if (!(inputEvent >= Event.eventArray.size() + 1)) {
                for (Event event : Event.eventArray) if (event.getEventId().equals(inputEvent)) getEvent = event;
            } else throw new NoSuchElementException();

            // Push new ticket to array
            ticketArray.add(new Ticket(nextId, getGuest, getEvent));

            // Print success
            System.out.println(Color.GREEN + "\nTicket added!" + Color.RESET);

            // Print added ticket
            for (Ticket ticket : ticketArray) if (ticket.ticketId.equals(nextId)) printTicketInfo(ticket);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        }
    }

    public static void removeTicket() {
        Ticket getTicket = null;
        System.out.println("\nRemove ticket:");
        System.out.println("=========================");
        try {
            // Input ticketId
            System.out.print(Color.YELLOW + "TicketId: " + Color.RESET);
            Integer inputId = scanner.nextInt();
            scanner.nextLine();

            // Get ticket object by Id
            if (!(inputId > Collections.max(ticketArray).ticketId)) {
                for (Ticket ticket : Ticket.ticketArray) if (ticket.ticketId.equals(inputId)) getTicket = ticket;
            } else throw new NoSuchElementException();

            // Remove ticket from array
            ticketArray.remove(getTicket);

            // Print success
            System.out.println(Color.GREEN + "\nTicket " + Color.YELLOW + inputId + Color.GREEN + " removed!" + Color.RESET);

        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        } catch (NoSuchElementException nse) {
            System.out.println(Color.RED + "\nThis input does not exist! Try again." + Color.RESET);
        }
    }

    /**
     * Override Comparable to compare values to array items
     */

    @Override
    public int compareTo(Ticket other) {
        return ticketId.compareTo(other.ticketId);
    }
}