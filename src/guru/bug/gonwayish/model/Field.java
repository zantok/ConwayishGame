package guru.bug.gonwayish.model;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Field {
    private final Map<Position, CellHome> fieldMap;
    private boolean running;

    public Field(Function<Position, ? extends CellHome> cellFactory) {
        fieldMap = Position.all().stream()
                .map(p -> {
                    CellHome result = cellFactory.apply(p);
                    result.init(this, p);
                    return result;
                })
                .collect(Collectors.toMap(CellHome::getPosition, Function.identity()));
    }

    public boolean isRunning() {
        return running;
    }

    public Map<Position, CellHome> getFieldMap() {
        return fieldMap;
    }

    public Set<CellHome> findAround(Position pos) {
        return pos.around().stream()
                .map(fieldMap::get)
                .collect(Collectors.toSet());
    }
}
