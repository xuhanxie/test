import java.util.*;
public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        b.display();
        Player whitePlayer = new Player("ken", Color.W);
        Player blackPlayer = new Player("bbl", Color.B);
        whitePlayer.getPieceList(b);
        blackPlayer.getPieceList(b);
        whitePlayer.display(b);
        blackPlayer.display(b);
        Piece pToMove1 = b.board[2][0];
        whitePlayer.makeMove(b, pToMove1, new Position(3, 1));

        b.display();
        System.out.println();
        Piece pToMove2 = b.board[5][1];
        blackPlayer.makeMove(b, pToMove2, new Position(4, 2));
        b.display();
        System.out.println();
        blackPlayer.display(b);

        Piece pToMove3 = b.board[4][2];
        blackPlayer.makeMove(b, pToMove3, new Position(2, 0));
        b.display();
        blackPlayer.display(b);
    }
}