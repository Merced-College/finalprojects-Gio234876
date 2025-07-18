import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Set up players arralist data structure
        List<Player> players = Player.setupPlayers();

        // Create a deck
        Deck deck = new Deck();

        // Draw and display the initial card
        UnoColorSelector.Card initialCard = deck.drawCard();
        System.out.println("Initial card on the table: " + initialCard.getColor() + " " + initialCard.getNumber());
        System.out.println();

        // Deal cards to players and show their hands
        int cardsPerPlayer = 5;
        Player.dealCardsToPlayers(players, deck, cardsPerPlayer);

        Scanner scanner = new Scanner(System.in);
        UnoColorSelector.Color currentColor = initialCard.getColor();
        int currentNumber = initialCard.getNumber();
        boolean gameWon = false;

        while (!gameWon) {
            for (Player player : players) {
                System.out.println(player.getName() + "'s turn!");
                player.showHand();
                System.out.println("Current card: " + currentColor + " " + currentNumber);

                // Check if player has a matching color or number card
                boolean hasMatch = false;
                for (int i = 0; i < player.getHand().size(); i++) {
                    UnoColorSelector.Card card = player.getHand().get(i);
                    if (card.getColor() == currentColor || card.getNumber() == currentNumber) {
                        hasMatch = true;
                        break;
                    }
                }

                if (hasMatch) {
                    System.out.print("You have a matching card. Enter the number (1-" + player.getHand().size() + ") of the card to place down, or 'd' to draw: ");
                } else {
                    System.out.print("No matching card. Enter 'd' to draw a card: ");
                }
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("d")) {
                    UnoColorSelector.Card drawn = deck.drawCard();
                    if (drawn != null) {
                        player.addCard(drawn);
                        System.out.println("You drew: " + drawn);
                    } else {
                        System.out.println("Deck is empty!");
                    }
                } else if (hasMatch) {
                    try {
                        int cardIndex = Integer.parseInt(input) - 1;
                        UnoColorSelector.Card chosen = player.getHand().get(cardIndex);
                        if (chosen.getColor() == currentColor || chosen.getNumber() == currentNumber) {
                            player.getHand().remove(cardIndex);
                            System.out.println("You placed down: " + chosen);
                            currentColor = chosen.getColor(); // update current color
                            currentNumber = chosen.getNumber(); // update current number
                        } else {
                            System.out.println("That card does not match the color or number. Turn skipped.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Turn skipped.");
                    }
                } else {
                    System.out.println("Invalid input. Turn skipped.");
                }

                System.out.println("Cards left in your hand: " + player.getHand().size());
                System.out.println();

                // Check for win condition
                if (player.getHand().isEmpty()) {
                    System.out.println(player.getName() + " has won the game!");
                    gameWon = true;
                    break;
                }
            }
        }
    }
}