import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private GameStatus gameStatus;
    
    public Game(Board board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = players;
        this.dice = dice;
        this.gameStatus = GameStatus.NOT_STARTED;
    }
    
    public void play() {
        this.gameStatus = GameStatus.IN_PROGRESS;
        System.out.println("=== Game Started ===\n");
        int turnCount = 0;
        while (this.gameStatus == GameStatus.IN_PROGRESS) {
            Player currentPlayer = players.get(0);
            int diceValue = dice.rollDice();
            int currentPosition = currentPlayer.getCurrentPosition();
            if (!canPlayerMove(currentPlayer, diceValue)) {
               continue;
            }
            
            turnCount++;
            System.out.println("Turn " + turnCount + ": " + currentPlayer.getName() + " rolled " + diceValue);
            System.out.println("  Position: " + currentPosition + " -> " + (currentPosition + diceValue));
            
            int newPosition = board.checkForSnakeAndLadder(currentPosition + diceValue);
            currentPlayer.setCurrentPosition(newPosition);
            
            System.out.println("  Final position: " + newPosition);
            
            if (newPosition == 100) {
                System.out.println("\n*** " + currentPlayer.getName() + " wins! ***");
                this.gameStatus = GameStatus.ENDED;
                break;
            }
            
            if (!hasPlayerRolledSix(currentPlayer, diceValue)) {
                System.out.println("  -> Next player's turn\n");
                players.remove(0);
                players.add(currentPlayer);
            } else {
                System.out.println("  -> Extra turn!\n");
            }
        }
    }

    private boolean canPlayerMove(Player player, int diceValue) {
        if (player.getCurrentPosition() == 0 && diceValue != 1) {
            players.remove(0);
            players.add(player);
            return false;
        }
        return true;
    }


     private boolean hasPlayerRolledSix(Player player, int diceValue) {
        if ( diceValue == 6) {
            return true;
        }
        return false;
    }
    
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }
    
    public boolean isGameStarted() {
        return this.gameStatus == GameStatus.IN_PROGRESS;
    }
    
    public boolean isGameEnded() {
        return this.gameStatus == GameStatus.ENDED;
    }
}
