package VendingMachine;

public class Transaction {
    private double amount;
    private double change;
    private Item item;

    public Transaction() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double calculateChange(double amount, double itemPrice) {
        if (amount > itemPrice) {
            double change = (int) (amount - itemPrice);
            return change;
        }
        return 0;
    }

    public boolean isPaymentSufficient(double amount, double itemPrice) {
        if (amount >= itemPrice) {
            return true;
        }
        return false;
    }

    public void resetTransaction() {
        this.amount = 0;
        this.change = 0;
        this.item = null;
    }
}
