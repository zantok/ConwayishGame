package guru.bug.gonwayish.model;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class CellInfo {
    private final Position position;
    private final boolean alive;
    private final double size;

    public CellInfo(Position position, boolean alive, double size) {
        this.position = position;
        this.alive = alive;
        this.size = size;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public double getSize() {
        return size;
    }
}
