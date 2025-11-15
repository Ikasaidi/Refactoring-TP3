package src.Service;

import src.Utils.ClientUtil;
import src.Entity.Item;
import src.Utils.ItemUtils;
import src.Utils.MenuPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientActions {

    //Choice 1
    public static void handleShowMenu(List<Item> inventory) {
        System.out.println("\n=== MENU ===");
        MenuPrinter.printIndexedItems(inventory);
    }


    //Choice 2
    public static void handleAddTrio(List<Item> inventory, CartService cartService, Scanner scanner) {
        Item main = chooseItembyType(inventory, "main", "\nPlats principaux:", scanner);
        if (main == null) {return;}

        Item snack = chooseItembyType(inventory, "snack", "\nAccompagnements:", scanner);
        if (snack == null) {return;}

        Item drink = chooseItembyType(inventory, "drink", "\nBoissons:", scanner);
        if (drink == null) {return;}

        if (!validateStockTrio(main, snack, drink)) {return;}

        addTrioToCart(cartService, main, snack, drink);

    }

    private static Item chooseItembyType(List<Item> inventory, String type, String title, Scanner scanner) {
        System.out.println(title);
        ArrayList<Item> filtered = ItemUtils.itemByType(inventory, type);

        if (filtered.isEmpty()) {
            System.out.println("ERREUR: Aucun item disponible pour cette catégorie.");
            return null;
        }

        MenuPrinter.printIndexedItems(filtered);
        int index = ClientUtil.readZeroBasedIndexFromUser(scanner, "Choix: ");

        if (!ItemUtils.isValidIndex(index, filtered)) {
            System.out.println("ERREUR: Choix invalide");
            return null;
        }
        return filtered.get(index);
    }

    private static boolean validateStockTrio(Item main, Item snack, Item drink) {
        if (main.getStock() <= 0 || snack.getStock() <= 0 || drink.getStock() <= 0) {
            System.out.println("ERREUR: Stock insuffisant pour ce trio!");
            return false;
        }
        return true;
    }


    private static void addTrioToCart(CartService cartService, Item main, Item snack, Item drink) {
        cartService.addTrioInCart(main, snack, drink);
    }

    //Choice 3
    public static void handleAddOneItem(List<Item> inventory, CartService cartService, Scanner scanner) {
        System.out.println("\n=== MENU ===");
        MenuPrinter.printIndexedItems(inventory);

        int index = ClientUtil.readZeroBasedIndexFromUser(scanner, "Choix: ");

        if (!ItemUtils.isValidIndex(index, inventory)) {
            System.out.println("ERREUR: Choix invalide");
            return;
        }

        Item selectedItem = inventory.get(index);

        if (!validateStockForOneITem(selectedItem)) return;

        addItemToCartForOne(cartService, selectedItem);
    }

    private static boolean validateStockForOneITem(Item item) {
        if (item.getStock() <= 0) {
            System.out.println("ERREUR: Plus de stock pour " + item.getName());
            return false;
        }
        return true;
    }

    private static void addItemToCartForOne(CartService cartService, Item item) {
        cartService.addOneItemInCart(item);
    }

    //choice 4
    public static void handleViewTheCart(CartService cartService) {
        if (cartService.isCartEmpty()) {
            System.out.println("\nPanier vide!");
            return;
        }
        cartService.printCart();
    }

    //Choice 5
    public static void handleRemoveItemFromCart(CartService cartService, Scanner scanner) {
        if (cartService.isCartEmpty()) {
            System.out.println("\nPanier vide!");
        } else {
            cartService.printCart();
            System.out.print("\nNuméro de l'item à retirer (0 pour annuler): ");
            int removeChoice = scanner.nextInt();

            if (removeChoice > 0 && removeChoice <= cartService.getCartSize()) {
                cartService.removeOneItem(removeChoice - 1);
            } else if (removeChoice != 0) {
                System.out.println("ERREUR: Choix invalide");
            }
        }
    }

    //Choice 6 :

    public static void handleCheckout(CartService cartService, OrderService orderService) {
        if (cartService.isCartEmpty()) {
            System.out.println("\nPanier vide! Ajoutez des items d'abord.");
            return;
        }

        orderService.checkout(cartService.getCartItem(), cartService);
    }

    //choice 7 :

    public static boolean handleExitFromClientMode(CartService cartService) {
        cartService.clearCartItemList();
        System.out.println("\nPanier vidé. Retour au menu principal...");
        return true;
    }





}
