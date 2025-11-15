package src.Service;

import src.Entity.CartItem;

import java.util.List;

public class OrderService {
    private int orderNum = 1;

    public void checkout(List<CartItem> cartItemList, CartService cartService) {
        if (!enoughStock(cartItemList)) return;
        removeFromStock(cartItemList);
        receipt(cartItemList, cartService);
        finalizeOrder(cartService);
    }

    private boolean enoughStock(List<CartItem> cartItemList) {
        boolean stockOk = true;
        for (CartItem cartItem : cartItemList) {
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
        return stockOk;
    }

    private  void removeFromStock(List<CartItem> cartItemList) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.isTrio()) {
                cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
                cartItem.getTrioSnack().setStock(cartItem.getTrioSnack().getStock() - 1);
                cartItem.getTrioDrink().setStock(cartItem.getTrioDrink().getStock() - 1);
            } else {
                cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
            }

        }
    }

    public void receipt(List<CartItem> cartItemList, CartService cartService) {
        System.out.println("\n========= RECU =========");
        System.out.println("Commande #" + orderNum);
        orderNum++;


        for (CartItem cartItem : cartItemList) {
            System.out.printf("%-15s | %4.2f $\n",
                    cartItem.getDescription(),
                    cartItem.getPrice());

        }
        System.out.println("------------------------");
        System.out.printf("TOTAL: %.2f$\n", cartService.calculTotal());
        System.out.println("========================");
    }

    private void finalizeOrder(CartService cartService) {
        cartService.clearCartItemList();
        System.out.println("\n✓ Commande passée avec succès!");
    }


}
