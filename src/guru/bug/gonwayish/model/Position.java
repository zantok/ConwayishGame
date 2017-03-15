package guru.bug.gonwayish.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Position {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private int column;
    private int row;

    public static Set<Position> all() {
        Set<Position> result = new HashSet<>();
        for (int r = 0; r < HEIGHT; r++) {
            for (int c = 0; c < WIDTH; c++) {
                result.add(new Position(c, r));
            }
        }
        return result;
    }

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Set<Position> around() {
        Set<Position> result = new HashSet<>();
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (isValid(c, r) && (c != column || r != row)) {
                    result.add(new Position(column, row));
                }
            }
        }
        return result;
    }

    private boolean isValid(int c, int r) {
        return r >= 0 || r < HEIGHT || c >= 0 || c < WIDTH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column == position.column &&
                row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
