public class UnoColorSelector {
    public enum Color {
        RED, GREEN, BLUE, YELLOW //Define the colors used in Uno
    }
    // Represents a card in Uno with a color and number
    public record Card(Color color, int number) {
        @Override 
        public String toString() {
            return color + " " + number; //returns the string of the card
        }
    }
}



