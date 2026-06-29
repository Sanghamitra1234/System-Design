package ATMMachine;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== ATM MACHINE SYSTEM ==========\n");
        
        // Step 1: Initialize Bank Service and ATM Machine
        BankService bankService = new BankService();
        ATMMachine atm = new ATMMachine(bankService);
        
        // Step 2: Create Users and Accounts
        User user1 = new User("John Doe");
        Account account1 = new Account(user1, 5000, "ACC001");
        user1.setAccount(account1);
        
        User user2 = new User("Jane Smith");
        Account account2 = new Account(user2, 3000, "ACC002");
        user2.setAccount(account2);
        
        // Step 3: Create Cards
        Card card1 = new Card("1234567890", 1234, "123");
        Card card2 = new Card("9876543210", 5678, "456");
        
        // Step 4: Register Cards with Bank Service
        bankService.registerCard(card1.getNumber(), account1);
        bankService.registerCard(card2.getNumber(), account2);
        
        // Step 5: Initialize ATM Cash
        atm.addCash(Denomination.THOUSAND, 5);
        atm.addCash(Denomination.HUNDRED, 10);
        atm.addCash(Denomination.FIFTY, 10);
        atm.addCash(Denomination.TWENTY, 10);
        atm.addCash(Denomination.TEN, 10);
        
        System.out.println("=== Test Case 1: Successful Withdrawal ===");
        testSuccessfulWithdrawal(atm, card1, bankService);
        
        System.out.println("\n=== Test Case 2: Insufficient Balance ===");
        testInsufficientBalance(atm, card1);
        
        System.out.println("\n=== Test Case 3: Wrong PIN ===");
        testWrongPin(atm, card2);
        
        System.out.println("\n=== Test Case 4: Successful Deposit ===");
        testSuccessfulDeposit(atm, card2, bankService);
        
        System.out.println("\n=== Test Case 5: Invalid Amount (Not Multiple of 10) ===");
        testInvalidAmount(atm, card1);
        
        System.out.println("\n=== Test Case 6: ATM Insufficient Cash ===");
        testATMInsufficientCash(atm, card1);
    }
    
    private static void testSuccessfulWithdrawal(ATMMachine atm, Card card, BankService bankService) {
        System.out.println("Initial Balance: " + bankService.getAccountBalance(card));
        
        atm.insertCard(card);
        System.out.println("✓ Card inserted");
        
        atm.authenticate(card, 1234);
        System.out.println("✓ Authentication successful");
        
        atm.withdraw(500);
        System.out.println("✓ Withdrawal of 500 successful");
        
        float balance = atm.checkBalance();
        System.out.println("✓ Final Balance: " + balance);
        
        atm.ejectCard();
        System.out.println("✓ Card ejected");
    }
    
    private static void testInsufficientBalance(ATMMachine atm, Card card) {
        atm.insertCard(card);
        System.out.println("✓ Card inserted");
        
        atm.authenticate(card, 1234);
        System.out.println("✓ Authentication successful");
        
        System.out.println("Attempting to withdraw 10000 (insufficient balance)...");
        try {
            atm.withdraw(10000);
        } catch (IllegalStateException e) {
            System.out.println("✗ Withdrawal failed - " + e.getMessage());
        }
        atm.ejectCard();
        System.out.println("✓ Card ejected");
    }
    
    private static void testWrongPin(ATMMachine atm, Card card) {
        atm.insertCard(card);
        System.out.println("✓ Card inserted");
        
        System.out.println("Attempting authentication with wrong PIN...");
        atm.authenticate(card, 9999);
        System.out.println("✗ Authentication failed - Wrong PIN");
        
        atm.ejectCard();
        System.out.println("✓ Card ejected");
    }
    
    private static void testSuccessfulDeposit(ATMMachine atm, Card card, BankService bankService) {
        System.out.println("Initial Balance: " + bankService.getAccountBalance(card));
        
        atm.insertCard(card);
        System.out.println("✓ Card inserted");
        
        atm.authenticate(card, 5678);
        System.out.println("✓ Authentication successful");
        
        atm.deposit(1000);
        System.out.println("✓ Deposit of 1000 successful");
        
        float balance = atm.checkBalance();
        System.out.println("✓ Final Balance: " + balance);
        
        atm.ejectCard();
        System.out.println("✓ Card ejected");
    }
    
    private static void testInvalidAmount(ATMMachine atm, Card card) {
        atm.insertCard(card);
        System.out.println("✓ Card inserted");
        
        atm.authenticate(card, 1234);
        System.out.println("✓ Authentication successful");
        
        System.out.println("Attempting to withdraw 555 (not multiple of 10)...");
        try {
            atm.withdraw(555);
        } catch (IllegalStateException e) {
            System.out.println("✗ Withdrawal failed - " + e.getMessage());
        }
        atm.ejectCard();
        System.out.println("✓ Card ejected");
    }
    
    private static void testATMInsufficientCash(ATMMachine atm, Card card) {
        atm.insertCard(card);
        System.out.println("✓ Card inserted");
        
        atm.authenticate(card, 1234);
        System.out.println("✓ Authentication successful");
        
        System.out.println("Attempting to withdraw 100000 (ATM insufficient cash)...");
        try {
            atm.withdraw(100000);
        } catch (IllegalStateException e) {
            System.out.println("✗ Withdrawal failed - " + e.getMessage());
        }
        atm.ejectCard();
        System.out.println("✓ Card ejected");
    }
}
