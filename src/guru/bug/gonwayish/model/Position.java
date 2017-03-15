package guru.bug.gonwayish.model;

import java.util.Objects;

public class Position {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private int column;
    private int row;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
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
