import java.util.*;
public class Deck {
    private static final Random random = new Random();
    private final List<Card> cards = new ArrayList<>();
    private int dealtIndex = 0;
    public Deck() {
        for (int i = 1; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(i, suit));
            }
        }
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i >= 0; i--) {
            // this will return an integer between 0 and i inclusively
            int j = random.nextInt(i+1);
            Card card1 = cards.get(i);
            Card card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);
        }
    }

    private int remainingCards() {
        return cards.size() - dealtIndex;
    }

    public Card dealCard() {
        return remainingCards() == 0 ? null : cards.get(dealtIndex++);
    }
}
