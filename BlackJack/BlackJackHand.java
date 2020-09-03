package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackHand extends Hand {
    private static final int WIN_SCORE = 21;

    @Override
    public int score() {
        List<Integer> scores = possibleScores();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > WIN_SCORE && score < minOver) {
                minOver = score;
            } else if (score <= WIN_SCORE && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }

    public boolean busted() {
        return score() > WIN_SCORE;
    }

    public boolean isBlackJack() {
        if (cards.size() != 2) {
            return false;
        }
        Card first = cards.get(0);
        Card second = cards.get(1);
        return (isAce(first) && isFaceCard(second)
                || isAce(second) && isFaceCard(first));
    }

    private List<Integer> possibleScores() {
        List<Integer> scores = new ArrayList<>();
        for (Card card : cards) {
            updateScores(card, scores);
        }
        return scores;
    }

    private void updateScores(Card card, List<Integer> scores) {
        final int[] toAdd = getScores(card); /////////////////////////// final !!!
        if (scores.isEmpty()) {
            for (int score : toAdd) {
                scores.add(score);
            }
        } else {
            final int length = scores.size(); ////////////////////////// final !!!
            for (int i = 0; i < length; i++) {
                int oldScore = scores.get(i);
                scores.set(i, oldScore + toAdd[0]);
                for (int j = 1; j < toAdd.length; j++) {
                    scores.add(oldScore + toAdd[j]);
                }
            }
        }
    }

    private int[] getScores(Card card) {
        if (card.value().getValue() > 1) {
            return new int[]{Math.min(card.value().getValue(), 10)};
        } else {
            return new int[]{1, 11};
        }
    }

    private static boolean isAce(Card c) {
        return c.value() == FaceValue.Ace;
    }

    private static boolean isFaceCard(Card c) {
        return c.value().getValue() >= 11 && c.value().getValue() <= 13;
    }
}
