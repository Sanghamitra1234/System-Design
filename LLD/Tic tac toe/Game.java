public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameStatus status;
    
    public Game(String nameP1, String nameP2) {
        this.player1 = new Player(nameP1, SymbolType.X);
        this.player2 = new Player(nameP2, SymbolType.O);
        this.status = GameStatus.IN_PROGRESS;
    }

    public void startGame() {
        this.board = new Board(3);
        this.currentPlayer = this.player1;
    }
    
    public boolean playTurn(int position) {
        if (status != GameStatus.IN_PROGRESS) {
            System.out.println("Game is already over!");
            return false;
        }
        int row = position / 3;
        int col = position % 3;
        Cell cell = this.board.getCell(row, col);
        boolean isMoved = this.board.playMove(cell, this.currentPlayer);
        if (!isMoved) {
            return false;
        }
        status = getGameStatus();
        if (status == GameStatus.IN_PROGRESS) {
            this.currentPlayer = this.currentPlayer == this.player1 ? this.player2 : this.player1;
        }
        return true;
    }
    
    public GameStatus getGameStatus() {
        if (this.board.getGameStatus() == GameStatus.WIN) {
            return GameStatus.WIN;
        } else if (this.board.getGameStatus() == GameStatus.DRAW) {
            return GameStatus.DRAW;
        } else {
            return GameStatus.IN_PROGRESS;
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
 
    public Player getPlayer1() {
        return this.player1;
    }
 
    public Player getPlayer2() {
        return this.player2;
    }
}