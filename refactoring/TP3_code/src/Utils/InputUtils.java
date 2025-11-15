package src.Utils;

import java.util.Scanner;

public class InputUtils {

    public static int readIntFromUser(Scanner userInputScanner, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            if (userInputScanner.hasNextInt()) {
                int value = userInputScanner.nextInt();
                userInputScanner.nextLine();
                return value;
            }
            System.out.println("ERREUR: Veuillez entrer un nombre entier valide.");
            userInputScanner.next();
        }
    }

    public static int readIntInRangeFromUser(Scanner userInputScanner, String promptMessage, int minimumInclusive, int maximumInclusive) {
        while (true) {
            int userValue = readIntFromUser(userInputScanner, promptMessage);
            if (userValue >= minimumInclusive && userValue <= maximumInclusive) {
                return userValue;
            }
            System.out.println("ERREUR: Veuillez entrer un nombre entre "
                    + minimumInclusive + " et " + maximumInclusive + ".");
        }
    }

    public static int readPositiveIntFromUser(Scanner userInputScanner, String promptMessage) {
        while (true) {
            int userValue = readIntFromUser(userInputScanner, promptMessage);
            if (userValue > 0) {
                return userValue;
            }
            System.out.println("ERREUR: Veuillez entrer un nombre strictement positif.");
        }
    }

    public static double readDoubleFromUser(Scanner userInputScanner, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            if (userInputScanner.hasNextDouble()) {
                int value = userInputScanner.nextInt();
                userInputScanner.nextLine();
                return value;
            }
            System.out.println("ERREUR: Veuillez entrer un nombre décimal valide.");
            userInputScanner.next();
        }
    }

    public static String readNonEmptyLineFromUser(Scanner userInputScanner, String promptMessage) {
        String inputLine;
        do {
            System.out.print(promptMessage);
            inputLine = userInputScanner.nextLine().trim();
        } while (inputLine.isEmpty());
        return inputLine;
    }
}
