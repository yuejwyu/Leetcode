package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    protected final List<Card> cards = new ArrayList<>();

    public int score() { // or abstract class here
        int score = 0;
        for (Card card : cards) {
            score += card.value().getValue();
        }
        return score;
    }

    public void addCards(Card[] c) {
        Collections.addAll(cards, c);
    }

    public int size() {
        return cards.size();
    }
}
