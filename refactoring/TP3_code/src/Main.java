package src;

import src.Service.CartService;
import src.Service.OrderService;

public class Main {

    public static void main(String[] args) {
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();

        new McDonaldSystem(cartService, orderService).run();
    }
}
