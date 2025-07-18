public class UnoColorSelector {
    public enum Color {
        RED, GREEN, BLUE, YELLOW
    }

    public record Card(Color color, int number) {
        @Override
        public String toString() {
            return color + " " + number;
        }
    }
}