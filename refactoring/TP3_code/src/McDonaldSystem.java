package src;

import src.Entity.Item;
import src.Service.CartService;
import src.Service.ClientActions;
import src.Service.EmployeeActions;
import src.Service.OrderService;
import src.Utils.InputUtils;
import src.Utils.MenuPrinter;

import java.util.*;

public class McDonaldSystem {

    private final ArrayList<Item> inventory = new ArrayList<>();
    public final Scanner scanner = new Scanner(System.in);

    public final CartService cartService;
    public final OrderService orderService;

    public McDonaldSystem(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
        seedInventory();
    }


    public void run() {
        mainMenu();
    }

    public void seedInventory() {
        inventory.add(new Item("Big Mac", 6.99, 50, "main"));
        inventory.add(new Item("Quarter Pounder", 7.49, 40, "main"));
        inventory.add(new Item("McChicken", 5.99, 45, "main"));
        inventory.add(new Item("Frites", 3.49, 100, "snack"));
        inventory.add(new Item("Nuggets (6)", 4.99, 60, "snack"));
        inventory.add(new Item("Coca-Cola", 2.49, 80, "drink", "Medium"));
        inventory.add(new Item("Sprite", 2.49, 70, "drink", "Medium"));
        inventory.add(new Item("Jus d'orange", 2.99, 50, "drink", "Medium"));
    }

    public void mainMenu(){
        MenuPrinter.mcdoHeader();

        while (true) {
           MenuPrinter.mcdoOptionsMenu();

            int mainMenuSelectedOption = InputUtils.readIntInRangeFromUser(scanner, "Choix: ", 1, 3);

            if (mainMenuSelectedOption == 1) {
                clientMode();
            } else if (mainMenuSelectedOption == 2) {
                inventoryMode();
            } else if (mainMenuSelectedOption == 3) {
                break;
            }
        }
    }


    public void clientMode() {
        MenuPrinter.greetClientWithHisName(scanner);
        

        cartService.clearCartItemList();
        

        while (true) {
            MenuPrinter.mcdoClientMenu();

            int customerSelectedOption = InputUtils.readIntInRangeFromUser(scanner, "Choix: ", 1, 7);
            
            if (customerSelectedOption == 1) {

                ClientActions.handleShowMenu(inventory);

            } else if (customerSelectedOption == 2) {
                ClientActions.handleAddTrio(inventory, cartService,scanner);
                
            } else if (customerSelectedOption == 3) {
                ClientActions.handleAddOneItem(inventory, cartService,scanner);
                
            } else if (customerSelectedOption == 4) {
                ClientActions.handleViewTheCart(cartService);
                
            } else if (customerSelectedOption == 5) {


                ClientActions.handleRemoveItemFromCart(cartService,scanner);
                
            } else if (customerSelectedOption == 6) {

                ClientActions.handleCheckout(cartService, orderService);

                
            } else if (customerSelectedOption == 7) {

                if (ClientActions.handleExitFromClientMode(cartService))
                break;
            }
        }
    }

    public  void inventoryMode() {
        while (true) {
            MenuPrinter.mcdoInventoryMenu();

            int employeeSelectedOption = InputUtils.readIntInRangeFromUser(scanner, "Choix: ", 1, 5);
            
            if (employeeSelectedOption == 1) {
                EmployeeActions.handleShowInventory(inventory);
            } else if (employeeSelectedOption == 2) {
                EmployeeActions.handleAddStockInInventory(inventory,scanner);
            } else if (employeeSelectedOption == 3) {
                EmployeeActions.handleRemoveStockInInventory(inventory,scanner);
            } else if (employeeSelectedOption == 4) {
               EmployeeActions.handleAddNewItemInInventory(inventory,scanner);
            } else if (employeeSelectedOption == 5) {
                break;
            }
        }
    }
}