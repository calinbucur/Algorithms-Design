
import java.util.Scanner;
import java.util.ArrayList;

public class Engine {
    static final int INF = 300000000;
    
    public static String convert (int x) {
        if(x < 0 || x > 63) {
            return null;
        }
        else {
            String s = new String();
            char c = (char) (97 + (x % 8));
            char r = (char) (49 + (x / 8));
            return s + c + r;
        }
    }
    
    public static int[] rev_convert (String s) {
        int [] x = new int [2];
        x[0] = (((int) s.charAt(1)) - 49) * 8 + ((int) s.charAt(0)) - 97;
        x[1] = (((int) s.charAt(3)) - 49) * 8 + ((int) s.charAt(2)) - 97;
        return x;
    }
    
    public static String force (Board board, Scanner s) {
        String cmd = new String();
        String prev;

        while(true) {
            prev = cmd;
            cmd = s.nextLine();
            System.out.println("#" + cmd);
            if(cmd.equals("new")) {
                return "new";
            }
            if(cmd.matches("[a-h][1-8][a-h][1-8]")
                    || cmd.matches("[a-h][1-8][a-h][1-8]q")) {
                int [] move = Engine.rev_convert(cmd);
                if(board.color ^ board.turn) {
                    board.enemy[move[0]].move(board.enemy, board.player, move[1]);
                    board.turn = !board.turn;
                }
                else {
                    board.player[move[0]].move(board.player, board.enemy, move[1]);
                    board.turn = !board.turn;
                }
            }
            if(cmd.equals("go")) {
                return prev;
            }
        }
    }
 
    public static int negamax (Board board, int alpha, int beta, int depth, int[] chosen) {
        if(depth == 0) {
            return board.eval();
        }
        for(Piece k : board.player) {
            if(k instanceof Pawn)
                ((Pawn)k).EP = false;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < 64; i++) {
            Piece p = board.player[i];
            if(p != null) {
                if(p instanceof Pawn) {
                    ((Pawn)p).EP = false;
                }
                if(board.enemy[i] instanceof Pawn)
                    ((Pawn)board.enemy[i]).EP = false;
                ArrayList<Integer> moves = p.verify(board.player, board.enemy);
                if(!moves.isEmpty()) {
                    for(int m : moves) {
                        Board aux = new Board(board);
                        aux.player = aux.piece_cpy(board.player);
                        aux.enemy = aux.piece_cpy(board.enemy);
                        aux.player[i].move(aux.player, aux.enemy, m);
                        Boolean ck = false;
                        for(Piece k : aux.player) {
                            if(k instanceof King) {
                                if(((King) k).check(aux.player, aux.enemy, k.position))
                                    ck = true;
                            }
                        }
                        if(ck)
                            continue;
                        Piece[] paux = aux.player;
                        aux.player = aux.enemy;
                        aux.enemy = paux;
                        int score = -Engine.negamax(aux, -beta, -alpha, depth - 1, chosen);
                        if(score > max) {
                            max = score;
                        }
                        if(max > alpha) {
                            if(depth == 5) {
                            chosen[0] = p.position;
                            chosen[1] = m;
                            }
                            alpha = max;
                        }
                        if(alpha >= beta)
                            return alpha;
                    }
                }
            }
        }
        return alpha;
    }
    
    public static void new_game (Scanner s) {
        String cmd;
        Board x = new Board(true);
        x.Reset();
        while(true) {
            cmd = s.nextLine();
            if(cmd.equals("force")) {
                cmd = force(x, s);
            }
            if(cmd.equals("new")) {
                new_game(s);
                return;
            }
            if(cmd.equals("black")) {
                if(!x.color) {
                    x.color = true;
                    Piece [] aux = x.player;
                    x.player = x.enemy;
                    x.enemy = aux;
                    x.turn = !x.turn;
                    }
                else {
                    x.turn = !x.turn;
                }
                int [] chosen = new int[2];
                negamax(x, -INF, INF, 5, chosen);
                System.out.println(chosen[0]);
                System.out.println(chosen[1]);
                x.player[chosen[0]].move(x.player, x.enemy, chosen[1]);
                System.out.println("move " + Engine.convert(x.player[chosen[1]].prev) + Engine.convert(chosen[1]));
                System.out.flush();
            }
            if(cmd.equals("white")) {
                if(x.color) {
                    x.color = false;
                    Piece [] aux = x.player;
                    x.player = x.enemy;
                    x.enemy = aux;
                    x.turn = !x.turn;
                }
                else {
                        x.turn = !x.turn;
                }
                int [] chosen = new int[2];
                negamax(x, -INF, INF, 5, chosen);
                System.out.println(chosen[0]);
                System.out.println(chosen[1]);
                x.player[chosen[0]].move(x.player, x.enemy, chosen[1]);
                System.out.println("move " + Engine.convert(x.player[chosen[1]].prev) + Engine.convert(chosen[1]));
                System.out.flush();
            }
            if(cmd.matches("[a-h][1-8][a-h][1-8]")
                    || cmd.matches("[a-h][1-8][a-h][1-8]q")) {
                int [] move = Engine.rev_convert(cmd);
                x.enemy[move[0]].move(x.enemy, x.player, move[1]);
                x.turn = !x.turn;
                int [] chosen = new int[2];
                negamax(x, -INF, INF, 5, chosen);
                try {
                x.player[chosen[0]].move(x.player, x.enemy, chosen[1]);
                System.out.println("move " + Engine.convert(chosen[0]) + Engine.convert(chosen[1]));
                System.out.flush();
                x.turn = !x.turn;
                }
                catch (NullPointerException ex) {
                    System.out.println("resign");
                    System.out.flush();
                }
            }
        }
    }
    
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        String cmd = s.nextLine();        
        if(!cmd.equals("xboard"))
            return;
        s.nextLine();        
        System.out.println("feature sigint=0 sigterm=0 san=0");
        System.out.flush();        
        while(true) {
            cmd = s.nextLine();           
            if(cmd.equals("new")) {
                new_game(s);
            }
            if(cmd.equals("quit")) {
                return;
            }
        }
    }
}