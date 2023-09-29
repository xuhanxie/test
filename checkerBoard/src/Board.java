public class Board {
    Piece[][] board;
    int n = 8;
    public Board() {
        board = new Piece[n][n];
        initialize();
    }

    public void initialize() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 8; j++)
                if ((i + j) % 2 == 0) {
                    Piece p = new Piece(Color.W, new Position(i, j));
                    board[i][j] = p;
                }
        }

        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    Piece p = new Piece(Color.B, new Position(i, j));
                    board[i][j] = p;
                }
            }
        }
    }

    public boolean isValidMove(Piece p, Position newPosition) {
        // check if newPosition is within the board
        if (newPosition.x < 0 || newPosition.x >= n || newPosition.y < 0 || newPosition.y >= n) {
            return false;
        }
        // check if newPosition is empty
        if (board[newPosition.x][newPosition.y] != null) {
            return false;
        }

        // check if the move is a diagonal capture
        Piece capture = getCapturedPiece(p, newPosition);
        if (capture != null) {
            return true;
        }

        // check if the move is a simple diagonal
        if (Math.abs(p.position.x - newPosition.x) == 1 && Math.abs(p.position.y - newPosition.y) == 1) {
            return true;
        }
        return false;
    }

    public Piece getCapturedPiece(Piece p, Position newPosition) {
        int captureRow = (p.position.x + newPosition.x) / 2;
        int captureCol = (p.position.y + newPosition.y) / 2;
        Piece capturedPiece = board[captureRow][captureCol];
        if (capturedPiece != null && capturedPiece.color != p.color) {
            return capturedPiece;
        }
        return null;
    }

    public void display() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j];
                if (p != null) {
                    System.out.print(p.color);
                    System.out.print(",");
                } else {
                    System.out.print("-,");
                }
            }
            System.out.println();
        }
    }
}
