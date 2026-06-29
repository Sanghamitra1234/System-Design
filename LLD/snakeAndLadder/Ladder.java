public class Ladder implements GameObject {
    private int head;
    private int tail;
    
    public Ladder(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getStart() {
        return head;
    }
    
    public int getEnd() {
        return tail;
    }
    
    @Override
    public int getDestination(int position) {
        if (position == head) {
            return tail;
        }
        return position;
    }
} 
