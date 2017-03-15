package guru.bug.gonwayish.model;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private Map<Position, AbstractCell> fieldMap = new HashMap<>();
    private boolean running;

    public boolean isRunning() {
        return running;
    }
}
