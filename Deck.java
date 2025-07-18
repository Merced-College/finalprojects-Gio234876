import java.util.Random;
import java.util.Stack;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Deck {
    private Stack<UnoColorSelector.Card> cards;
    private static final int TOTAL_CARDS = 40; // 4 colors x 10 numbers

    //uses a stack for the deck to allow easy drawing of cards
    public Deck() {

        cards = new Stack<>();
        List<UnoColorSelector.Card> tempList = new ArrayList<>();
        // Create one card for each color and number (0-9)
        for (UnoColorSelector.Color color : UnoColorSelector.Color.values()) {
            for (int number = 0; number <= 9; number++) {
                tempList.add(new UnoColorSelector.Card(color, number));
            }
        }
        // Shuffle the cards
        Collections.shuffle(tempList, new Random());
        // Add to stack
        cards.addAll(tempList);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public UnoColorSelector.Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.pop();
        }
        return null;
    }
}