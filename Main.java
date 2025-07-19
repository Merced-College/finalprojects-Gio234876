import java.util.List;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Set up players arralist data structure
        List<Player> players = Player.setupPlayers();

        // Create a deck
        Deck deck = new Deck();
        Stack<UnoColorSelector.Card> discardPile = new Stack<>();

        // Draw and display the initial card
        UnoColorSelector.Card initialCard = deck.drawCard();
        System.out.println("Initial card on the table: " + initialCard.color() + " " + initialCard.number());
        System.out.println();
        discardPile.push(initialCard); // Add initial card to discard pile

        // Deal cards to players and show their hands
        int cardsPerPlayer = 5;
        Player.dealCardsToPlayers(players, deck, cardsPerPlayer);

        Scanner scanner = new Scanner(System.in);
        UnoColorSelector.Color currentColor = initialCard.color();
        int currentNumber = initialCard.number();
        boolean gameWon = false;

        // After setting up players:
        Queue<Player> playerQueue = new LinkedList<>(players);

        while (!gameWon) {
            Player player = playerQueue.poll(); // Get the next player

            System.out.println(player.getName() + "'s turn!");
            player.showHand();
            System.out.println("Current card: " + currentColor + " " + currentNumber);

            // Check if player has a matching color or number card
            boolean hasMatch = false;
            for (int i = 0; i < player.getHand().size(); i++) {
                UnoColorSelector.Card card = player.getHand().get(i);
                if (card.color() == currentColor || card.number() == currentNumber) {
                    hasMatch = true;
                    break;
                }
            }

            if (hasMatch) {
                System.out.print("You have a matching card. Enter the number (1-" + player.getHand().size() + ") of the card to place down, or 'd' to draw: ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("d")) {
                    UnoColorSelector.Card drawn = deck.drawCard();
                    if (drawn != null) {
                        player.addCard(drawn);
                        System.out.println("You drew: " + drawn);
                    } else {
                        System.out.println("Deck is empty!");
                    }
                } else {
                    try {
                        int cardIndex = Integer.parseInt(input) - 1;
                        UnoColorSelector.Card chosen = player.getHand().get(cardIndex);
                        if (chosen.number() == 10) { // Cat card logic
                            System.out.println("You played the CAT CARD! You may swap a card with any player.");
                            // The player does not lose their turn, so you may allow them to play again or just continue
                            // The cat card can be placed on any card, so no need to check color/number match
                            player.getHand().remove(cardIndex);
                            discardPile.push(chosen);
                            // Optionally: let the player swap a card here
                            // Continue the turn or move to next player as per your rules
                            currentColor = chosen.color();
                            currentNumber = chosen.number();
                        } else if (chosen.color() == currentColor || chosen.number() == currentNumber) {
                            player.getHand().remove(cardIndex);
                            discardPile.push(chosen);
                            System.out.println("You placed down: " + chosen);
                            currentColor = chosen.color();
                            currentNumber = chosen.number();
                        } else {
                            System.out.println("That card does not match the color or number. Turn skipped.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Turn skipped.");
                    }
                }
            } else {
                // Only accept 'd' for draw
                String input;
                do {
                    System.out.print("No matching card. Enter 'd' to draw a card: ");
                    input = scanner.nextLine().trim();
                } while (!input.equalsIgnoreCase("d"));
                UnoColorSelector.Card drawn = deck.drawCard();
                if (drawn != null) {
                    player.addCard(drawn);
                    System.out.println("You drew: " + drawn);
                } else {
                    System.out.println("Deck is empty!");
                }
            }

            System.out.println("Cards left in your hand: " + player.getHand().size());
            System.out.println();

            // Check for win condition
            if (player.getHand().isEmpty()) {
                System.out.println(player.getName() + " has won the game!");
                gameWon = true;
            } else {
                playerQueue.offer(player); // Put player back at the end of the queue if not won
            }
        }
    }
}