package src.Utils;

import src.Entity.Item;

import java.util.Scanner;

public class MenuPrinter {
    public static void mcdoHeader() {
        System.out.println("=== MCDONALDS ===");
    }

    public static void greetClientWithHisName(Scanner scanner) {
        System.out.print("Nom: ");
        scanner.nextLine();
        String nameClient = scanner.nextLine();
        System.out.println("Bienvenue " + nameClient);
    }

    public static void mcdoOptionsMenu() {
        System.out.println("\n1. Mode Client");
        System.out.println("2. Mode Inventaire");
        System.out.println("3. Quitter");

    }

    public static void mcdoClientMenu() {
        System.out.println("\n1. Voir menu");
        System.out.println("2. Ajouter TRIO au panier");
        System.out.println("3. Ajouter item au panier");
        System.out.println("4. Voir panier");
        System.out.println("5. Retirer du panier");
        System.out.println("6. Passer commande");
        System.out.println("7. Retour");

    }

    public static void mcdoInventoryMenu() {
        System.out.println("\n=== INVENTAIRE ===");
        System.out.println("1. Afficher inventaire");
        System.out.println("2. Ajouter stock");
        System.out.println("3. Retirer stock");
        System.out.println("4. Ajouter nouvel item");
        System.out.println("5. Retour");

    }

    public static void printIndexedItems(java.util.List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " - " + items.get(i).getPrice() + "$");
        }
    }

}
