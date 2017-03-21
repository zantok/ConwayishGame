package guru.bug.gonwayish.model;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Cell implements Runnable {
    private static final long LIFE_PERIOD = 20000; // milliseconds
    private final Field field;
    private final Position position;
    private double size;
    private long birthtime;

    Cell(Field field, Position position, boolean initialAlive) {
        this.field = field;
        this.position = position;
        this.size = 0;

        if (initialAlive) {
            birthtime = System.currentTimeMillis();
        } else {
            birthtime = -1;
        }
    }

    public Position getPosition() {
        return position;
    }

    public Field getField() {
        return field;
    }

    @Override
    public void run() {
        while (field.isRunning()) {
            pause();
            long cur = System.currentTimeMillis();
            long age = cur - birthtime;

            if (age > LIFE_PERIOD) {
                System.out.println("Cell " + position + " is too old");
                updateCellInfo(-1, 0);
                break;
            }

            double p = (age - LIFE_PERIOD / 2.0) / LIFE_PERIOD * Math.PI;
            double s = Math.cos(p);
            setSize(s);
        }
        System.out.println("Cell " + position + " finished");
    }

    private void pause() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    private synchronized void updateCellInfo(long birthtime, double size) {
        setBirthtime(birthtime);
        setSize(size);
    }

    private synchronized void setSize(double size) {
        this.size = size;
    }

    private synchronized void setBirthtime(long birthtime) {
        this.birthtime = birthtime;
    }

    public synchronized CellInfo getCellInfo() {
        return new CellInfo(position, birthtime > -1, size);
    }
}
