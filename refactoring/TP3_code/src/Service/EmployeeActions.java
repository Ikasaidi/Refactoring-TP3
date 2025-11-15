package src.Service;

import src.Entity.Item;
import src.Utils.InputUtils;

import java.util.List;
import java.util.Scanner;

public class EmployeeActions {

    //choice 1
    public static void handleShowInventory(List<Item> inventory) {
        System.out.println("\n--- STOCK ACTUEL ---");

        System.out.printf("%-3s | %-20s | %-10s | %-8s%n", "ID", "Nom", "Prix", "Stock");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.printf("%-3d | %-20s | %-10.2f$ | %-8d%n",
                    i + 1,
                    item.getName(),
                    item.getPrice(),
                    item.getStock());

        }
    }

    //choice 2
    public static void handleAddStockInInventory(List<Item> inventory, Scanner scanner) {
        String itemName = askItemNameToStock(scanner);
        Item item = findItemInStockByName(inventory, itemName);

        if (item == null) {
            System.out.println("Item non trouvé");
            return;
        }

        int qty = askQuantityToRemoveOrAdd(scanner, "ajouter");
        addStockInInventory(item, qty);
        System.out.println("Stock ajouté!");
    }

    private static String askItemNameToStock(Scanner scanner) {
        scanner.nextLine();
        return InputUtils.readNonEmptyLineFromUser(scanner, "Nom de l'item: ");
    }

    private static Item findItemInStockByName(List<Item> inventory, String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }



    private static void addStockInInventory(Item item, int qty) {
        item.setStock(item.getStock() + qty);
    }

    //Choice 3
    public static void handleRemoveStockInInventory(List<Item> inventory, Scanner scanner) {
        String itemName = askItemNameToStock(scanner);
        Item item = findItemInStockByName(inventory, itemName);

        if (item == null) {
            System.out.println("src.Entity.Item non trouvé");
            return;
        }

        int qty = askQuantityToRemoveOrAdd(scanner, "retirer");
        if (!hasEnoughStockToRemove(item, qty)) {
            System.out.println("ERREUR : Pas assez de stock!");
            return;
        }

        removeStockFromInventory(item, qty);
        System.out.println("Stock retiré!");
    }



    private static int askQuantityToRemoveOrAdd(Scanner scanner, String action) {
        String promp = ("Quantité à " + action + ": ");
        return InputUtils.readPositiveIntFromUser(scanner, promp);
    }


    private static boolean hasEnoughStockToRemove(Item item, int qty) {
        return item.getStock() >= qty;
    }


    private static void removeStockFromInventory(Item item, int qty) {
        item.setStock(item.getStock() - qty);
    }

    //choice 4
    public static void handleAddNewItemInInventory(List<Item> inventory, Scanner scanner) {
        String nom   = askNameToNewItem(scanner);
        double price = askPriceToNewItem(scanner);
        int stock    = askInitialStockToNewItem(scanner);
        String type  = askTypeToNewItem(scanner);
        if (type == null) {
            System.out.println("ERREUR: Type invalide (attendu: main/snack/drink)");
            return;
        }

        Item item = createNewItemFromInputs(nom, price, stock, type, scanner);
        addItem(inventory, item);
        System.out.println("Item ajouté!");
    }

    private static String askNameToNewItem(Scanner scanner) {
        scanner.nextLine();
        return InputUtils.readNonEmptyLineFromUser(scanner, "Nom: ");
    }

    private static double askPriceToNewItem(Scanner scanner) {
        return InputUtils.readDoubleFromUser(scanner, "Prix: ");
    }

    private static int askInitialStockToNewItem(Scanner scanner) {
        return InputUtils.readPositiveIntFromUser(scanner, "Stock initial: ");
    }

    private static String askTypeToNewItem(Scanner scanner) {
        scanner.nextLine();
        String choixType  = InputUtils.readNonEmptyLineFromUser(scanner, "Type (main/snack/drink): ");
        String type = choixType.trim().toLowerCase();

        if (!type.equals("main") && !type.equals("snack") && !type.equals("drink")) {
            return null;
        }
        return type;
    }


    private static Item createNewItemFromInputs(String nom, double price, int stock, String type, Scanner scanner) {
        if (type.equals("drink")) {
            String size = InputUtils.readNonEmptyLineFromUser(scanner, "Size: ");
            return new Item(nom, price, stock, type, size);
        }
        return new Item(nom, price, stock, type);
    }

    private static void addItem(List<Item> inventory, Item item) {
        inventory.add(item);
    }

}
