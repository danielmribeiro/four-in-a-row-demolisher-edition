package game.ui.gui;

import game.logic.GameObservable;
import game.logic.states.AwaitsExit;
import game.logic.states.AwaitsStart;
import game.ui.gui.states.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;
import static game.ui.gui.GUIConstants.*;

public class MainPane extends BorderPane {
    private GameObservable gameObservable;
    private Label gameBoardLabel;
    private Label currentPlayerLabel;

    public MainPane(GameObservable gameObservable) {
        this.gameObservable = gameObservable;
        createView();
        registerObserver();
        refresh();
    }

    private void registerObserver(){
        gameObservable.addPropertyChangeListener(PROPERTY_NAME_FOUR_IN_A_ROW, evt -> {
            refresh();
        });
    }

    private void createView(){

        setPrefSize(DIM_X_FRAME, DIM_Y_FRAME);
        setMinSize(DIM_X_FRAME, DIM_Y_FRAME);

        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        // BOX LEFT
        gameBoardLabel =new Label("GameBoard");
        gameBoardLabel.setFont(GAMEBOARDFONT);

        GameBoardPane gameBoardPane = new GameBoardPane(gameObservable);

        VBox leftBox = new VBox(10);

        leftBox.setPrefSize(LEFT_VBOX_X, LEFT_VBOX_Y);
        leftBox.setMinSize(LEFT_VBOX_X, LEFT_VBOX_Y);
        leftBox.setPadding(new Insets(10, 10, 10, 10));
        leftBox.getChildren().addAll(gameBoardLabel, gameBoardPane);

        // BOX CENTRAL ENVOLVENTE CONTENDO A ESQUERDA E A DIREITA
        HBox center = new HBox(10);
        center.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(leftBox);
        setCenter(center);

        // STATES
        AwaitsColumnChoicePane awaitsColumnChoicePane = new AwaitsColumnChoicePane(gameObservable);
        AwaitsExitPane awaitsExitPane = new AwaitsExitPane(gameObservable);
        AwaitsGameModePane awaitsGameModePane = new AwaitsGameModePane(gameObservable);
        AwaitsMathMiniGamePane awaitsMathMiniGamePane = new AwaitsMathMiniGamePane(gameObservable);
        AwaitsMiniGamePane awaitsMiniGamePane = new AwaitsMiniGamePane(gameObservable);
        AwaitsNextReplayMovePane awaitsNextReplayMovePane = new AwaitsNextReplayMovePane(gameObservable);
        AwaitsPlayerNamePane awaitsPlayerNamePane = new AwaitsPlayerNamePane(gameObservable);
        AwaitsReplayIDPane awaitsReplayIDPane = new AwaitsReplayIDPane(gameObservable);
        AwaitsStartPane awaitsStartPane = new AwaitsStartPane(gameObservable);
        AwaitsTheNextMovePane awaitsTheNextMovePane = new AwaitsTheNextMovePane(gameObservable);
        AwaitsWritingMiniGamePane awaitsWritingMiniGamePane = new AwaitsWritingMiniGamePane(gameObservable);

        // STACKPANE COM OS PAINEIS DOS ESTADOS
        StackPane bottom = new StackPane(awaitsColumnChoicePane,awaitsExitPane,awaitsGameModePane,awaitsMathMiniGamePane,awaitsMiniGamePane,awaitsNextReplayMovePane,awaitsPlayerNamePane,awaitsReplayIDPane,awaitsStartPane,awaitsTheNextMovePane,awaitsWritingMiniGamePane);
        bottom.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        bottom.setPrefSize(DIM_X_BOTTOM_PANEL, DIM_Y_BOTTOM_PANEL);
        bottom.setMinSize(DIM_X_BOTTOM_PANEL, DIM_Y_BOTTOM_PANEL);

        setBottom(bottom);
    }

    private void refresh(){

    }
}
