package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsWritingMiniGamePane extends HBox {

    private GameObservable gameObservable;
    private TextField writingAnswerTextField;
    Button readyButton;

    public AwaitsWritingMiniGamePane(GameObservable gameObservable) {
        this.gameObservable = gameObservable;
        createViewAndRegisterListeners();
        registerObserver();
        refresh();
    }

    private void registerObserver(){
        gameObservable.addPropertyChangeListener(PROPERTY_NAME_FOUR_IN_A_ROW, evt -> {
            refresh();
        });
    }

    private void createViewAndRegisterListeners(){
        writingAnswerTextField = new TextField("");
        writingAnswerTextField.setMaxWidth(50);
        readyButton = new Button("Ready");

        setAlignment(Pos.CENTER);
        getChildren().addAll(writingAnswerTextField, readyButton);
        readyButton.setOnAction((e)->gameObservable.answerTheWritingMiniGame((writingAnswerTextField.getText()).trim()));
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_WRITING_MINI_GAME);
    }
}
