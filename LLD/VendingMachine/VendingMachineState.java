package VendingMachine;

public abstract class VendingMachineState {
    VendingMachine machine;

    VendingMachineState(VendingMachine machine) {
        this.machine = machine;
    }
    abstract void dispenseItem(String code);
    abstract void selectItem(String code);
    abstract void insertCoin(Denomination denomination);
    abstract void returnChange();
}

class IdleState extends VendingMachineState {
        IdleState(VendingMachine machine) {
            super(machine);
        }

        @Override
        public void dispenseItem(String code) {
            System.out.println("No item selected.");
        }

        @Override
        public void selectItem(String code) {
            if (code == null || code.isEmpty()) {
                System.out.println("Please select a valid item.");
                return;
            }
            if (!machine.getInventory().isItemAvailable(code)) {
                System.out.println("Item is out of stock.");
                return;
            }
            machine.setSelectedItem(machine.getInventory().getItem(code));
            machine.setCurrentState(new ItemSelectedState(machine));
        }
        
        @Override
        public void insertCoin(Denomination denomination) {
            System.out.println("Please select an item before inserting money.");
        }
        
        @Override
        public void returnChange() {
            System.out.println("No item selected.");
        }
}

class ItemSelectedState extends VendingMachineState {
        ItemSelectedState(VendingMachine machine) {
            super(machine);
        }

        @Override
        public void dispenseItem(String code) {
            System.out.println("Insert money first to dispense item.");
        }

        @Override
        public void selectItem(String code) {
            System.out.println("Item is already selected.");
        }
        
        @Override
        public void insertCoin(Denomination denomination) {
            if (denomination == null ) {
                System.out.println("Please select a valid denomination.");
                return;
            }
            if (denomination.getValue() <= 0) {
                System.out.println("Please select a valid denomination.");
                return;
            }

            if (denomination.getValue() >= machine.getSelectedItem().getPrice()) {
                machine.setCurrentBalance(denomination.getValue());
                machine.setBalance(machine.getBalance() + denomination.getValue());
                System.out.println("Coin is inserted into the machine");
                machine.setCurrentState(new MoneyInsertedState(machine));
            }
        }
        
        @Override
        public void returnChange() {
            System.out.println("No item selected.");
        }
}

class MoneyInsertedState extends VendingMachineState {
        MoneyInsertedState(VendingMachine machine) {
            super(machine);
        }
        
        @Override
        public void dispenseItem(String code) {
            machine.getInventory().updateQuantity(machine.getSelectedItem(), machine.getSelectedItem().getQuantity() - 1);
            if (machine.getCurrentBalance() > machine.getSelectedItem().getPrice()) {
                machine.setCurrentState(new MoneyInsertedState(machine));
            } else {
                machine.setCurrentState(new IdleState(machine));
                machine.setSelectedItem(null);
            }
            System.out.println("Item is dispensed.");
        }

        @Override
        public void selectItem(String code) {
            System.out.println("Item is already selected.");
        }
        
        @Override
        public void insertCoin(Denomination denomination) {
            System.out.println("Coin is already inserted.");
        }
        
        @Override
        public void returnChange() {
            if (machine.getCurrentBalance() > machine.getSelectedItem().getPrice()) {
                double change = machine.getCurrentBalance() - machine.getSelectedItem().getPrice();
                System.out.println("Change is returned: " + change);
                machine.setCurrentBalance(0);
                machine.setBalance((int)(machine.getBalance() - change));
                machine.setSelectedItem(null);
            }
            System.out.println("Change is dispensed.");
            machine.setCurrentState(new IdleState(machine));
        }
}

