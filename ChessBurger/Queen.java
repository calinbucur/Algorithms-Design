
import java.util.ArrayList;


public class Queen extends Piece{
   
@Override
    public final void set_tables() {
        wTable = new int[] {
            -20,-10,-10, -5, -5,-10,-10,-20,
            -10,  0,  5,  0,  0,  0,  0,-10,
            -10,  5,  5,  5,  5,  5,  0,-10,
              0,  0,  5,  5,  5,  5,  0, -5,
             -5,  0,  5,  5,  5,  5,  0, -5,
            -10,  0,  5,  5,  5,  5,  0,-10,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -20,-10,-10, -5, -5,-10,-10,-20
        };
        bTable = new int[] {
            -20,-10,-10, -5, -5,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5,  5,  5,  5,  0,-10,
             -5,  0,  5,  5,  5,  5,  0, -5,
              0,  0,  5,  5,  5,  5,  0, -5,
            -10,  5,  5,  5,  5,  5,  0,-10,
            -10,  0,  5,  0,  0,  0,  0,-10,
            -20,-10,-10, -5, -5,-10,-10,-20
        };
    }
    
    public Queen (Boolean color, int position) {
        this.color = color;
        this.position = position;
        this.prev = position;
        this.val = 900;
        set_tables();
    }
    
    public Queen (Boolean color, int position, int val) {
        this.color = color;
        this. position = position;
        this.val = val;
        set_tables();
    }
    
    public Queen (Queen that) {
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
        for(i = position + 7; (i <= 63) && (i % 8 != 7); i += 7) {
            if(first[i] != null) {
                break;
            }
            if(second[i] != null) {
                moves.add(i);
                break;
            }
            moves.add(i);
        }
        for(i = position - 9; (i >= 0) && (i % 8 != 7); i -= 9) {
            if(first[i] != null) {
                break;
            }
            if(second[i] != null) {
                moves.add(i);
                break;
            }
            moves.add(i);
        }
        for(i = position - 7; (i >= 0) && (i % 8 != 0); i -= 7) {
            if(first[i] != null) {
                break;
            }
            if(second[i] != null) {
                moves.add(i);
                break;
            }
            moves.add(i);
        }
        for(i = position + 9; (i <= 63) && (i % 8 != 0); i += 9) {
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
}
