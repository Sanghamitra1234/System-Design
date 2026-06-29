package ATMMachine;

public enum Denomination {
    THOUSAND(1000),
    HUNDRED(100),
    FIFTY(50),
    TWENTY(20),
    TEN(10);
    
    private final int value;
    
    Denomination(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
