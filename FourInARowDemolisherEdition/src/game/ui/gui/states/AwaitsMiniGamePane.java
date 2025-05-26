package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsMiniGamePane extends HBox {
    private GameObservable gameObservable;

    public AwaitsMiniGamePane(GameObservable gameObservable) {
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
        Label questionLabel = new Label("Do you want to play the minigame?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        setAlignment(Pos.CENTER);
        getChildren().addAll(questionLabel, yesButton, noButton);
        yesButton.setOnAction((e)->gameObservable.playMiniGame(true));
        noButton.setOnAction((e)-> gameObservable.playMiniGame(false));
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_MINI_GAME );
    }

}
