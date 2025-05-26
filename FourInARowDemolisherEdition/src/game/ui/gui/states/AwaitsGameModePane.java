package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsGameModePane extends HBox {
    private GameObservable gameObservable;

    public AwaitsGameModePane(GameObservable gameObservable) {
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
        Button cpuVsCpuButton = new Button("CPU vs CPU");
        Button playerVsCpuButton = new Button("Player vs CPU");
        Button playerVsPlayerButton = new Button("Player vs Player");
        Button backButton = new Button("Return to Main Menu");

        setAlignment(Pos.CENTER);
        getChildren().addAll(cpuVsCpuButton, playerVsCpuButton, playerVsPlayerButton,backButton);
        cpuVsCpuButton.setOnAction((e)->gameObservable.chooseGameMode(0));
        playerVsCpuButton.setOnAction((e)->gameObservable.chooseGameMode(1));
        playerVsPlayerButton.setOnAction((e)->gameObservable.chooseGameMode(2));
        backButton.setOnAction((e)-> gameObservable.goBack());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_GAME_MODE );
    }
}
