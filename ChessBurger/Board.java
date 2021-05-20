
public class Board {
    
    Piece [] player;
    Piece [] enemy;

    Boolean color;
    Boolean turn;
    
    public Board (Boolean color) {
        this.color = color;
        turn = false;
        player = new Piece [64];
        enemy = new Piece [64];
    }
    
    public Board (Boolean color, Boolean turn) {
        this.color = color;
        this.turn = turn;
    }
    
    public Board (Board that) {
        this(that.color, that.turn);
    }
    
    public Piece[] piece_cpy(Piece [] p) {
        Piece[] cpy = new Piece[64];
        for(int i = 0; i < 64; i++) {
            if(p[i] != null) {
                if(p[i] instanceof Pawn) {
                    cpy[i] = new Pawn((Pawn)p[i]);
                }
                if(p[i] instanceof Rook) {
                    cpy[i] = new Rook((Rook)p[i]);
                }
                if(p[i] instanceof Knight) {
                    cpy[i] = new Knight((Knight)p[i]);
                }
                if(p[i] instanceof Bishop) {
                    cpy[i] = new Bishop((Bishop)p[i]);
                }
                if(p[i] instanceof Queen) {
                    cpy[i] = new Queen((Queen)p[i]);
                }
                if(p[i] instanceof King) {
                    cpy[i] = new King((King)p[i]);
                }
            }
            else {
                cpy[i] = null;
            }
        }
        return cpy;
    }
    
    public void Reset () {
        turn = false;
        player = new Piece [64];
        enemy = new Piece [64];
        if(color) {
            SetPawns(player, enemy);
            SetRooks(player, enemy);
            SetKnights(player, enemy);
            SetBishops(player, enemy);
            SetQueens(player, enemy);
            SetKings(player, enemy);
        }
        else {
            SetPawns(enemy, player);
            SetRooks(enemy, player);
            SetKnights(enemy, player);
            SetBishops(enemy, player);
            SetQueens(enemy, player);
            SetKings(enemy, player);
        }
    }
    
    public void SetPawns (Piece [] black, Piece [] white) {
        for(int i = 8; i < 16; i++) {
            white[i] = new Pawn(false, i);
            black[i + 40] = new Pawn(true, i + 40);
        }
    }
    public void SetRooks(Piece [] black, Piece [] white) {
        white[0] = new Rook(false, 0);
        white[7] = new Rook(false, 7);
        black[63] = new Rook(true, 63);
        black[56] = new Rook(true, 56);
    }
    public void SetKnights(Piece [] black, Piece [] white) {
        white[1] = new Knight(false, 1);
        white[6] = new Knight(false, 6);
        black[62] = new Knight(true, 62);
        black[57] = new Knight(true, 57);
    }
    public void SetBishops(Piece [] black, Piece [] white) {
        white[2] = new Bishop(false, 2);
        white[5] = new Bishop(false, 5);
        black[61] = new Bishop(true, 61);
        black[58] = new Bishop(true, 58);
    }
    public void SetQueens(Piece [] black, Piece [] white) {
        white[3] = new Queen(false, 3);
        black[59] = new Queen(true, 59);
    }
    public void SetKings(Piece [] black, Piece [] white) {
        white[4] = new King(false, 4);
        black[60] = new King(true, 60);
    }
    public int eval () {
        int p_val = 0;
        int e_val = 0;
        for(Piece p : player) {
            if(p != null) {
                p_val += p.val;
                if(p.color) {
                    p_val += p.bTable[p.position];
                }
                else {
                    p_val += p.wTable[p.position];
                }
            }
        }
        for(Piece e : enemy) {
            if(e != null) {
                e_val += e.val;
                if(e.color) {
                    e_val += e.bTable[e.position];
                }
                else {
                    e_val += e.wTable[e.position];
                }
            }
        }
        return p_val - e_val;
    }
}