package house.lift;

public enum Direction {
    UP('\u25B2'),
    DOWN('\u25BC');

    private char label;

    Direction(char label) {
        this.label = label;
    }

    public char getLabel() {
        return label;
    }
}
