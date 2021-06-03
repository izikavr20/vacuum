package ribbon.ex.ui;

import ribbon.ex.service.VacuumServiceProvider;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class CommandLineUI {

    private final VacuumServiceProvider provider;

    public CommandLineUI(VacuumServiceProvider provider) {
        this.provider = provider;
    }

    public void startUI() {

        Scanner sc = new Scanner(System.in);
        boolean toContinue;
        do {
            printMainMenu();
            toContinue = handleUserAction(sc.nextLine());//handle null string
        } while (toContinue);
        System.out.println("Terminating...");
        provider.terminate();
    }

    private boolean handleUserAction(String nextLine) {

        boolean toContinue = true;
        switch (nextLine) {
            case "1" -> add();
            case "2" -> handleRobotActions();
            case "3" -> toContinue = false; //quit
            default -> invalid();
        }
        return toContinue;
    }

    private void printMainMenu() {

        System.out.println("""
                Choose option:
                1. Add new robot
                2. Choose robot
                3. Quit
                """);
    }

    private void handleRobotActions() {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> list = provider.getRobotsNames();

        printRobots(list);
        printChooseMsg();
        var robotName = sc.nextLine().toLowerCase(Locale.ROOT);

        if (!provider.robotNameTaken(robotName)) {
            printRobotExist(robotName);

        } else {
            printRobotActions();
            var robotAction = sc.nextLine();
            selectRobotAction(robotAction, robotName);
        }
    }

    private void printRobotExist(String name) {
        System.out.println("robot " + name + " doesn't exist");
    }

    private void selectRobotAction(String robotAction, String robotName) {

        switch (robotAction) {
            case "1" -> provider.clean(robotName);
            case "2" -> provider.reboot(robotName);
            case "3" -> provider.delete(robotName);
            case "4" -> provider.fix(robotName);
            case "5" -> printReturnToMenu();
            default -> invalid();
        }
    }

    private void printReturnToMenu() {
        System.out.println("returning to Main Menu...");
    }

    private void printRobotActions() {
        System.out.println("""
                Select action
                1. Clean
                2. Reboot
                3. Delete
                4. Fix
                5. Back to menu
                """);
    }

    private void printChooseMsg() {
        System.out.println("choose robot name");
    }

    private void printRobots(ArrayList<String> list) {
        for (var name : list) {
            System.out.println(name);
        }
    }

    private void invalid() {
        System.out.println("Not a valid option, try again");
    }

    private void add() {
        Scanner sc = new Scanner(System.in);
        printAskName();
        var robotName = sc.nextLine();
        printRobotModels();
        var model = sc.nextLine();
        printWifi();
        var wifiName = sc.nextLine();
        addNewRobot(robotName, model, wifiName);
    }

    private void printWifi() {
        System.out.println("Insert wifi Name");
    }

    private void printRobotModels() {

        System.out.println("""
                select robot model number, if the choice is invalid ,the input V1 will be chosen
                1. V1
                2. V2
                3. V3
                """);
    }

    private void printAskName() {

        System.out.println("Insert robot name");
    }

    private void addNewRobot(String robotName, String model, String wifiName) {

        var robotModel = provider.getModel(model);
        provider.add(robotName, wifiName, robotModel);
    }

}
