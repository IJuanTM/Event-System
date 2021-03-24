import model.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        fillStages();
        fillEvents();
        fillArtists();
        fillGuests();
        fillTickets();

        System.out.println(Color.GREEN + "\nWelcome to the Event System\u2122" + Color.RESET);

        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            try {
                System.out.println("\nMain menu:");
                System.out.println("-----------------------------");
                System.out.println("| " + Color.BLUE + "1)" + Color.RESET + " Add items to system \t|");
                System.out.println("| " + Color.CYAN + "2)" + Color.RESET + " View guest tickets \t|");
                System.out.println("| " + Color.CYAN + "3)" + Color.RESET + " View event info \t\t|");
                System.out.println("| " + Color.CYAN + "4)" + Color.RESET + " List upcoming events \t|");
                System.out.println("| " + Color.CYAN + "5)" + Color.RESET + " List all guests \t\t|");
                System.out.println("| " + Color.CYAN + "6)" + Color.RESET + " List all artists \t\t|");
                System.out.println("| " + Color.CYAN + "7)" + Color.RESET + " List all stages \t\t|");
                System.out.println("| " + Color.RED + "0)" + Color.RESET + " Exit program \t\t\t|");
                System.out.println("-----------------------------");
                System.out.print("Choose option: ");
                switch (scanner.nextInt()) {
                    case 1 -> addMenu();
                    case 2 -> Ticket.ticketMenu();
                    case 3 -> Event.eventMenu();
                    case 4 -> Event.listEvents();
                    case 5 -> Guest.listGuests();
                    case 6 -> Artist.listArtists();
                    case 7 -> Stage.listStages();
                    case 0 -> System.exit(0);
                    default -> System.out.println(Color.RED + "\nThis option does not exist, please try again." + Color.RESET);
                }
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    private static void addMenu() {
        while (true) {
            try {
                System.out.println("\nAdd menu:");
                System.out.println("-------------------------");
                System.out.println("| " + Color.CYAN + "1)" + Color.RESET + " Add new ticket \t|");
                System.out.println("| " + Color.CYAN + "2)" + Color.RESET + " Add new guest \t\t|");
                System.out.println("| " + Color.CYAN + "3)" + Color.RESET + " Add new artist \t|");
                System.out.println("| " + Color.CYAN + "4)" + Color.RESET + " Add new event \t\t|");
                System.out.println("| " + Color.CYAN + "5)" + Color.RESET + " Add new stage \t\t|");
                System.out.println("| " + Color.YELLOW + "0)" + Color.RESET + " Go back \t\t\t|");
                System.out.println("-------------------------");
                System.out.print("Choose option: ");
                switch (scanner.nextInt()) {
                    case 1 -> Ticket.addTicket();
                    case 2 -> Guest.addGuest();
                    case 3 -> Artist.addArtist();
                    case 4 -> Event.addEvent();
                    case 5 -> Stage.addStage();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println(Color.RED + "\nThis option does not exist, please try again." + Color.RESET);
                }
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    private static void fillStages() {
        Stage.stageArray.add(new Stage(1, "Tivoli Vredenburg"));
        Stage.stageArray.add(new Stage(2, "Landgraaf"));
        Stage.stageArray.add(new Stage(3, "Biddinghuizen"));
        Stage.stageArray.add(new Stage(4, "Ziggo Dome"));
        Stage.stageArray.add(new Stage(5, "DeLaMar"));
    }

    private static void fillEvents() {
        for (Stage stage : Stage.stageArray) {
            if (stage.getStageId() == 1) {
                Event.eventArray.add(new Gig(2, "Ed Sheeran Tour 2021", 2, stage, LocalDate.parse("2021-07-12")));
                Event.eventArray.add(new Gig(5, "Elton John Tour", 2, stage, LocalDate.parse("2021-04-22")));
            } else if (stage.getStageId() == 2) Event.eventArray.add(new Festival(1, "Pinkpop 2021", 1, stage, LocalDate.parse("2021-08-22")));
            else if (stage.getStageId() == 3) Event.eventArray.add(new Festival(3, "Lowlands 2021", 1, stage, LocalDate.parse("2021-05-07")));
            else if (stage.getStageId() == 4) Event.eventArray.add(new Gig(4, "Red Hot Chilli Peppers Live", 2, stage, LocalDate.parse("2021-05-02")));
            else if (stage.getStageId() == 5) Event.eventArray.add(new Show(6, "Queen Tribute Concert", 3, stage, LocalDate.parse("2021-03-12")));
        }
    }

    private static void fillArtists() {
        for (Event event : Event.eventArray) {
            if (event.getEventId() == 1) {
                Artist.artistArray.add(new Artist(1, "Metallica", event));
                Artist.artistArray.add(new Artist(5, "Rammstein", event));
                Artist.artistArray.add(new Artist(6, "Keane", event));
                Artist.artistArray.add(new Artist(7, "Gorillaz", event));
                Artist.artistArray.add(new Artist(8, "Eminem", event));
                Artist.artistArray.add(new Artist(9, "AC/DC", event));
            } else if (event.getEventId() == 2) Artist.artistArray.add(new Artist(2, "Ed Sheeran", event));
            else if (event.getEventId() == 3) {
                Artist.artistArray.add(new Artist(4, "Muse", event));
                Artist.artistArray.add(new Artist(10, "The Rollingstones", event));
                Artist.artistArray.add(new Artist(11, "John Mayer", event));
                Artist.artistArray.add(new Artist(12, "Imagine Dragons", event));
            } else if (event.getEventId() == 4) Artist.artistArray.add(new Artist(3, "Red Hot Chilli Peppers", event));
            else if (event.getEventId() == 5) Artist.artistArray.add(new Artist(13, "Elton John", event));
        }
    }

    private static void fillGuests() {
        Guest.guestArray.add(new Guest(1, "Henk van Vliet"));
        Guest.guestArray.add(new Guest(2, "Sara van Dam"));
        Guest.guestArray.add(new Guest(3, "Pieter Lemstra"));
        Guest.guestArray.add(new Guest(4, "Soufiane Corvers"));
        Guest.guestArray.add(new Guest(5, "Jaap-Jan Snoeijer"));
        Guest.guestArray.add(new Guest(6, "Malte de Grauw"));
        Guest.guestArray.add(new Guest(7, "Myrne Staphorst"));
        Guest.guestArray.add(new Guest(8, "Yordy Klok"));
        Guest.guestArray.add(new Guest(9, "Janno Cillessen"));
        Guest.guestArray.add(new Guest(10, "Hans Janssen"));
    }

    private static void fillTickets() {
        for (Event event : Event.eventArray) {
            if (event.getEventId() == 1) {
                for (Guest guest : Guest.guestArray) {
                    if (guest.getGuestId() == 1) Ticket.ticketArray.add(new Ticket(1, guest, event));
                    if (guest.getGuestId() == 4) Ticket.ticketArray.add(new Ticket(4, guest, event));
                    if (guest.getGuestId() == 8) Ticket.ticketArray.add(new Ticket(6, guest, event));
                }
            } else if (event.getEventId() == 2) {
                for (Guest guest : Guest.guestArray) {
                    if (guest.getGuestId() == 2) Ticket.ticketArray.add(new Ticket(2, guest, event));
                    if (guest.getGuestId() == 1) Ticket.ticketArray.add(new Ticket(3, guest, event));
                    if (guest.getGuestId() == 4) Ticket.ticketArray.add(new Ticket(7, guest, event));
                    if (guest.getGuestId() == 9) Ticket.ticketArray.add(new Ticket(9, guest, event));
                }
            } else if (event.getEventId() == 3) {
                for (Guest guest : Guest.guestArray) {
                    if (guest.getGuestId() == 3) Ticket.ticketArray.add(new Ticket(8, guest, event));
                    if (guest.getGuestId() == 6) Ticket.ticketArray.add(new Ticket(11, guest, event));
                    if (guest.getGuestId() == 7) Ticket.ticketArray.add(new Ticket(12, guest, event));
                    if (guest.getGuestId() == 2) Ticket.ticketArray.add(new Ticket(10, guest, event));
                }
            } else if (event.getEventId() == 4) {
                for (Guest guest : Guest.guestArray) {
                    if (guest.getGuestId() == 5) Ticket.ticketArray.add(new Ticket(5, guest, event));
                    if (guest.getGuestId() == 10) Ticket.ticketArray.add(new Ticket(13, guest, event));
                }
            }
        }
    }
}