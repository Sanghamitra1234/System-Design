public class Cell {
    private int xposition;
    private int yposition;
    private int position;
    
    public Cell(int xposition, int yposition, int position) {
        this.xposition = xposition;
        this.yposition = yposition;
        this.position = position;
    }
    
    public int getXposition() {
        return xposition;
    }
    
    public int getYposition() {
        return yposition;
    }
    
    public int getPosition() {
        return position;
    }
}
