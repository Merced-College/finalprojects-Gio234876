import java.util.Random;
import java.util.Stack;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Deck {
    private LinkedList<UnoColorSelector.Card> cards; // Data Structure: LinkedList for efficient add/remove from both ends
    private static final int TOTAL_CARDS = 40; // 4 colors x 10 numbers

    //uses a stack for the deck to allow easy drawing of cards
    public Deck() {

        cards = new LinkedList<>();
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
            return cards.removeFirst(); // Draw from the top/front
        }
        return null;
    }

    /**
     * Adds a card to the bottom of the deck (useful for discard pile or reshuffling).
     */
    public void addToBottom(UnoColorSelector.Card card) {
        cards.addLast(card);
    }
}