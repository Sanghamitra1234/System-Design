package ATMMachine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ATMMachine {
    private Map<Denomination, Integer> cashAvailable;
    private ATMState currentState;
    private BankService bankService;
    private Card card;

    public void setState(ATMState state) {
        this.currentState = state;
    }
    
    public ATMMachine(BankService bankService) {
        this.cashAvailable = new HashMap<>();
        this.currentState = new IdleState();
        this.bankService = bankService;
    }

    public void addCash(Denomination denomination, int count) {
        this.cashAvailable.put(denomination, count);
    }
    
    public void insertCard(Card card) {
        if (currentState instanceof IdleState) {
            this.card = card;
            setState(new CardInsertedState());
        }
    }

    public void authenticate(Card card, int pin) {
        if (currentState instanceof CardInsertedState) {
            if (bankService.isUserAuthenticated(card, pin)) {
                setState(new AuthenticateState());
            } else {
                setState(new NotAuthenticatedState());
            }
        }
    }
    
    public void ejectCard() {
        this.card = null;
        setState(new IdleState());
    }
    
    public boolean deposit(float amount) {
        if (amount > 0) {
            if (this.bankService.creditAmount(this.card, amount) == TransactionStatus.SUCCESS) {
                return true;
            } else {
                ejectCard();
                return false;
            }
        }
        return false;
    }
    
    public boolean withdraw(int amount) {
        if(validateTransaction(amount)) {
            TransactionStatus transactionStatus = bankService.debitAmount(this.card, amount);
            if (transactionStatus == TransactionStatus.FAILURE) {
                ejectCard();
                return false;
            } else {
                System.out.println("Withdrawal successful. Amount: " + amount);
                checkBalance();
                return true;
            }
        } else {
            ejectCard();
            return false;
        }
    }
    
    public float checkBalance() {
        if (this.currentState instanceof AuthenticateState) {
            return bankService.getAccountBalance(this.card);
        }
        ejectCard();
        return 0;
    }

    private boolean validateTransaction(int amount) {
        if (bankService.getAccountBalance(this.card) < amount) {
            throw new IllegalStateException("Not sufficient Balance");
        }
        if (amount % 10 != 0 || amount <= 0) {
            return false;
        }
        Map<Denomination, Integer> result = new LinkedHashMap<>();

        for (Denomination denomination : Denomination.values()) {
            int noteValue = denomination.getValue();
            int requiredNotes = amount / noteValue;
            int availableNotes = cashAvailable.getOrDefault(denomination, 0);
            int notesToUse = Math.min(requiredNotes, availableNotes);
            if (notesToUse > 0) {
                result.put(denomination, notesToUse);
                amount -= notesToUse * noteValue;
            }
        }
        if (amount > 0) {
            throw new IllegalStateException("Not sufficient Balance");
        }

        if (amount == 0) {
            for (Map.Entry<Denomination, Integer> entry : cashAvailable.entrySet()) {
                Denomination denomination = entry.getKey();
                if (result.containsKey(denomination)) {
                    entry.setValue(entry.getValue() - result.get(denomination));
                }
            }
            return true;
        }
        return false;
    }
}
