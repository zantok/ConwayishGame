package guru.bug.gonwayish.model;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public abstract class Cell {
    protected abstract double calculateViability();

    protected abstract boolean canBeBorn();

}
