package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsTheNextMovePane extends HBox {
    private GameObservable gameObservable;
    private TextField playerNameTextField;
    Button normalPieceButton;
    Button specialPieceButton;
    Button reverseMoveButton;
    Button saveButton;
    Button backButton;

    public AwaitsTheNextMovePane(GameObservable gameObservable) {
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
        normalPieceButton = new Button("Normal Piece");
        specialPieceButton = new Button("Special Piece");
        reverseMoveButton = new Button("Reverse Move");
        saveButton = new Button("Save Game");
        backButton = new Button("Return to Main Menu");

        setAlignment(Pos.CENTER);
        getChildren().addAll(normalPieceButton, specialPieceButton, reverseMoveButton,saveButton,backButton);
        normalPieceButton.setOnAction((e)->gameObservable.chooseGamePiece(false));
        specialPieceButton.setOnAction((e) -> gameObservable.chooseGamePiece(true));
        reverseMoveButton.setOnAction((e)->gameObservable.undoMove());
        saveButton.setOnAction(new SaveGameListener());
        backButton.setOnAction((e)->gameObservable.goBack());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_THE_NEXT_MOVE);
    }

    class SaveGameListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./temp/saved"));
            File selectedFile = fileChooser.showSaveDialog(null);
            if (selectedFile != null) {
                gameObservable.save(selectedFile);
            } else {
                System.err.println("Gravacao cancelada ");
            }
        }
    }
}
