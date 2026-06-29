package VendingMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Inventory {
    private Map<String, Item> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addNewItem(Item item) {
        this.items.put(item.getCode(), item);
    }

    public void restock(Item item, int quantity) {
        if (!this.items.containsKey(item.getCode())) {
            return;
        }
        Item currItem = this.items.get(item.getCode());
        currItem.setQuantity(currItem.getQuantity() + quantity);
        this.items.put(item.getCode(), currItem);
    }

    public boolean isItemAvailable(String code) {
        if (this.items.containsKey(code)) {
            return true;
        }
        return false;
    }

    public void updateQuantity(Item item, int quantity) {
        if (!this.items.containsKey(item.getCode())) {
            return;
        }
        Item currItem = this.items.get(item.getCode());
        currItem.setQuantity(quantity);
        this.items.put(item.getCode(), currItem);
    }

    public Item getItem(String code) {
        if (!this.items.containsKey(code)) {
            return null;
        }
        return this.items.get(code);
    }


    public List<Item> displayItem() {
        return this.items.values().stream().collect(Collectors.toList());
    }
}
