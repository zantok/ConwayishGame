package guru.bug.gonwayish;

import guru.bug.gonwayish.model.CellHome;
import guru.bug.gonwayish.model.Position;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.Function;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class GameMain extends Application {

    private static GameController mainGameController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        mainGameController = loader.getController();
        primaryStage.setTitle("Gonwayish Game");
        primaryStage.setScene(new Scene(root, 600, 650));
        primaryStage.show();
    }

    public static void startGame(Function<Position, ? extends CellHome> cellFactory) {
        Application.launch();
        mainGameController.setCellFactory(cellFactory);
    }

}
