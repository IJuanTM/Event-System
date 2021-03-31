import model.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        fillUsers();
        fillGuests();
        fillStages();
        fillEvents();
        fillArtists();
        fillTickets();

        System.out.println(Color.GREEN + "\nWelcome to the Event System\u2122" + Color.RESET);

        loginUser();
    }

    /**
     * Menus
     */

    private static void loginUser() {
        while (true) {
            try {
                System.out.println(Color.CYAN + "\nLogin menu:" + Color.RESET);
                System.out.println(Color.CYAN + "---------------------" + Color.RESET);
                System.out.println(Color.CYAN + "| " + Color.GREEN + "1)" + Color.RESET + " Login\t\t\t" + Color.CYAN + "|" + Color.RESET);
                System.out.println(Color.CYAN + "| " + Color.RED + "0)" + Color.RESET + " Exit program\t" + Color.CYAN + "|" + Color.RESET);
                System.out.println(Color.CYAN + "---------------------" + Color.RESET);
                System.out.print("Choose option: ");
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input == 1) {
                    System.out.print("\nUsername: ");
                    String inputName = scanner.nextLine();
                    System.out.print("Password: ");
                    String inputPassword = scanner.nextLine();
                    for (User user : User.userArray) {
                        if ((user.getUserName().equals(inputName)) && (user.getUserPassword().equals(User.encryptPassword(inputPassword)))) {
                            System.out.println(Color.GREEN + "\nLogin successful" + Color.RESET);
                            mainMenu();
                        } else System.out.println(Color.RED + "\nWrong credentials! Try again." + Color.RESET);
                    }
                } else if (input == 0) {
                    System.out.println(Color.RED + "\nExiting program..." + Color.RESET);
                    System.exit(0);
                } else if (input == 1234) {
                    System.out.println("\n============================================================");
                    System.out.print(Color.RED + "ENTER NEW PASSWORD: " + Color.RESET);
                    String inputNewPassword = scanner.nextLine();
                    System.out.println(Color.YELLOW + "GENERATED HASH: " + Color.RESET + User.encryptPassword(inputNewPassword) + Color.RESET);
                    System.out.println("============================================================");
                }
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    private static void mainMenu() {
        while (true) {
            try {
                System.out.println(Color.YELLOW + "\nMain menu:" + Color.RESET);
                System.out.println(Color.YELLOW + "---------------------------------" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.BLUE + "1)" + Color.RESET + " Add items to system\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.PURPLE + "2)" + Color.RESET + " Remove items from system\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.CYAN + "3)" + Color.RESET + " View guest tickets\t\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.CYAN + "4)" + Color.RESET + " View event info\t\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.CYAN + "5)" + Color.RESET + " List upcoming events\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.CYAN + "6)" + Color.RESET + " List all guests\t\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.CYAN + "7)" + Color.RESET + " List all artists\t\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.CYAN + "8)" + Color.RESET + " List all stages\t\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "| " + Color.RED + "0)" + Color.RESET + " Log out\t\t\t\t\t" + Color.YELLOW + "|" + Color.RESET);
                System.out.println(Color.YELLOW + "---------------------------------" + Color.RESET);
                System.out.print("Choose option: ");
                switch (scanner.nextInt()) {
                    case 0 -> {
                        System.out.println(Color.RED + "\nLogging out..." + Color.RESET);
                        return;
                    }
                    case 1 -> addMenu();
                    case 2 -> removeMenu();
                    case 3 -> Ticket.ticketMenu();
                    case 4 -> Event.eventMenu();
                    case 5 -> Event.listEvents();
                    case 6 -> Guest.listGuests();
                    case 7 -> Artist.listArtists();
                    case 8 -> Stage.listStages();
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
                System.out.println(Color.BLUE + "\nAdd menu:" + Color.RESET);
                System.out.println(Color.BLUE + "-------------------------" + Color.RESET);
                System.out.println(Color.BLUE + "| " + Color.CYAN + "1)" + Color.RESET + " Add new ticket\t\t" + Color.BLUE + "|" + Color.RESET);
                System.out.println(Color.BLUE + "| " + Color.CYAN + "2)" + Color.RESET + " Add new guest\t\t" + Color.BLUE + "|" + Color.RESET);
                System.out.println(Color.BLUE + "| " + Color.CYAN + "3)" + Color.RESET + " Add new artist\t\t" + Color.BLUE + "|" + Color.RESET);
                System.out.println(Color.BLUE + "| " + Color.CYAN + "4)" + Color.RESET + " Add new event\t\t" + Color.BLUE + "|" + Color.RESET);
                System.out.println(Color.BLUE + "| " + Color.CYAN + "5)" + Color.RESET + " Add new stage\t\t" + Color.BLUE + "|" + Color.RESET);
                System.out.println(Color.BLUE + "| " + Color.YELLOW + "0)" + Color.RESET + " Go back\t\t\t" + Color.BLUE + "|" + Color.RESET);
                System.out.println(Color.BLUE + "-------------------------" + Color.RESET);
                System.out.print("Choose option: ");
                switch (scanner.nextInt()) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> Ticket.addTicket();
                    case 2 -> Guest.addGuest();
                    case 3 -> Artist.addArtist();
                    case 4 -> Event.addEvent();
                    case 5 -> Stage.addStage();
                    default -> System.out.println(Color.RED + "\nThis option does not exist, please try again." + Color.RESET);
                }
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    private static void removeMenu() {
        while (true) {
            try {
                System.out.println(Color.PURPLE + "\nRemove menu:" + Color.RESET);
                System.out.println(Color.PURPLE + "-------------------------" + Color.RESET);
                System.out.println(Color.PURPLE + "| " + Color.CYAN + "1)" + Color.RESET + " Remove ticket\t\t" + Color.PURPLE + "|" + Color.RESET);
                System.out.println(Color.PURPLE + "| " + Color.CYAN + "2)" + Color.RESET + " Remove guest\t\t" + Color.PURPLE + "|" + Color.RESET);
                System.out.println(Color.PURPLE + "| " + Color.CYAN + "3)" + Color.RESET + " Remove artist\t\t" + Color.PURPLE + "|" + Color.RESET);
                System.out.println(Color.PURPLE + "| " + Color.CYAN + "4)" + Color.RESET + " Remove event\t\t" + Color.PURPLE + "|" + Color.RESET);
                System.out.println(Color.PURPLE + "| " + Color.CYAN + "5)" + Color.RESET + " Remove stage\t\t" + Color.PURPLE + "|" + Color.RESET);
                System.out.println(Color.PURPLE + "| " + Color.YELLOW + "0)" + Color.RESET + " Go back\t\t\t" + Color.PURPLE + "|" + Color.RESET);
                System.out.println(Color.PURPLE + "-------------------------" + Color.RESET);
                System.out.print("Choose option: ");
                switch (scanner.nextInt()) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> Ticket.removeTicket();
                    case 2 -> Guest.removeGuest();
                    case 3 -> Artist.removeArtist();
                    case 4 -> Event.removeEvent();
                    case 5 -> Stage.removeStage();
                    default -> System.out.println(Color.RED + "\nThis option does not exist, please try again." + Color.RESET);
                }
            } catch (InputMismatchException ime) {
                System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
                scanner.nextLine();
            }
        }
    }

    /**
     * Filling the arrays with starting items
     */

    private static void fillUsers() {
        User.userArray.add(new User("Admin", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"));
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
                Event.eventArray.add(new Gig(2, "Ed Sheeran Tour 2021", 2, stage, LocalDate.parse("2021-03-12")));
                Event.eventArray.add(new Gig(5, "Elton John Tour", 2, stage, LocalDate.parse("2021-09-22")));
            } else if (stage.getStageId() == 2) Event.eventArray.add(new Festival(1, "Pinkpop 2021", 1, stage, LocalDate.parse("2021-05-22")));
            else if (stage.getStageId() == 3) Event.eventArray.add(new Festival(3, "Lowlands 2021", 1, stage, LocalDate.parse("2021-08-07")));
            else if (stage.getStageId() == 4) Event.eventArray.add(new Gig(4, "Red Hot Chilli Peppers Live", 2, stage, LocalDate.parse("2021-05-02")));
            else if (stage.getStageId() == 5) Event.eventArray.add(new Show(6, "Queen Tribute Concert", 3, stage, LocalDate.parse("2021-04-06")));
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