package ATMMachine;

public class Transaction {
    private int id;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private int amount;
    private String date;
    
    public Transaction(int id, TransactionType transactionType, TransactionStatus transactionStatus, int amount, String date) {
        this.id = generateId();
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.amount = amount;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }
    
    public TransactionType getTransactionType() {
        return transactionType;
    }
    
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public String getDate() {
        return date;
    }
    
    private int generateId() {
        return (int) (Math.random() * 1000000);
    }
}
