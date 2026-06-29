public class Dice {
    private int value;
    
    public Dice() {
        this.value = 0;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int rollDice() {
        this.value = (int) (Math.random() * 6) + 1;
        return this.value;
    }
}
