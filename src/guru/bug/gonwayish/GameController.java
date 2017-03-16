package guru.bug.gonwayish;

import guru.bug.gonwayish.model.CellHome;
import guru.bug.gonwayish.model.Field;
import guru.bug.gonwayish.model.Position;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;

import java.util.function.Function;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class GameController {
    private final SimpleObjectProperty<Function<Position, ? extends CellHome>> cellFactory = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Field> field = new SimpleObjectProperty<>();
    public GridPane gameTableEdit;
    public GridPane gameTableRun;
    public TabPane tabPane;
    public Tab editTab;
    public Tab runTab;

    public void initialize() {
        cellFactory.addListener((o, ov, nv) -> field.set(new Field(nv)));
        field.addListener((o, ov, nv) -> rebuildUI(nv));
    }

    private void rebuildUI(Field field) {
        gameTableEdit.getChildren().clear();
        gameTableRun.getChildren().clear();
        if (field != null) {
            fillGrid(gameTableEdit, field, CheckBox::new);
            fillGrid(gameTableRun, field, () -> new Circle(10));
        }
    }

    private void fillGrid(GridPane grid, Field field, Function<CellHome, Node> nodeFactory) {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        for (int c = 0; c < Position.WIDTH; c++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHalignment(HPos.CENTER);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(c, cc);
        }
        for (int r = 0; r < Position.HEIGHT; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setValignment(VPos.CENTER);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(r, rc);
        }
        field.getFieldMap().values()
                .forEach(c -> {
                    Node node = nodeFactory.apply(c);
                    Position pos = c.getPosition();
                    grid.add(node, pos.getColumn(), pos.getRow());
                });
    }

    public void selectionChanged(Event event) {
        if (tabPane.getSelectionModel().getSelectedItem() == editTab) {
            stopGame();
        } else {
            startGame();
        }
    }

    private void startGame() {

    }

    private void stopGame() {

    }

    public Function<Position, ? extends CellHome> getCellFactory() {
        return cellFactory.get();
    }

    public ObjectProperty<Function<Position, ? extends CellHome>> cellFactoryProperty() {
        return cellFactory;
    }

    public void setCellFactory(Function<Position, ? extends CellHome> cellFactory) {
        this.cellFactory.set(cellFactory);
    }
}
