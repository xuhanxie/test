import java.util.*;
public class Position {
    int x;
    int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void display() {
        System.out.print(Arrays.asList(x, y));
        System.out.print(",");
    }
}
