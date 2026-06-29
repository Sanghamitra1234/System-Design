

public class Player {
    private String name;
    private SymbolType symbol;
    
    public Player(String name, SymbolType symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public SymbolType getSymbol() {
        return symbol;
    }
    
    public void setSymbol(SymbolType symbol) {
        this.symbol = symbol;
    }
}
