
import java.util.ArrayList;


public class Rook extends Piece {
    
    Boolean castle = true;
    @Override
    public final void set_tables() {
        wTable = new int[] {
             0,  0,  0,  5,  5,  0,  0,  0,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
             5, 10, 10, 10, 10, 10, 10,  5,
             0,  0,  0,  0,  0,  0,  0,  0
        };
        bTable = new int[]  {
             0,  0,  0,  0,  0,  0,  0,  0,
             5, 10, 10, 10, 10, 10, 10,  5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
            -5,  0,  0,  0,  0,  0,  0, -5,
             0,  0,  0,  5,  5,  0,  0,  0
        };
    }
    public Rook (Boolean color, int position) {
        this.color = color;
        this.position = position;
        prev = position;
        this.val = 500;
        set_tables();
    }
    
    public Rook (Boolean color, int position, int val) {
        this.color = color;
        this. position = position;
        this.val = val;
        set_tables();
    }
    
    public Rook (Rook that) {
        this(that.color, that.position, that.val);
    }
    @Override
    public ArrayList<Integer> verify (Piece [] first, Piece [] second) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        int i;
        for(i = position + 8; i <= 63; i += 8) {
            if(first[i] != null) {
                break;
            }
            if(second[i] != null) {
                moves.add(i);
                break;
            }
            moves.add(i);
        }
        for(i = position - 8; i >= 0; i -= 8) {
            if(first[i] != null) {
                break;
            }
            if(second[i] != null) {
                moves.add(i);
                break;
            }
            moves.add(i);
        }
        for(i = position + 1; i % 8 != 0; i++) {
            if(first[i] != null) {
                //System.out.println("ce drq");
                break;
            }
            if(second[i] != null) {
                moves.add(i);
                break;
            }
            moves.add(i);
        }
        for(i = position - 1; (i >= 0)&&(i % 8 != 7); i--) {
            if(first[i] != null) {
                break;
            }
            if(second[i] != null) {
                moves.add(i);
                break;
            }
            moves.add(i);
        }
        return moves;
    }
    
    @Override
    public void move (Piece [] first, Piece [] second, int dest) {
        first[dest] = this;
        first[position] = null;
        prev = position;
        position = dest;
        second[dest] = null;
        castle = false;
    }
}