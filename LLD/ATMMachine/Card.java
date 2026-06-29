package ATMMachine;

public class Card {
    private String number;
    private int pin;
    private String cvv;
    private boolean isActive;
    
    public Card(String number, int pin, String cvv) {
        this.number = number;
        this.pin = pin;
        this.cvv = cvv;
        this.isActive = true;
    }
    
    public String getNumber() {
        return number;
    }
    
    public int getPin() {
        return pin;
    }
    
    public String getCvv() {
        return cvv;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public void setPin(int pin) {
        this.pin = pin;
    }
    
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean getIsActive() {
        return this.isActive;
    }
}
