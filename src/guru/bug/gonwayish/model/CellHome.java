package guru.bug.gonwayish.model;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class CellHome implements Runnable {
    private Field field;
    private Position position;
    private Cell cell;
    private long birthtime;
    private double viability;

    final void init(Field field, Position position) {
        this.field = field;
        this.position = position;
    }

    @Override
    public final void run() {
        while (field.isRunning()) {
            if (cell != null) {
                checkViability();
            } else if (canBeBorn()) {
                birth();
            }
            sleep();
        }
    }

    private void checkViability() {
        double v = calculateViability();
        if (v <= 0) {
            death();
        } else {
            this.viability = v;
        }
    }

    private void death() {
        alive = false;
        birthtime = -1;
        viability = 0;
    }

    private void birth() {
        alive = true;
        birthtime = System.currentTimeMillis();
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public final long getAge() {
        return alive ? System.currentTimeMillis() - birthtime : -1;
    }

    public final boolean isAlive() {
        return alive;
    }

    public final double getViability() {
        return viability;
    }

    public final Position getPosition() {
        return position;
    }

    public final Field getField() {
        return field;
    }
}
