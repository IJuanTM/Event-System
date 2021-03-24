package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Stage {
    private static final Scanner scanner = new Scanner(System.in);

    private final Integer stageId;
    private final String stageName;

    public static ArrayList<Stage> stageArray = new ArrayList<>();

    public Stage(Integer stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;
    }

    public static void listStages() {
        System.out.println(Color.GREEN + "\nStages: " + Color.RESET);
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
            // new stageId
            Integer nextId = stageArray.size() + 1;
            System.out.println(Color.YELLOW + "StageId: " + Color.RESET + nextId);

            // Input stageName
            System.out.print(Color.PURPLE + "StageName: " + Color.RESET);
            String inputName = scanner.nextLine();

            // Push new stage to array
            stageArray.add(new Stage(nextId, inputName));

            // Print success
            System.out.println(Color.GREEN + "\nSuccess!" + Color.RESET);

            // Print added stage
            for (Stage stage : stageArray) if (stage.stageId.equals(nextId)) printStageInfo(stage);
        } catch (InputMismatchException ime) {
            System.out.println(Color.RED + "\nWrong input type! Try again." + Color.RESET);
            scanner.nextLine();
        }
    }

    public Integer getStageId() {
        return stageId;
    }

    public String getStageName() {
        return stageName;
    }
}
