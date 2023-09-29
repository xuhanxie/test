import java.util.*;
public class Player {
    String name;
    Color color;
    List<Piece> pieceList = new ArrayList<>();
    int[][] dirs;
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        if (color == Color.W) {
            dirs = new int[][] {{1, -1}, {1, 1}};
        } else {
            dirs = new int[][] {{-1, -1}, {-1, 1}};
        }
    }

    public void getPieceList(Board b) {
        if (color == Color.W) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i+j) % 2 == 0) {
                        pieceList.add(b.board[i][j]);
                    }
                }
            }
        } else {
            for (int i = 5; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i+j) % 2 == 0) {
                        pieceList.add(b.board[i][j]);
                    }
                }
            }
        }
    }

    public List<List<Position>> getPossibleMoves(Board b) {
        List<List<Position>> res = new ArrayList<>();
        for (Piece p : pieceList) {
            res.add(getPossibleMove(p, b));
        }
        return res;
    }


    public List<Position> getPossibleMove(Piece p, Board b) {
        List<Position> res = new ArrayList<>();
        Position currPosition = p.position;
        for (int[] dir : dirs) {
            int row = currPosition.x + dir[0];
            int col = currPosition.y + dir[1];
            Position newPosition = new Position(row, col);
            if (b.isValidMove(p, newPosition)) {
                res.add(newPosition);
            }
        }
        return res;
    }

    public void makeMove(Board board, Piece p, Position newPosition) {
        int currX = p.position.x;
        int currY = p.position.y;
        if (board.isValidMove(p, newPosition)) {
            Piece capturedPiece = board.getCapturedPiece(p, newPosition);
            p.move(newPosition);
            board.board[newPosition.x][newPosition.y] = p;
            board.board[currX][currY] = null;
            if (capturedPiece != null) {
                board.board[capturedPiece.position.x][capturedPiece.position.y] = null;
                p.capture(capturedPiece);
            }
        }
    }

    public void display(Board b) {
        for (List<Position> positions : this.getPossibleMoves(b)) {
            for (Position p : positions) {
                p.display();
            }
            System.out.println();
        }
    }
}
