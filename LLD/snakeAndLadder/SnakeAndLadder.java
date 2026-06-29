import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadder {
    private Game game;
    private GameStatus gameStatus;

    public void startGame() {
        Board board = new Board(10);
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        
        this.game = new Game(board, players, new Dice());
        this.gameStatus = GameStatus.IN_PROGRESS;
    }



    public void playGame() {
        this.game.play();
    }
    
}
