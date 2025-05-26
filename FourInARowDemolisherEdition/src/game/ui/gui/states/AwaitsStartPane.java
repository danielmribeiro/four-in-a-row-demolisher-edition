package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsStartPane extends HBox {
    private GameObservable gameObservable;

    public AwaitsStartPane(GameObservable gameObservable) {
        this.gameObservable = gameObservable;
        createViewAndRegisterListeners();
        registerObserver();
        refresh();
    }

    private void registerObserver(){
        // regista um observador do jogoObservavel
        gameObservable.addPropertyChangeListener(PROPERTY_NAME_FOUR_IN_A_ROW, evt -> {
            refresh();
        });
    }

    private void createViewAndRegisterListeners(){
        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load Game");
        Button replaysButton = new Button("Replays");
        Button quitButton = new Button("Quit");

        setAlignment(Pos.CENTER);
        getChildren().addAll(newGameButton, loadGameButton, replaysButton,quitButton);
        newGameButton.setOnAction((e)->gameObservable.start());
        loadGameButton.setOnAction(new LoadGameListener());
        replaysButton.setOnAction((e)->gameObservable.watchReplay());
        quitButton.setOnAction((e)-> Platform.exit());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_START );
    }

    class LoadGameListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./temp/saved"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                gameObservable.loadGame(selectedFile);
            } else {
                System.err.println("Leitura cancelada ");
            }
        }
    }
}
