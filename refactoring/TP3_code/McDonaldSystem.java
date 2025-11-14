import java.util.*;

public class McDonaldSystem {
    // Tout est static pour simplifier (mauvaise pratique!)
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static int orderNum = 1;
    public static ArrayList<CartItem> cart = new ArrayList<>(); // Le panier global
    
    public static void main(String[] args) {
        inventory.add(new Item("Big Mac", 6.99, 50, "main"));
        inventory.add(new Item("Quarter Pounder", 7.49, 40, "main"));
        inventory.add(new Item("McChicken", 5.99, 45, "main"));
        inventory.add(new Item("Frites", 3.49, 100, "snack"));
        inventory.add(new Item("Nuggets (6)", 4.99, 60, "snack"));
        inventory.add(new Item("Coca-Cola", 2.49, 80, "drink", "Medium"));
        inventory.add(new Item("Sprite", 2.49, 70, "drink", "Medium"));
        inventory.add(new Item("Jus d'orange", 2.99, 50, "drink", "Medium"));

        mainMenu();
        

    }

    public static void mainMenu(){
        System.out.println("=== MCDONALDS ===");

        while (true) {
            System.out.println("\n1. Mode Client");
            System.out.println("2. Mode Inventaire");
            System.out.println("3. Quitter");
            System.out.print("Choix: ");

            int FirstChoice = scanner.nextInt();

            if (FirstChoice == 1) {
                clientMode();
            } else if (FirstChoice == 2) {
                inventoryMode();
            } else if (FirstChoice == 3) {
                break;
            }
        }
    }
    
