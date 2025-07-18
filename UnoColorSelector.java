public class UnoColorSelector {
    public enum Color {
        RED, GREEN, BLUE, YELLOW
    }

    public static class Card {
        private final Color color;
        private final int number;

        public Card(Color color, int number) {
            this.color = color;
            this.number = number;
        }

        public Color getColor() {
            return color;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return color + " " + number;
        }
    }
}