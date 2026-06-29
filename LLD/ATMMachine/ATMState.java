package ATMMachine;

public interface ATMState {
    void withdraw(ATMMachine atm, int amount);
    void deposit(ATMMachine atm, float amount);
    
}

class AuthenticateState implements ATMState {
    @Override
    public void withdraw(ATMMachine atm, int amount) {
        atm.withdraw(amount);
        atm.setState(new AuthenticateState());
    }
    
    @Override
    public void deposit(ATMMachine atm, float amount) {
        atm.deposit(amount);
        atm.setState(new AuthenticateState());
    }
}

class IdleState implements ATMState {
    public void withdraw(ATMMachine atm, int amount) {
        throw new IllegalStateException("Not authenticated");
    }
    
    public void deposit(ATMMachine atm, float amount) {
        throw new IllegalStateException("Not authenticated");
    }
}


class CardInsertedState implements ATMState {
    public void withdraw(ATMMachine atm, int amount) {
        throw new IllegalStateException("Not authenticated");
    }
    
    public void deposit(ATMMachine atm, float amount) {
        throw new IllegalStateException("Not authenticated");
    }
}

class NotAuthenticatedState implements ATMState {
    public void withdraw(ATMMachine atm, int amount) {
        throw new IllegalStateException("Not authenticated");
    }
    
    public void deposit(ATMMachine atm, float amount) {
        throw new IllegalStateException("Not authenticated");
    }
}