public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("=== Tic Tac Toe Game ===\n");
        
        Game game = new Game("Alice", "Bob");
        game.startGame();
        
        System.out.println("Game Started!");
        System.out.println("Alice (X) vs Bob (O)\n");
        
        // Test Case 1: Alice wins with horizontal line
        System.out.println("--- Test Case 1: Horizontal Win ---");
        testGame1(game);
        
        // Test Case 2: Draw
        System.out.println("\n--- Test Case 2: Draw ---");
        Game game2 = new Game("Player1", "Player2");
        game2.startGame();
        testDraw(game2);
    }
    
    private static void testGame1(Game game) {
        // Positions:
        // 0 1 2
        // 3 4 5
        // 6 7 8
        
        // Alice plays at 0
        System.out.println("Alice plays at position 0");
        game.playTurn(0);
        printStatus(game);
        
        // Bob plays at 3
        System.out.println("Bob plays at position 3");
        game.playTurn(3);
        printStatus(game);
        
        // Alice plays at 1
        System.out.println("Alice plays at position 1");
        game.playTurn(1);
        printStatus(game);
        
        // Bob plays at 4
        System.out.println("Bob plays at position 4");
        game.playTurn(4);
        printStatus(game);
        
        // Alice plays at 2 - WINS!
        System.out.println("Alice plays at position 2");
        game.playTurn(2);
        printStatus(game);
        
        if (game.getGameStatus() == GameStatus.WIN) {
            System.out.println("✓ TEST PASSED: Alice won with horizontal line!");
        } else {
            System.out.println("✗ TEST FAILED: Expected WIN status");
        }
    }
    
    private static void testDraw(Game game) {
        // Fill board without winner
        int[] moves = {0, 1, 2, 3, 5, 4, 6, 7, 8};
        for (int i = 0; i < moves.length; i++) {
            System.out.println(game.getCurrentPlayer().getName() + " plays at position " + moves[i]);
            game.playTurn(moves[i]);
            printStatus(game);
        }
        
        if (game.getGameStatus() == GameStatus.DRAW) {
            System.out.println("✓ TEST PASSED: Game ended in a draw!");
        } else {
            System.out.println("✗ TEST FAILED: Expected DRAW status");
        }
    }
    
    private static void printStatus(Game game) {
        System.out.println("Current Player: " + game.getCurrentPlayer().getName());
        System.out.println("Game Status: " + game.getGameStatus() + "\n");
    }
}