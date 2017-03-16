package guru.bug.gonwayish.example;

import guru.bug.gonwayish.GameMain;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class ExampleGame {

    public static void main(String[] args) {
        GameMain.startGame(p -> new ExampleCellHome());
    }

}
