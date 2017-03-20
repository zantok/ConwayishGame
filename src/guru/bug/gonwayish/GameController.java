package guru.bug.gonwayish;

import guru.bug.gonwayish.model.CellInfo;
import guru.bug.gonwayish.model.Field;
import guru.bug.gonwayish.model.Position;
import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class GameController extends AnimationTimer {
    private static final double CELL_WIDTH = 50;
    private static final double CELL_HEIGHT = 50;
    private static final double MAX_RADIUS = 24;
    private final Map<Position, CheckBox> checkBoxMap = new HashMap<>();
    private final Map<Position, Circle> circlesMap = new HashMap<>();
    private Field field = new Field();
    public GridPane gameTableEdit;
    public GridPane gameTableRun;
    public TabPane tabPane;
    public Tab editTab;
    public Tab runTab;


    public void initialize() {
        fillGrid(gameTableEdit, p -> {
            CheckBox cb = new CheckBox();
            StackPane sp = new StackPane(cb);
            sp.setPrefSize(CELL_WIDTH, CELL_HEIGHT);
            checkBoxMap.put(p, cb);
            return sp;
        });
        fillGrid(gameTableRun, p -> {
            Circle circle = new Circle(0);
            circle.setFill(Color.BLUE);
            StackPane sp = new StackPane(circle);
            sp.setPrefSize(CELL_WIDTH, CELL_HEIGHT);
            sp.setMaxSize(CELL_WIDTH, CELL_HEIGHT);
            circlesMap.put(p, circle);
            return sp;
        });
    }

    private void fillGrid(GridPane grid, Function<Position, Node> nodeFactory) {
        setupColumns(grid);
        setupRows(grid);
        Position.all()
                .forEach(p -> {
                    Node node = nodeFactory.apply(p);
                    grid.add(node, p.getColumn(), p.getRow());
                });
    }

    private void setupColumns(GridPane grid) {
        grid.getColumnConstraints().clear();
        for (int c = 0; c < Position.WIDTH; c++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHalignment(HPos.CENTER);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(c, cc);
        }
    }

    private void setupRows(GridPane grid) {
        grid.getRowConstraints().clear();
        for (int r = 0; r < Position.HEIGHT; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setValignment(VPos.CENTER);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(r, rc);
        }
    }

    public void selectionChanged() {
        if (editTab.isSelected()) {
            field.stop();
            stop();
        } else {
            field.start(p -> checkBoxMap.get(p).isSelected());
            start();
        }
    }

    @Override
    public void handle(long now) {
        Map<Position, CellInfo> snapshot = field.getSnapshot();
        circlesMap.entrySet().forEach(e -> {
            Position p = e.getKey();
            CellInfo info = snapshot.get(p);
            Circle cir = e.getValue();
            if (info.isAlive()) {
                cir.setVisible(true);
                double size = snapshot.get(p).getSize();
                cir.setRadius(size * MAX_RADIUS);
            } else {
                cir.setVisible(false);
            }
        });
    }
}
