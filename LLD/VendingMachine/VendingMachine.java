package VendingMachine;

public class VendingMachine {
    private final static VendingMachine INSTANCE = new VendingMachine();
    private Inventory inventory;
    private VendingMachineState stateManager;
    private Item selectedItem;
    private int balance;
    private int currentBalance;

    public VendingMachine() {
        this.inventory = new Inventory();
        setCurrentState();
       
    }

    public static VendingMachine getVendingMachine() {
        return INSTANCE;
    }

    public VendingMachineState getCurrentState() {
        return this.stateManager;
    }

    public void setCurrentState() {
        this.stateManager = new IdleState(this);
    }


    public void insertCoin(Denomination denomination) {
        stateManager.insertCoin(denomination);
    }

    public void selectItem(String code) {
        stateManager.selectItem(code);
    }

    public void dispenseItem(String code) {
        Item item = inventory.getItem(code);
        if (this.currentBalance >= item.getPrice()) {
            stateManager.dispenseItem(code);
            this.currentBalance -= item.getPrice();
            System.out.println("Dispensed: " + item.getName());

            if (this.currentBalance > 0) {
                stateManager.returnChange();
                System.out.println("Returning change: " + balance);
            }
        }
    }

    public void returnChange() {
        stateManager.returnChange();
    }

    // public void cancelTransaction() {
    //     stateManager.returnChange();
    // }

    public void viewAvailableItems() {
       System.out.println(this.inventory.displayItem());
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int money) {
        this.balance = money;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setCurrentState(VendingMachineState state) {
        this.stateManager = state;
    }


    public void setSelectedItem(Item item) {
        this.selectedItem = item;
    }

    public Item getSelectedItem() {
        return this.selectedItem;
    }

    public void setCurrentBalance(int balance) {
        this.currentBalance = balance;
    }
    public int getCurrentBalance() {
        return this.currentBalance;
    }
}
