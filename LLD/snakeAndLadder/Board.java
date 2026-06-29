import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private Cell[] cells;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    
    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size * size];
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.initializeBoard();
    }

    private void initializeBoard() {
        int m = this.size;
        int n = this.size;


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int position;
                if (i % 2 == 0) {  // even row
                    position = (i + 1) * n - j;  // right-to-left
                } else {  // odd row
                    position = i * n + j + 1;  // left-to-right
                }
                this.cells[i * n + j] = new Cell(i, j, position);
            }
        }

        this.addSnake(17, 4);
        this.addSnake(54, 34);
        this.addSnake(62, 18);
        this.addSnake(87, 24);
        this.addSnake(93, 73);
        this.addSnake(99, 79);
        
        this.addLadder(2, 38);
        this.addLadder(7, 14);
        this.addLadder(21, 42);
        this.addLadder(28, 84);
        this.addLadder(51, 67);
        this.addLadder(72, 91);
        this.addLadder(80, 99);
        
    }

    public int checkForSnakeAndLadder(int currentPosition) {
        for (Snake snake : snakes) {
            if (snake.getStart() == currentPosition) {
                System.out.println("Snake found! Moving from " + currentPosition + " to " + snake.getEnd());
                return snake.getDestination(currentPosition);
            }
        }
        
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == currentPosition) {
                System.out.println("Ladder found! Moving from " + currentPosition + " to " + ladder.getEnd());
                return ladder.getEnd();
            }
        }
        
        return currentPosition;
    }

    public void addSnake(int start, int end) {
        this.snakes.add(new Snake(start, end));
    }

    public void addLadder(int start, int end) {
        this.ladders.add(new Ladder(start, end));
    }
    
    public int getSize() {
        return size;
    }
    
    public Cell[] getCells() {
        return cells;
    }
    
    public List<Snake> getSnakes() {
        return snakes;
    }
    
    public List<Ladder> getLadders() {
        return ladders;
    }
    
    public void setCells(Cell[] cells) {
        this.cells = cells;
    }
    
    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }
    
    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }
}
