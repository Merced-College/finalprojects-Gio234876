import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Set up players
        List<Player> players = Player.setupPlayers();

        // Create a deck
        Deck deck = new Deck();

        // Deal 5 cards to each player and show their hand
        int cardsPerPlayer = 5;
        for (Player player : players) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                UnoColorSelector.Card card = deck.drawCard();
                if (card != null) {
                    player.addCard(card);
                }
            }
            System.out.println(player.getName() + "'s hand:");
            player.showHand();
            System.out.println();
        }
    }
}