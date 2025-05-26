package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsReplayIDPane extends HBox {
    private GameObservable gameObservable;

    public AwaitsReplayIDPane(GameObservable gameObservable) {
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
        Button Replay1Button = new Button("Replay 1");
        Button Replay2Button = new Button("Replay 2");
        Button Replay3Button = new Button("Replay 3");
        Button Replay4Button = new Button("Replay 4");
        Button Replay5Button = new Button("Replay 5");
        Button backButton = new Button("Return to Main Menu");

        setAlignment(Pos.CENTER);
        getChildren().addAll(Replay1Button, Replay2Button, Replay3Button,Replay4Button,Replay5Button,backButton);
        Replay1Button.setOnAction((e)->gameObservable.loadReplay(1));
        Replay1Button.setOnAction((e)->gameObservable.loadReplay(2));
        Replay1Button.setOnAction((e)->gameObservable.loadReplay(3));
        Replay1Button.setOnAction((e)->gameObservable.loadReplay(4));
        Replay1Button.setOnAction((e)->gameObservable.loadReplay(5));
        backButton.setOnAction((e)-> gameObservable.goBack());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_REPLAY_ID );
    }

}
