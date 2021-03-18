import java.util.Scanner;

import model.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        fillStages();
        fillEvents();
        fillArtists();
        fillGuests();

        System.out.println(Color.GREEN + "\nWelcome to the Event System\u2122" + Color.RESET);

        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\nMain menu:");
            System.out.println("-----------------------------");
            System.out.println("| " + Color.CYAN + "1)" + Color.RESET + " View event options \t|");
            System.out.println("| " + Color.CYAN + "2)" + Color.RESET + " List upcoming events \t|");
            System.out.println("| " + Color.CYAN + "3)" + Color.RESET + " List all artists \t\t|");
            System.out.println("| " + Color.CYAN + "4)" + Color.RESET + " List all guests \t\t|");
            System.out.println("| " + Color.RED + "0)" + Color.RESET + " Exit program \t\t\t|");
            System.out.println("-----------------------------");
            System.out.print("Choose option: ");
            switch (scanner.nextInt()) {
                case 1 -> Event.eventMenu();
                case 2 -> Event.listEvents();
                case 3 -> Artist.listArtists();
                case 4 -> Guest.listGuests();
                case 0 -> System.exit(0);
                default -> System.out.println(Color.RED + "\nThis option does not exist, please try again." + Color.RESET);
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
                Event.eventArray.add(new Gig(2, "Ed Sheeran Tour 2021", 2, stage, "2021-07-12"));
                Event.eventArray.add(new Gig(5, "Elton John Tour", 2, stage, "2021-04-22"));
            } else if (stage.getStageId() == 2) Event.eventArray.add(new Festival(1, "Pinkpop 2021", 1, stage, "2021-08-22"));
            else if (stage.getStageId() == 3) Event.eventArray.add(new Festival(3, "Lowlands 2021", 1, stage, "2021-05-07"));
            else if (stage.getStageId() == 4) Event.eventArray.add(new Gig(4, "Red Hot Chilli Peppers Live", 2, stage, "2021-04-22"));
            else if (stage.getStageId() == 5) Event.eventArray.add(new Show(6, "Queen Tribute Concert", 3, stage, "2021-04-22"));
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
        for (Event event : Event.eventArray) {
            if (event.getEventId() == 1) {
                Guest.guestArray.add(new Guest(1, "Henk Kaas", event));
                Guest.guestArray.add(new Guest(2, "Tim Cocx", event));
                Guest.guestArray.add(new Guest(3, "Karel", event));
            }
        }
    }
}