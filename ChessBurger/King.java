
import java.util.ArrayList;


public class King extends Piece {
    
    Boolean castle = true;
    @Override
    public final void set_tables() {
        wTable = new int[] {
             20, 30, 10,  0,  0, 10, 30, 20,
             20, 20,  0,  0,  0,  0, 20, 20,
            -10,-20,-20,-20,-20,-20,-20,-10,
            -20,-30,-30,-40,-40,-30,-30,-20,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30
        };
        bTable = new int[] {
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -30,-40,-40,-50,-50,-40,-40,-30,
            -20,-30,-30,-40,-40,-30,-30,-20,
            -10,-20,-20,-20,-20,-20,-20,-10,
            20, 20,  0,  0,  0,  0, 20, 20,
            20, 30, 10,  0,  0, 10, 30, 20
        };
    }
    public King (Boolean color, int position) {
        this.color = color;
        this.position = position;
        this.prev = position;
        this.val = 20000;
        set_tables();
    }
    
    public King (Boolean color, int position, int val) {
        this.color = color;
        this. position = position;
        this.val = val;
        set_tables();
    }
    
    public King (King that) {
        this(that.color, that.position, that.val);
    }
    
    public Boolean king_dist (int pos, Piece [] enemy) {
        if(pos <= 55) {
            if(enemy[pos + 8] instanceof King)
                return false;
            if(pos % 8 != 0) {
                if(enemy[pos + 7] instanceof King)
                    return false;
            }
            if(pos % 8 != 7) {
                if(enemy[pos + 9] instanceof King)
                    return false;
            }
        }
        if(pos >= 8) {
            if(enemy[pos - 8] instanceof King)
                return false;
            if(pos % 8 != 7) {
                if(enemy[pos - 7] instanceof King)
                    return false;
            }
            if(pos % 8 != 0) {
                if(enemy[pos - 9] instanceof King)
                    return false;
            }
        }
        if(pos % 8 != 0) {
            if(enemy[pos - 1] instanceof King)
                return false;
        }
        if(pos % 8 != 7) {
            if(enemy[pos + 1] instanceof King)
                return false;
        }
        return true;
    }

    @Override
    public ArrayList<Integer> verify (Piece [] first, Piece [] second) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        int cc = castle_check(first, second);
        if(cc == 1)
            moves.add(position + 2);
        if(cc == 2)
            moves.add(position - 2);
        if(cc == 3) {
            moves.add(position + 2);
            moves.add(position - 2);
        }
        if(position <= 55) {
            if(first[position  + 8] != null) {
                if(king_dist(position + 8, second))
                    moves.add(position + 8);
            }
            if(position % 8 != 0) {
                if(first[position  + 7] == null) {
                    if(king_dist(position + 7, second))
                        moves.add(position + 7);
                }
            }
            if(position % 8 != 7) {
                if(first[position  + 9] == null) {
                    if(king_dist(position + 9, second))
                        moves.add(position + 9);
                }
            }
        }
        if(position >= 8) {
            if(first[position  - 8] == null) {
                if(king_dist(position - 8, second))
                    moves.add(position - 8);
            }
            if(position % 8 != 7) {
                if(first[position  - 7] == null) {
                    if(king_dist(position - 7, second))
                        moves.add(position - 7);
                }
            }
            if(position % 8 != 0) {
                if(first[position  - 9] == null) {
                    if(king_dist(position - 9, second))
                        moves.add(position - 9);
                }
            }
        }
        if(position % 8 != 0) {
            if(first[position  - 1] == null) {
                if(king_dist(position - 1, second))
                    moves.add(position - 1);
            }
        }
        if(position % 8 != 7) {
            if(first[position  + 1] == null) {
                if(king_dist(position + 1, second))
                    moves.add(position + 1);
            }
        }
        return moves;
    }
    
    public Boolean check (Piece [] first, Piece [] second, int position) {
        int i;
        for(i = position + 8; i <= 63; i += 8) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Rook) {
                    return true;
                }
                else
                    break;
            }
        }
        for(i = position - 8; i >= 0; i -= 8) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Rook) {
                    return true;
                }
                else
                    break;
            }
        }
        for(i = position + 1; i <= 63 && i % 8 != 0; i++) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Rook) {
                    return true;
                }
                else
                    break;
            }
        }
        for(i = position - 1; i >= 0 && i % 8 != 7; i--) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Rook) {
                    return true;
                }
                else
                    break;
            }
        }
        
        for(i = position + 9; i <= 63 && i % 8 != 0; i += 9) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Bishop) {
                    return true;
                }
                else
                    break;
            }
        }
        for(i = position - 9; i >= 0 && i % 8 != 7; i -= 9) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Bishop) {
                    return true;
                }
                else
                    break;
            }
        }
        for(i = position + 7; i <= 63 && i % 8 != 7; i += 7) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Bishop) {
                    return true;
                }
                else
                    break;
            }
        }
        for(i = position - 7; i >= 0 && i % 8 != 0; i -= 7) {
            if(first[i] != null)
                break;
            if(second[i] != null) {
                if(second[i] instanceof Queen || second[i] instanceof Bishop) {
                    return true;
                }
                else
                    break;
            }
        }
        
        for(i = 0; i < 4; i++) {
            if((position + Knight.knight_moves[i] <= 63) && (position + Knight.knight_moves[i] >= 0)) {
               if((position + Knight.knight_moves[i]) % 8 > position % 8) {
                   if(second[position + Knight.knight_moves[i]] instanceof Knight) {
                       return true;
                   }
               }
            }
        }
        for(i = 4; i < 8; i++) {
            if((position + Knight.knight_moves[i] <= 63) && (position + Knight.knight_moves[i] >= 0)) {
               if((position + Knight.knight_moves[i]) % 8 < position % 8) {
                   if(second[position + Knight.knight_moves[i]] instanceof Knight) {
                       return true;
                   }
               }
            }
        }
        
        if(color && (position - 7 >= 0) && second[position - 7] instanceof Pawn)
            return true;
        if(color && (position - 9 >= 0) && second[position - 9] instanceof Pawn)
            return true;
        if(!color && (position + 7 <= 63) && second[position + 7] instanceof Pawn)
            return true;
        if(!color && (position + 9 <= 63) && second[position + 9] instanceof Pawn)
            return true;
        return false;
    }
    
    @Override
    public void move (Piece [] first, Piece [] second, int dest) {
        if(dest == position + 2) {
            first[position + 3].move(first, second, position + 1);
        }
        if(dest == position - 2) {
            first[position - 4].move(first, second, position - 1);
        }
        first[dest] = this;
        first[position] = null;
        prev = position;
        position = dest;
        second[dest] = null;
        castle = false;
    }
    
    public int castle_check (Piece [] first, Piece[] second) {
        int check = 0;
        Boolean b = true;
        if(check(first, second, position)) {
            return 0;
        }
        if(!castle) {
            return 0;
        }
        for(int i = 1; i < 3 && position + i < 64; i++) {
            if(first[position + i] != null || second[position + i] != null || check(first, second, position + i))
                b = false;
        }
        if(position + 3 <= 63 && b && first[position + 3] instanceof Rook && ((Rook)first[position + 3]).castle) {
            check++;
        }
        b = true;
        for(int i = 1; i <= 3 && position - i >= 0; i++) {
            if(first[position - i] != null || second[position - i] != null || check(first, second, position - i))
                b = false;
        }
        if(position - 4 >= 0 && b && first[position - 4] instanceof Rook && ((Rook)first[position - 4]).castle) {
            check += 2;
        }
        return check;
    }
}
