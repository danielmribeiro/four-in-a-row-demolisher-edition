package game;

import game.logic.Game;
import game.logic.GameObservable;
import game.ui.gui.FIARDERoot;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FourInARowDemolisherEditionGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(
            Stage primaryStage){
        Game game = new Game();
        GameObservable gameObservable = new GameObservable(game);

        FIARDERoot fiardeRoot = new FIARDERoot(gameObservable);

        Scene scene = new Scene(fiardeRoot);
        primaryStage.setResizable(false);
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Four In A Row - DEMOLISHER Edition");
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        primaryStage.show();
    }
}
