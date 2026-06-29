
public class Board {

    private Cell[][] cells;
    private GameStatus gameStatus;
    private int countMoves;
    private int size;
    
    public Board(int size) {
        this.size = size;
        cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                 cells[i][j] = (new Cell(null, i, j));
            }
        }
    } 
    
    public Cell getCell(int row, int col) {
        return this.cells[row][col];
    }
    
    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public boolean playMove(Cell c, Player p) {
        if (p == null || c == null) {
            throw new IllegalArgumentException("Player or cell cannot be null");
        }
        if(!isValidMove(c)) {
            throw new IllegalArgumentException("Invalid move");
        }

        // If a valid move place the symbol on the cell
        c.setSymbol(p.getSymbol());
        countMoves++;

        if(isWinningMove(c, p)) {
            this.gameStatus = GameStatus.WIN;
            return true;
        }
        
        if (countMoves == size * size) {
            this.gameStatus = GameStatus.DRAW;
        }
        
        return true;
    }

    private boolean isWinningMove(Cell c, Player p) {
        int countHorizontal = 0;
        int countVertical = 0;
        // check if the move is a winning move
        int row = c.getPosx();
        int col = c.getPosy();
        SymbolType symbol = p.getSymbol();

        // Check row and column in one loop
        for (int i = 0; i < this.size; i++) {
            if (cells[row][i].getSymbol() == symbol) {
                countHorizontal++;
            }
            if (cells[i][col].getSymbol() == symbol) {
                countVertical++;
            }
        }
        if (countHorizontal == this.size || countVertical == this.size) {
            return true;
        }

        boolean mainDiag = true;
        for (int i = 0; i < this.size; i++) {
            if (cells[i][i].getSymbol() != symbol) {
                mainDiag = false;
                break;
            }
        }

        // diagonal check
        boolean antiDiag = true;
        for (int i = 0; i < this.size; i++) {
            if (cells[i][size - 1 - i].getSymbol() != symbol) {
                antiDiag = false;
                break;
            }
        }
        return mainDiag || antiDiag;
    }

    private boolean isValidMove(Cell c) {
        return c.isEmpty() && c.getPosx() >= 0 && c.getPosy() >= 0 &&
        c.getPosx() < cells.length && c.getPosy() < cells.length;
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }
}
