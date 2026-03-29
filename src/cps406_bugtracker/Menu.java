package cps406_bugtracker;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Buglist bugList;
    private ArrayList<Integer> ids;

    public Menu() {
        scanner = new Scanner(System.in);
        bugList = new Buglist();
        ids = bugList.getBugIDs();
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();

            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    viewBugs();
                    break;
                case 2:
                    addBug();
                    break;
                case 3:
                    editBug();
                    break;
                case 4:
                    deleteBug();
                    break;
                case 5:
                    searchBugs();
                    break;
                case 6:
                    saveBugs();
                    break;
                case 7:
                    running = exitProgram();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }


    private void printMainMenu() {
        System.out.println("\n==== Bug Tracker ====");
        System.out.println("1. View Bugs");
        System.out.println("2. Add Bug");
        System.out.println("3. Edit Bug");
        System.out.println("4. Delete Bug");
        System.out.println("5. Search Bugs");
        System.out.println("6. Save");
        System.out.println("7. Exit");
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void viewBugs() {
        System.out.println("\n--- View Bugs ---");

        for (Integer id : ids) {
            bugList.printBugInfo(bugList.getBugInfo(id));
            System.out.println("");
        }
    }

    private void addBug() {
        System.out.println("\n-- Add Bug --");

        System.out.print("Enter Bug name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        int bugId = bugList.addBug(name, description);

        System.out.println("Bug added successfully, ID: " + bugId);

        ids = bugList.getBugIDs();
    }

    // Only works for bug descriptions and artifact for now, could add option to edit name later.
    private void editBug () {
        System.out.println("\n--- Edit Bug ---");
         boolean editing = true;

        int id = getIntInput("Enter Bug ID: ");

        if (ids.indexOf(id) == -1) {
            System.out.println("Bug not found. Cancelling edit");
            editing = false;
        }

        System.out.println("");
        bugList.printBugInfo(bugList.getBugInfo(id));
        System.out.println("");


        while (editing) {
            System.out.println("\nSelect field to edit:");
            System.out.println("1. Description");
            System.out.println("2. Artifacts");
            System.out.println("3. Cancel");

            int choice = getIntInput("> ");

            switch (choice) {
                case 1:
                    System.out.println("Enter new description: ");
                    String newDesc = scanner.nextLine();
                    bugList.modifyBugDesc(id, newDesc);
                    break;
                case 2:
                    System.out.print("Enter new artifact: ");
                    String newArtifact = scanner.nextLine();
                    bugList.addArtifactToBug(newArtifact, id);
                    break;
                case 3:
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void deleteBug() {
        System.out.println("\n--- Delete Bug ---");

        int id = getIntInput("Enter Bug ID: ");

        if (ids.indexOf(id) == -1){
            System.out.println("Bug not found. Deletion cancelled");
            return;
        }

        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            bugList.removeBug(id);
            System.out.println("Bug deleted.");
            ids = bugList.getBugIDs();
        }
        else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void searchBugs() {
        System.out.println("\n--- Search Bugs ---");

        System.out.println("Enter search keyword: ");
        String query = (scanner.nextLine()).toLowerCase();
        System.out.println("\nMatched:\n");

        for (Integer id : ids) {
            ArrayList<String> bugInfo = bugList.getBugInfo(id);

            if (bugInfo.get(0).contains(query) || 
                bugInfo.get(2).contains(query) || 
                bugInfo.get(3).contains(query)) {
                
                bugList.printBugInfo(bugInfo);
            }
            System.out.println("");
        }


    }

    private void saveBugs() {
        System.out.println("\n--- Save Bugs ---");
        //TODO: save bugs to stringBugs
        System.out.println("Saved successfully");
    }

    private boolean exitProgram() {
        System.out.println("Are you sure you want to exit? Unsaved changes will be lost (y/n)");

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            System.out.println("Exiting...");
            return false;
        }
        else {
            System.out.println("Cancelling exit...");
            return true;
        }
    }

}