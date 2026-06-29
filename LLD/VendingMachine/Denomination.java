package VendingMachine;

public enum Denomination {
    ONE(1),
    FIVE(5),
    TEN(10);

    private int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
