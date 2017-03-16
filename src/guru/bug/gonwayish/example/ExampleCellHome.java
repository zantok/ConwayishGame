package guru.bug.gonwayish.example;

import guru.bug.gonwayish.model.CellHome;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class ExampleCellHome extends CellHome {

    @Override
    protected double calculateViability() {
        return 0;
    }

    @Override
    protected boolean canBeBorn() {
        return false;
    }
}
