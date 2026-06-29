package ATMMachine;

import java.util.HashMap;
import java.util.logging.Logger;

public class BankService {
    HashMap<String, Account> cardMap = new HashMap<>();
    
    public boolean isUserAuthenticated(Card card, int pin) {
        if (card.getIsActive() && card.getPin() == pin) {
            return true;
        }
        return false;
    }

    public void registerCard(String cardNumber, Account account) {
        cardMap.put(cardNumber, account);
    }

    public float getAccountBalance(Card card) {
        Account account = cardMap.get(card.getNumber());
        if (account != null) {
            System.out.println("Account balance: " + account.getBalance());
            return account.getBalance();
        }
        throw new IllegalArgumentException("Card not found in system");
    }
    

    public TransactionStatus debitAmount(Card card, float amount) {
        Account account = cardMap.get(card.getNumber());
        if (account == null) {
            return TransactionStatus.FAILURE;
        }
        
        float accountBalance = account.getBalance();
        if (accountBalance >= amount) {
            float remainingBalance = accountBalance - amount;
            account.setBalance(remainingBalance);
            Logger.getLogger(BankService.class.getName()).info("Debited amount: " + amount + " Remaining balance: " + remainingBalance);
            return TransactionStatus.SUCCESS;
        } else {
            return TransactionStatus.FAILURE;
        }
    }


    public TransactionStatus creditAmount(Card card, float amount) {
        Account account = cardMap.get(card.getNumber());
        if (account == null) {
            return TransactionStatus.FAILURE;
        }
        
        float accountBalance = account.getBalance() + amount;
        account.setBalance(accountBalance);
        Logger.getLogger(BankService.class.getName()).info("Credited amount: " + amount );
        return TransactionStatus.SUCCESS;
    }

}
