package src.Service;

import src.Entity.CartItem;
import src.Entity.Item;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private final List<CartItem> cartItemList = new ArrayList<>();

    public boolean isCartEmpty() {return cartItemList.isEmpty();}
    public int getCartSize() {return cartItemList.size();}

    public List<CartItem> getCartItem() {
        return new ArrayList<>(cartItemList);
    }

    public void addOneItemInCart(Item item) {
        cartItemList.add(new CartItem(item));
        System.out.println("✓ " + item.getName() + " ajouté au panier!");
    }

    public void addTrioInCart(Item main, Item snack, Item drink) {
        cartItemList.add(new CartItem(main, snack, drink));
        System.out.println("✓ Trio ajouté au panier!");
    }

    public void removeOneItem(int index) {
        CartItem remove = cartItemList.remove(index);
        System.out.println("✓ " + remove.getDescription() + " retiré du panier!");
    }

    public void clearCartItemList() {cartItemList.clear();}

    public double calculTotal(){
        double total = 0;
        for (CartItem ci : cartItemList) {
            total += ci.getPrice();
        }
        return total;
    }

    public void printCart(){
        System.out.println("\n=== VOTRE PANIER ===");
        for (int i = 0; i < cartItemList.size(); i++) {
            CartItem ci = cartItemList.get(i);
            System.out.printf("%d. %s - %.2f$%n", (i + 1), ci.getDescription(), ci.getPrice());
        }

        System.out.println("--------------------");
        System.out.printf("TOTAL: %.2f$%n", calculTotal());
    }



}
