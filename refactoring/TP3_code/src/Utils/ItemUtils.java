package src.Utils;

import src.Entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static ArrayList<Item> itemByType(List<Item> inventory, String type) {
        ArrayList<Item> result = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getType().equals(type)) {
                result.add(inventory.get(i));
            }
        }
        return result;
    }

    public static boolean isValidIndex(int idx, List<?> list) {
        return idx >= 0 && idx < list.size();
    }

}
