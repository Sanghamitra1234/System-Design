package VendingMachine;

public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getVendingMachine();

        // Add products to the inventory
        vendingMachine.getInventory().addNewItem(new Item("A1", "Coke", 25, 3));
        vendingMachine.getInventory().addNewItem(new Item("A2", "Pepsi", 25, 2));
        vendingMachine.getInventory().addNewItem(new Item("B1", "Water", 10, 5));

        // Select a product
        System.out.println("\n--- Step 1: Select an item ---");
        vendingMachine.selectItem("A1");

        // Insert coins
        System.out.println("\n--- Step 2: Insert coins ---");
        vendingMachine.insertCoin(Denomination.TEN); // 10
        vendingMachine.insertCoin(Denomination.TEN); // 10
        vendingMachine.insertCoin(Denomination.FIVE); // 5

        // Dispense the product
        System.out.println("\n--- Step 3: Dispense item ---");
        vendingMachine.dispenseItem("A1"); // Should dispense Coke

        // Select another item
        System.out.println("\n--- Step 4: Select another item ---");
        vendingMachine.selectItem("B1");

        // Insert more amount
        System.out.println("\n--- Step 5: Insert more than needed ---");
        vendingMachine.insertCoin(Denomination.TEN); // 10

        // Try to dispense the product
        System.out.println("\n--- Step 6: Dispense and return change ---");
        vendingMachine.dispenseItem("B1");
    }
}
