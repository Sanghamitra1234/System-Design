public class Cell {
    private SymbolType symbol;
    private int posx;
    private int posy;
    
    public Cell(SymbolType symbol, int posx, int posy) {
        this.symbol = symbol;
        this.posx = posx;
        this.posy = posy;
    }
    
    public SymbolType getSymbol() {
        return symbol;
    }
    
    public void setSymbol(SymbolType symbol) {
        this.symbol = symbol;
    }
    
    public int getPosx() {
        return posx;
    }
    
    public void setPosx(int posx) {
        this.posx = posx;
    }
    
    public int getPosy() {
        return posy;
    }
    
    public void setPosy(int posy) {
        this.posy = posy;
    }
    
    public boolean isEmpty() {
        return symbol == null;
    }
}
