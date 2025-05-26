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

public class AwaitsMathMiniGamePane extends HBox {

    private GameObservable gameObservable;
    private TextField mathAnswerTextField;
    Button readyButton;

    public AwaitsMathMiniGamePane(GameObservable gameObservable) {
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
        mathAnswerTextField = new TextField("");
        mathAnswerTextField.setMaxWidth(50);
        readyButton = new Button("Ready");

        setAlignment(Pos.CENTER);
        getChildren().addAll(mathAnswerTextField, readyButton);
        readyButton.setOnAction(new AnswerListener());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_MATH_MINI_GAME );
    }

    private class AnswerListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            int value = 0;

            try {
                value = getValue();
            } catch (NumberFormatException ex){
                return;
            }

            if (value >= 0){
                gameObservable.answerTheMathMiniGame(value);
            }

        }

    }

    private int getValue() throws NumberFormatException {
        String s1 = (mathAnswerTextField.getText()).trim();
        if (s1.length() < 1){
            throw new NumberFormatException();
        }
        int value = Integer.parseInt(s1);
        return value;
    }
}
