package guru.bug.gonwayish;

import guru.bug.gonwayish.model.Position;
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

import java.util.function.Supplier;

public class Controller {
    public GridPane gameTableEdit;
    public GridPane gameTableRun;
    public TabPane tabPane;
    public Tab editTab;
    public Tab runTab;

    public void initialize() {
        fillGrid(gameTableEdit, CheckBox::new);
        fillGrid(gameTableRun, () -> new Circle(10));
    }

    private void fillGrid(GridPane grid, Supplier<Node> supplier) {
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
            for (int c = 0; c < Position.WIDTH; c++) {
                Node node = supplier.get();
                grid.add(node, c, r);
            }
        }
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
}
