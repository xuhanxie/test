public class Player {
    public Money money;
    public Player(Money money) {
        this.money = money;
    }

    public boolean canPurchase(Card card, Discount discount) {
        if (this.money.canCover(card.cost, discount)) {
            return true;
        } else {
            return false;
        }
    }

    public void purchase(Card card, Discount discount) {
        if (!canPurchase(card, discount)) {
            System.out.println("Cannot purchase due to insufficient balance.");
            return;
        } else {
            this.money.cover(card.cost, discount);
        }
    }

    public void printStatus() {
        money.printStatus();
    }
}