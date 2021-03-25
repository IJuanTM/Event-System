package model;

import java.util.*;

public class Stage implements Comparable<Stage> {
    private static final Scanner scanner = new Scanner(System.in);

    private final Integer stageId;
    private final String stageName;

    public static ArrayList<Stage> stageArray = new ArrayList<>();

    public Stage(Integer stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;
    }

    /**
     * Stage methods
     */

    public static void listStages() {
        System.out.println(Color.GREEN + "\nStages in system: " + Color.RESET + "(" + stageArray.size() + ")");
        for (Stage stage : Stage.stageArray) System.out.println("Id: " + Color.YELLOW + stage.stageId + Color.RESET + "\tName: " + Color.PURPLE + stage.stageName + Color.RESET);
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void printStageInfo(Stage stage) {
        System.out.println("\nStage info:");
        System.out.println("=========================");
        System.out.println(Color.YELLOW + "Id: " + Color.RESET + stage.stageId);
        System.out.println(Color.PURPLE + "Name: " + Color.RESET + stage.stageName);
        System.out.print("\nBack to menu (" + Color.YELLOW + "ENTER" + Color.RESET + "): ");
        scanner.nextLine();
    }

    public static void addStage() {
        System.out.println("\nAdd stage:");
        System.out.println("=========================");
        try {
            // New stageId
            Integer nextId = Collections.max(stageArray).stageId + 1;
            System.out.println(Color.YELLOW + "StageId: " + Color.RESET + nextId);

            // Input stageName
            System.out.print(Color.PURPLE + "StageName: " + Color.RESET);
            String inputName = scanner.nextLine();

            // Push new stage to array
            stageArray.add(new Stage(nextId, inputName));

            // Print success
            System.out.println(Color.GREEN + "\nStage added!" + Color.RESET);

            // Print added stage
            for (Stage stage : stageArray) if (stage.stageId.equals(nextId)) printStageInfo(stage);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        }
    }

    public static void removeStage() {
        Stage getStage = null;
        System.out.println("\nRemove stage:");
        System.out.println("=========================");
        try {
            // Input stageId
            System.out.print(Color.YELLOW + "StageId: " + Color.RESET);
            Integer inputId = scanner.nextInt();
            scanner.nextLine();

            // Get stage object by Id
            if (!(inputId > Collections.max(stageArray).stageId)) {
                for (Stage stage : Stage.stageArray) if (stage.stageId.equals(inputId)) getStage = stage;
            } else throw new NoSuchElementException();

            // Remove stage from array
            stageArray.remove(getStage);

            // Print success
            System.out.println(Color.GREEN + "\nStage " + Color.YELLOW + inputId + Color.GREEN + " removed!" + Color.RESET);

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

    public Integer getStageId() {
        return stageId;
    }

    public String getStageName() {
        return stageName;
    }

    /**
     * Override Comparable to compare values to array items
     */

    @Override
    public int compareTo(Stage other) {
        return stageId.compareTo(other.stageId);
    }
}