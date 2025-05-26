package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsExitPane extends HBox {
    private GameObservable gameObservable;

    public AwaitsExitPane(GameObservable gameObservable) {
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
        Button quitButton = new Button("Quit");
        Button backButton = new Button("Return to Main Menu");

        setAlignment(Pos.CENTER);
        getChildren().addAll(quitButton,backButton);
        quitButton.setOnAction((e)->Platform.exit());
        backButton.setOnAction((e)-> gameObservable.goBack());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_EXIT );
    }

}
