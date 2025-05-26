package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsNextReplayMovePane extends HBox {
    private GameObservable gameObservable;

    public AwaitsNextReplayMovePane(GameObservable gameObservable) {
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
        Button nextMoveButton = new Button("Next Move");
        Button previousMoveButton = new Button("Previous Move");
        Button backButton = new Button("Return back");

        setAlignment(Pos.CENTER);
        getChildren().addAll(previousMoveButton, nextMoveButton,backButton);
        previousMoveButton.setOnAction((e)->gameObservable.nextReplayMove(false));
        nextMoveButton.setOnAction((e)->gameObservable.nextReplayMove(true));
        backButton.setOnAction((e)-> gameObservable.goBack());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_NEXT_REPLAY_MOVE );
    }
}
