
import java.util.ArrayList;


public class Knight extends Piece {
    final static int [] knight_moves = {-15, -6, 10, 17, -17, -10, 6, 15};
    
    @Override
    public final void set_tables() {
        wTable = new int [] {
            -50,-40,-30,-30,-30,-30,-40,-50,
            -40,-20,  0,  5,  5,  0,-20,-40,
            -30,  5, 10, 15, 15, 10,  5,-30,
            -30,  0, 15, 20, 20, 15,  0,-30,
            -30,  5, 15, 20, 20, 15,  5,-30,
            -30,  0, 10, 15, 15, 10,  0,-30,
            -40,-20,  0,  0,  0,  0,-20,-40,
            -50,-40,-30,-30,-30,-30,-40,-50                                  
        };
        bTable = new int[] {
            -50,-40,-30,-30,-30,-30,-40,-50,
            -40,-20,  0,  0,  0,  0,-20,-40,
            -30,  0, 10, 15, 15, 10,  0,-30,
            -30,  5, 15, 20, 20, 15,  5,-30,
            -30,  0, 15, 20, 20, 15,  0,-30,
            -30,  5, 10, 15, 15, 10,  5,-30,
            -40,-20,  0,  5,  5,  0,-20,-40,
            -50,-40,-30,-30,-30,-30,-40,-50
        };
    }
    public Knight (Boolean color, int position) {
        this.color = color;
        this.position = position;
        this.prev = position;
        this.val = 320;
        set_tables();
    }
    
    public Knight (Boolean color, int position, int val) {
        this.color = color;
        this. position = position;
        this.val = val;
        set_tables();
    }
    
    public Knight (Knight that) {
        this(that.color, that.position, that.val);
    }
    
    @Override
    public ArrayList<Integer> verify (Piece [] first, Piece [] second) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        int i;
        for(i = 0; i < 4; i++) {
            if((position + knight_moves[i] <= 63) && (position + knight_moves[i] >= 0)) {
               if((position + knight_moves[i]) % 8 > position % 8) {
                   if(first[position + knight_moves[i]] == null) {
                       moves.add(position + knight_moves[i]);
                   }
               }
            }
        }
        for(i = 4; i < 8; i++) {
            if((position + knight_moves[i] <= 63) && (position + knight_moves[i] >= 0)) {
               if((position + knight_moves[i]) % 8 < position % 8) {
                   if(first[position + knight_moves[i]] == null) {
                       moves.add(position + knight_moves[i]);
                   }
               }
            }
        }
        return moves;
    }
}
