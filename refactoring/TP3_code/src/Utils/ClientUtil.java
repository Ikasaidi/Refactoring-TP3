package src.Utils;

import java.util.Scanner;

public class ClientUtil {


    public static int readZeroBasedIndexFromUser(Scanner userInputScanner, String promptMessage) {
        int oneBasedIndex = InputUtils.readIntFromUser(userInputScanner, promptMessage);
        return oneBasedIndex - 1;
    }
}
