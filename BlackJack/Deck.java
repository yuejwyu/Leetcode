package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static final Random random = new Random();
    private final List<Card> cards = new ArrayList<>();
    private int dealtIndex = 0;

    public Deck() {
        for (FaceValue faceValue : FaceValue.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(faceValue, suit));
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int j = random.nextInt(cards.size() - i) + i;
            swap(i, j);
        }
    }

    public Card[] dealHand(int number) {
        if (remainingCards() < number) {
            return null;
        }
        Card[] cards = new Card[number]; //////////////////////////////// !!!!!!!
        for (int i = 0; i < number; i++) {
            cards[i] = dealCard();
        }
        return cards;
    }

    public Card dealCard() {
        return remainingCards() == 0 ? null : cards.get(dealtIndex++);
    }

    public void reset() {
        dealtIndex = 0;
        shuffle();
    }

    private void swap(int i, int j) {
        Card tmp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, tmp);
    }

    private int remainingCards() {
        return cards.size() - dealtIndex;
    }
}
