package guru.bug.gonwayish.model;

import java.awt.*;
import java.util.Set;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Cell implements Runnable {
    private static final long LIFE_PERIOD = 2000; // milliseconds
    private final Field field;
    private final Position position;
    private double size;
    private long birthtime;

    Cell(Field field, Position position, boolean initialAlive) {
        this.field = field;
        this.position = position;

        if (initialAlive) {
            this.birthtime = System.currentTimeMillis();
            this.size = 1;
        } else {
            this.birthtime = -1;
            this.size = 0;
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
        waitUntilFieldReady();
        while (field.isRunning()) {
            pause();
            long bt = getBirthtime();
            long cur = System.currentTimeMillis();

            Set<Cell> around = field.findAround(position);
            long liveCount = around.stream()
                    .map(Cell::getCellInfo)
                    .filter(CellInfo::isAlive)
                    .count();

            if (bt == -1 && liveCount == 3) {
                bt = System.currentTimeMillis();
                updateCellInfo(bt, 1);
            }

            long age = cur - bt;

            if (age > LIFE_PERIOD && bt != -1) {
                System.out.println("Cell " + position + " is too old");
                updateCellInfo(-1, 0);
            }

        }
        System.out.println("Cell " + position + " finished");
    }

    private void waitUntilFieldReady() {
        synchronized (field) {
            while (!field.isRunning()) {
                try {
                    field.wait();
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }
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

    private synchronized long getBirthtime() {
        return birthtime;
    }

    public synchronized CellInfo getCellInfo() {
        return new CellInfo(position, birthtime > -1, size);
    }
}
