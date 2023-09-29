public class Card {
    public Money cost;
    public Card(Money cost) {
        this.cost = cost;
    }

    public void printStatus() {
        cost.printStatus();
    }
}
