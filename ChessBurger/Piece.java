
import java.util.ArrayList;


public abstract class Piece {
    Boolean color;
    int position;
    int prev = -1;
    int val;
    int [] wTable;
    int [] bTable;
    
    public abstract void set_tables();
    
    public Piece () {
        
    }
    
    public Piece (Boolean color, int position, int val) {
        this.color = color;
        this. position = position;
        this.val = val;
    }
    
    public Piece (Piece that) {
        this(that.color, that.position, that.val);
    }
    
    public abstract ArrayList<Integer> verify (Piece [] first, Piece [] second);
    
    public void move (Piece [] first, Piece [] second, int dest) {
        first[dest] = this;
        first[position] = null;
        prev = position;
        position = dest;
        second[dest] = null;
    }
}
