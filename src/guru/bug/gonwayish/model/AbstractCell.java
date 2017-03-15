package guru.bug.gonwayish.model;

public abstract class AbstractCell implements Runnable {
    private Field field;
    private boolean alive;
    private long birthtime;
    private double viability;

    void setField(Field field) {
        this.field = field;
    }

    @Override
    public final void run() {
        while (field.isRunning()) {
            if (alive) {
                checkViability();
            } else if (canBeBorn()) {
                birth();
            }
            sleep();
        }
    }

    private void checkViability() {
        double v = calculateViability();
        double vt = calculateViabilityThreshold();
        if (v < vt) {
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

    public long getAge() {
        return alive ? System.currentTimeMillis() - birthtime : -1;
    }

    public boolean isAlive() {
        return alive;
    }

    protected abstract double calculateViabilityThreshold();
    protected abstract double calculateViability();
    protected abstract boolean canBeBorn();
}
