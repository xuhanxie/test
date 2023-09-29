public class Piece {
    Position position;
    Color color;
    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position newPosition) {
        this.position = newPosition;
    }

    public void capture(Piece capturedPiece) {
        capturedPiece = null;
    }
}
