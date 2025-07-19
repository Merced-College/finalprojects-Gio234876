import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final String name;
    private final List<UnoColorSelector.Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>(); //arrayList data structure to store the player's hand
    }

    public String getName() {
        return name; //returns the name of the player
    }

    public void addCard(UnoColorSelector.Card card) {
        hand.add(card); //adds a card to the player's hand
    }

    public void showHand() {
        for (UnoColorSelector.Card card : hand) {
            System.out.println(card); //prints each card in the player's hand
        }
    }

    //Sorts the player's hand by card number using selection sort.
    //Data Structure: ArrayList used to store the player's hand.
    //Sorting Algorithm: Selection Sort 

    public void sortHandByNumber() {
        // --- Selection Sort Algorithm ---
        for (int i = 0; i < hand.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(j).number() < hand.get(minIndex).number()) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                UnoColorSelector.Card temp = hand.get(i);
                hand.set(i, hand.get(minIndex));
                hand.set(minIndex, temp);
            }
        }
    }

    
     //Sorts the player's hand by color, then by number using merge sort.
     //Data Structure: ArrayList used to store the player's hand.
     //Sorting Algorithm: Merge Sort (see method below)
    
    public void sortHandByColorThenNumber() {
        if (hand.size() <= 1) return;
        handMergeSort(0, hand.size() - 1);
    }

    //Merge Sort Algorithm
    private void handMergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            handMergeSort(left, mid);
            handMergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }
    // Merges two sorted halves of the hand
    private void merge(int left, int mid, int right) {
        List<UnoColorSelector.Card> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        // Merge the two halves based on color and number
        while (i <= mid && j <= right) {
            UnoColorSelector.Card cardI = hand.get(i);
            UnoColorSelector.Card cardJ = hand.get(j);
            // Compare by color first, then by number if colors are equal
            if (cardI.color().ordinal() < cardJ.color().ordinal() ||
                (cardI.color() == cardJ.color() && cardI.number() <= cardJ.number())) {
                temp.add(cardI);
                i++;
            } else {
                temp.add(cardJ);
                j++;
            }
        }
        while (i <= mid) {
            temp.add(hand.get(i)); // Add remaining cards from left half
            i++;
        }
        while (j <= right) {
            temp.add(hand.get(j)); // Add remaining cards from right half
            j++;
        }
        // Copy sorted temp list back into hand
        for (int k = 0; k < temp.size(); k++) {
            hand.set(left + k, temp.get(k));
        }
    }

    
     //Sorts the player's hand by color only using insertion sort.

    public void sortHandByColor() {
        for (int i = 1; i < hand.size(); i++) {
            UnoColorSelector.Card key = hand.get(i);
            int j = i - 1;
            while (j >= 0 && hand.get(j).color().ordinal() > key.color().ordinal()) {
                hand.set(j + 1, hand.get(j));
                j--;
            }
            hand.set(j + 1, key);
        }
    }


    // Simple method to ask how many players will play and collect their names
    // Data Structure: ArrayList used to store the list of players

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
                name = "Player" + i; // Default name if input is empty
            }
            players.add(new Player(name)); // Create a new player with the given name
        }

        return players;
    }

    // Deals cards to each player from the deck
    public static void dealCardsToPlayers(List<Player> players, Deck deck, int cardsPerPlayer) {
        for (Player player : players) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                UnoColorSelector.Card card = deck.drawCard();
                if (card != null) {
                    player.addCard(card);
                }
            }
            player.sortHandByColor(); //Make sure to call this!
            System.out.println(player.getName() + "'s hand:");
            player.showHand();
            System.out.println(); // Show the player's hand after dealing cards
        }
    }

    
    //Returns the player's hand of cards.
    public List<UnoColorSelector.Card> getHand() {
        return hand;
    }
}