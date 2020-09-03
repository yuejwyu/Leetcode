package BlackJack;

public class Card {
    private final FaceValue faceValue;
    private final Suit suit;

    public Card(FaceValue faceValue, Suit suit) {
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public FaceValue value() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }
}
