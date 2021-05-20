
import java.util.ArrayList;

public class Pawn extends Piece {

    Boolean EP = false;
    @Override
    public final void set_tables() {
        wTable = new int[] {
             0,  0,  0,  0,  0,  0,  0,  0,
             5, 10, 10,-20,-20, 10, 10,  5,
             5, -5,-10,  0,  0,-10, -5,  5,
             0,  0,  0, 20, 20,  0,  0,  0,
             5,  5, 10, 25, 25, 10,  5,  5,
            10, 10, 20, 30, 30, 20, 10, 10,
            50, 50, 50, 50, 50, 50, 50, 50,
             0,  0,  0,  0,  0,  0,  0,  0
        };
        bTable = new int[] {
              0,  0,  0,  0,  0,  0,  0,  0,
             50, 50, 50, 50, 50, 50, 50, 50,
             10, 10, 20, 30, 30, 20, 10, 10,
              5,  5, 10, 25, 25, 10,  5,  5,
              0,  0,  0, 20, 20,  0,  0,  0,
              5, -5,-10,  0,  0,-10, -5,  5,
              5, 10, 10,-20,-20, 10, 10,  5,
              0,  0,  0,  0,  0,  0,  0,  0
        };
    }

    public Pawn (Boolean color, int position) {
        this.color = color;
        this.position = position;
        this.prev = position;
        this.val = 100;
        set_tables();
    }
    
    public Pawn (Boolean color, int position, int val) {
        this.color = color;
        this. position = position;
        this.val = val;
        set_tables();
    }
    
    public Pawn (Pawn that) {
        this(that.color, that.position, that.val);
    }
    
    @Override
    public ArrayList<Integer> verify (Piece [] first, Piece [] second) {
        if(color)
            return verifyB (first, second);
        else
            return verifyW (first, second);
    }
    
    public ArrayList<Integer> verifyB (Piece [] first, Piece [] second) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        if(position / 8 == 3) {
            if(position % 8 != 0 && second[position - 1] instanceof Pawn && ((Pawn)second[position - 1]).EP) {
                moves.add(position - 9);
            }
            if(position % 8 != 7 && second[position + 1] instanceof Pawn && ((Pawn)second[position + 1]).EP) {
                moves.add(position - 7);
            }
        }
        if(position / 8 == 6 && second[position - 16] == null
                && first[position - 16] == null && second[position - 8] == null && first[position - 8] == null) {
            moves.add(position - 16);
        }
        if(position % 8 != 0 && second[position - 9] != null) {
            moves.add(position - 9);
        }
        if(position % 8 != 7 && second[position - 7] != null) {
            moves.add(position - 7);
        }
        if(second[position - 8] == null && first[position - 8] == null) {
            moves.add(position - 8);
        }
        return moves;
    }

    public ArrayList<Integer> verifyW (Piece [] first, Piece [] second) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        if(position / 8 == 4) {
            if(position % 8 != 0 && second[position - 1] instanceof Pawn && ((Pawn)second[position - 1]).EP) {
                moves.add(position + 7);
            }
            if(position % 8 != 7 && second[position + 1] instanceof Pawn && ((Pawn)second[position + 1]).EP) {
                moves.add(position + 9);
            }
        }
         if(position / 8 == 1 && second[position + 16] == null
                 && first[position + 16] == null && second[position + 8] == null && first[position + 8] == null) {
            moves.add(position + 16);
        }
        if(position % 8 != 7 && second[position + 9] != null) {
            moves.add(position + 9);
        }
        if(position % 8 != 0 && second[position + 7] != null) {
            moves.add(position + 7);
        }
        if(second[position + 8] == null && first[position + 8] == null) {
            moves.add(position + 8);
        }
        return moves;
    }
    
    @Override
    public void move (Piece [] first, Piece [] second, int dest) {
        if(dest / 8 == 0 && color) {
            first[dest] = new Queen (color, dest);
            second[dest] = null;
            first[position] = null;
            return;
        }
        if(dest / 8 == 7 && !color) {
            first[dest] = new Queen (color, dest);
            second[dest] = null;
            first[position] = null;
            return;
        }
        if((dest == position + 7 || dest == position + 9) && second[dest] == null) {
            second[dest - 8] = null;
        }
        if((dest == position - 7 || dest == position - 9) && second[dest] == null) {
            second[dest + 8] = null;
        }
        if(dest == position + 16 || dest == position - 16) {
            if((dest % 8 != 0 && second[dest - 1] instanceof Pawn) || (dest % 8 != 7 && second[dest + 1] instanceof Pawn))
                EP = true;
        }
        first[dest] = this;
        first[position] = null;
        prev = position;
        position = dest;
        second[dest] = null;
    }
}