    // Méthode énorme avec beaucoup de logique (violation SRP)
    public static void clientMode() {
        System.out.print("Nom: ");
        scanner.nextLine();
        String nameClient = scanner.nextLine();
        System.out.println("Bienvenue " + nameClient);
        
        // Vider le panier pour ce client
        cart.clear();
        

        while (true) {
            System.out.println("\n1. Voir menu");
            System.out.println("2. Ajouter TRIO au panier");
            System.out.println("3. Ajouter item au panier");
            System.out.println("4. Voir panier");
            System.out.println("5. Retirer du panier");
            System.out.println("6. Passer commande");
            System.out.println("7. Retour");
            System.out.print("Choix: ");
            
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                // Afficher menu directement ici (code dupliqué)
                System.out.println("\n=== MENU ===");
                for (int i = 0; i < inventory.size(); i++) {
                    Item item = inventory.get(i);
                    System.out.println(item.getName() + " - " + item.getPrice() + "$ (stock: " + item.getStock() + ")");
                }
            } else if (choice == 2) {
                // Ajouter un trio au panier - tout dans la même méthode!
                System.out.println("\nPlats principaux:");
                ArrayList<Item> typeMains = new ArrayList<>();
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).getType().equals("main")) {
                        typeMains.add(inventory.get(i));
                    }
                }
                for (int i = 0; i < typeMains.size(); i++) {
                    System.out.println((i+1) + ". " + typeMains.get(i).getName() + " - " + typeMains.get(i).getPrice() + "$");
                }
                System.out.print("Choix: ");
                int choixMenu = scanner.nextInt() - 1;
                
                System.out.println("\nAccompagnements:");
                ArrayList<Item> snacks = new ArrayList<>();
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).getType().equals("snack")) {
                        snacks.add(inventory.get(i));
                    }
                }
                for (int i = 0; i < snacks.size(); i++) {
                    System.out.println((i+1) + ". " + snacks.get(i).getName() + " - " + snacks.get(i).getPrice() + "$");
                }
                System.out.print("Choix: ");
                int sideMenu = scanner.nextInt() - 1;
                
                System.out.println("\nBoissons:");
                ArrayList<Item> drinks = new ArrayList<>();
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).getType().equals("drink")) {
                        drinks.add(inventory.get(i));
                    }
                }
                for (int i = 0; i < drinks.size(); i++) {
                    System.out.println((i+1) + ". " + drinks.get(i).getName() + " - " + drinks.get(i).getPrice() + "$");
                }
                System.out.print("Choix: ");
                int drinkMenu = scanner.nextInt() - 1;
                
                // Vérifier indices
                if (choixMenu >= 0 && choixMenu < typeMains.size() && sideMenu >= 0 && sideMenu < snacks.size() && drinkMenu >= 0 && drinkMenu < drinks.size()) {
                    // Vérifier stock AVANT d'ajouter au panier
                    if (typeMains.get(choixMenu).getStock() > 0 && snacks.get(sideMenu).getStock() > 0 && drinks.get(drinkMenu).getStock() > 0) {
                        CartItem trio = new CartItem(typeMains.get(choixMenu), snacks.get(sideMenu), drinks.get(drinkMenu));
                        cart.add(trio);
                        System.out.println("✓ Trio ajouté au panier!");
                    } else {
                        System.out.println("ERREUR: Stock insuffisant pour ce trio!");
                    }
                } else {
                    System.out.println("ERREUR: Choix invalide");
                }
                
            } else if (choice == 3) {
                // Ajouter item individuel au panier
                System.out.println("\n=== MENU ===");
                for (int i = 0; i < inventory.size(); i++) {
                    Item item = inventory.get(i);
                    System.out.println((i+1) + ". " + item.getName() + " - " + item.getPrice() + "$ (stock: " + item.getStock() + ")");
                }
                System.out.print("Choix: ");
                int itemChoice = scanner.nextInt() - 1;
                
                if (itemChoice >= 0 && itemChoice < inventory.size()) {
                    Item selectedItem = inventory.get(itemChoice);
                    // Vérifier stock AVANT d'ajouter au panier
                    if (selectedItem.getStock() > 0) {
                        CartItem cartItem = new CartItem(selectedItem);
                        cart.add(cartItem);
                        System.out.println("✓ " + selectedItem.getName() + " ajouté au panier!");
                    } else {
                        System.out.println("ERREUR: Plus de stock pour " + selectedItem.getName());
                    }
                } else {
                    System.out.println("ERREUR: Choix invalide");
                }
                
            } else if (choice == 4) {
                // Voir panier
                if (cart.size() == 0) {
                    System.out.println("\nPanier vide!");
                } else {
                    System.out.println("\n=== VOTRE PANIER ===");
                    double total = 0;
                    for (int i = 0; i < cart.size(); i++) {
                        CartItem cartItem = cart.get(i);
                        System.out.printf("%d. %s - %.2f$\n", (i+1), cartItem.getDescription(), cartItem.getPrice());
                        total += cartItem.getPrice();
                    }
                    System.out.println("--------------------");
                    System.out.printf("TOTAL: %.2f$\n", total);
                }
                
            } else if (choice == 5) {
                // Retirer du panier
                if (cart.size() == 0) {
                    System.out.println("\nPanier vide!");
                } else {
                    System.out.println("\n=== VOTRE PANIER ===");
                    for (int i = 0; i < cart.size(); i++) {
                        CartItem cartItem = cart.get(i);
                        System.out.printf("%d. %s - %.2f$\n", (i+1), cartItem.getDescription(), cartItem.getPrice());
                    }
                    System.out.print("\nNuméro de l'item à retirer (0 pour annuler): ");
                    int removeChoice = scanner.nextInt();
                    
                    if (removeChoice > 0 && removeChoice <= cart.size()) {
                        CartItem removed = cart.remove(removeChoice - 1);
                        System.out.println("✓ " + removed.getDescription() + " retiré du panier!");
                    } else if (removeChoice != 0) {
                        System.out.println("ERREUR: Choix invalide");
                    }
                }
                
            } else if (choice == 6) {
                // Passer la commande
                if (cart.size() == 0) {
                    System.out.println("\nPanier vide! Ajoutez des items d'abord.");
                } else {
                    // Vérifier stock pour tous les items
                    boolean stockOk = true;
                    for (int i = 0; i < cart.size(); i++) {
                        CartItem cartItem = cart.get(i);
                        if (cartItem.isTrio()) {
                            if (cartItem.getItem().getStock() <= 0 || cartItem.getTrioSnack().getStock() <= 0 || cartItem.getTrioDrink().getStock() <= 0) {
                                stockOk = false;
                                System.out.println("ERREUR: Stock insuffisant pour " + cartItem.getDescription());
                            }
                        } else {
                            if (cartItem.getItem().getStock() <= 0) {
                                stockOk = false;
                                System.out.println("ERREUR: Stock insuffisant pour " + cartItem.getItem().getName());
                            }
                        }
                    }
                    
                    if (stockOk) {
                        // Retirer du stock
                        for (int i = 0; i < cart.size(); i++) {
                            CartItem cartItem = cart.get(i);
                            if (cartItem.isTrio()) {
                                cartItem.getItem().setStock(cartItem.getItem().getStock()-1);
                                cartItem.getTrioSnack().setStock(cartItem.getTrioSnack().getStock()-1);
                                cartItem.getTrioDrink().setStock(cartItem.getTrioDrink().getStock()-1);
                            } else {
                                cartItem.getItem().setStock(cartItem.getItem().getStock()-1);
                            }
                        }
                        
                        // Afficher reçu
                        System.out.println("\n========= RECU =========");
                        System.out.println("Commande #" + orderNum);
                        orderNum++;
                        double total = 0;
                        for (int i = 0; i < cart.size(); i++) {
                            CartItem cartItem = cart.get(i);
                            System.out.printf("%sideMenu - %.2f$\n", cartItem.getDescription(), cartItem.getPrice());
                            total += cartItem.getPrice();
                        }
                        System.out.println("------------------------");
                        System.out.printf("TOTAL: %.2f$\n", total);
                        System.out.println("========================");
                        
                        // Vider le panier
                        cart.clear();
                        System.out.println("\n✓ Commande passée avec succès!");
                    }
                }
                
            } else if (choice == 7) {
                // Vider le panier en quittant
                cart.clear();
                break;
            }
        }
    }
    
    // Mode inventaire - accès direct à la liste (violation encapsulation)
    public static void inventoryMode() {
        while (true) {
            System.out.println("\n=== INVENTAIRE ===");
            System.out.println("1. Afficher inventaire");
            System.out.println("2. Ajouter stock");
            System.out.println("3. Retirer stock");
            System.out.println("4. Ajouter nouvel item");
            System.out.println("5. Retour");
            System.out.print("Choix: ");
            
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                System.out.println("\n--- STOCK ACTUEL ---");
                for (int i = 0; i < inventory.size(); i++) {
                    Item item = inventory.get(i);
                    System.out.println(item.getName() + ": " + item.getName() + " unités (" + item.getName() + "$)");
                }
            } else if (choice == 2) {
                System.out.print("Nom de l'item: ");
                scanner.nextLine();
                String itemName = scanner.nextLine();
                
                boolean found = false;
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).getName().equals(itemName)) {
                        System.out.print("Quantité à ajouter: ");
                        int qty = scanner.nextInt();
                        inventory.get(i).setStock(inventory.get(i).getStock() + qty);
                        System.out.println("Stock ajouté!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Item non trouvé");
                }
            } else if (choice == 3) {
                System.out.print("Nom de l'item: ");
                scanner.nextLine();
                String itemName = scanner.nextLine();
                
                boolean found = false;
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).getName().equals(itemName)) {
                        System.out.print("Quantité à retirer: ");
                        int qty = scanner.nextInt();
                        if (inventory.get(i).getStock() >= qty) {
                            inventory.get(i).setStock(inventory.get(i).getStock() - qty);
                            System.out.println("Stock retiré!");
                        } else {
                            System.out.println("ERREUR: Pas assez de stock!");
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Item non trouvé");
                }
            } else if (choice == 4) {
                scanner.nextLine();
                System.out.print("Nom: ");
                String nom = scanner.nextLine();
                System.out.print("Prix: ");
                double price = scanner.nextDouble();
                System.out.print("Stock initial: ");
                int stock = scanner.nextInt();
                System.out.print("Type (main/snack/drink): ");
                String type = scanner.next();
                
                if (type.equals("drink")) {
                    System.out.print("Taille: ");
                    String size = scanner.next();
                    inventory.add(new Item(nom, price, stock, type, size));
                } else {
                    inventory.add(new Item(nom, price, stock, type));
                }
                System.out.println("Item ajouté!");
            } else if (choice == 5) {
                break;
            }
        }
    }
}