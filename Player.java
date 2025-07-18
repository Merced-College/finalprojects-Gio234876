import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final String name;
    private final List<UnoColorSelector.Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(UnoColorSelector.Card card) {
        hand.add(card);
    }

    public void showHand() {
        for (UnoColorSelector.Card card : hand) {
            System.out.println(card);
        }
    }

    // Simple method to ask how many players will play and collect their names
    public static List<Player> setupPlayers() {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.print("How many players will be playing the game? ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = "Player" + i;
            }
            players.add(new Player(name));
        }

        return players;
    }
}