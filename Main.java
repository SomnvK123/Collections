import Enum.*;
import Manager.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                Action action = Action.getAction(choice);

                switch (action) {
                    case ADD_WORD -> {
                        System.out.print("Enter word: ");
                        String word = sc.nextLine();
                        System.out.print("Enter translation: ");
                        String translation = sc.nextLine();
                        manager.addWord(word, translation);
                        System.out.println("Word added successfully.");
                    }

                    case LIST_WORDS -> {
                        manager.listWords();
                    }

                    case ADD_SYNONYM -> {
                        System.out.print("Enter word: ");
                        String word = sc.nextLine();
                        System.out.print("Enter synonym: ");
                        String synonym = sc.nextLine();
                        manager.addSynonym(word, synonym);
                        System.out.println("Synonym added successfully.");
                    }

                    case LIST_HISTORY -> {
                        System.out.println("History of searched words: ");
                        manager.listHistory();
                    }

                    case SEARCH_WORD -> {
                        System.out.print("Enter word to search: ");
                        String word = sc.nextLine();
                        manager.searchWord(word);
                        manager.addToHistory(word); // Add searched word to history
                    }

                    case EXIT -> {
                        System.out.println("Exit");
                        return; // Exit the loop & program
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nDICTIONARY MANAGEMENT MENU");
        for (Action action : Action.values()) {
            System.out.println(action.getId() + ": " + action.getName());
        }
        System.out.print("Enter your choice: ");
    }
}