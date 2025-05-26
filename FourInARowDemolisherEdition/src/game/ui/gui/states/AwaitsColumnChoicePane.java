package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsColumnChoicePane extends HBox {
    private GameObservable gameObservable;

    public AwaitsColumnChoicePane(GameObservable gameObservable) {
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
        Button column1Button = new Button("Column 1");
        Button column2Button = new Button("Column 2");
        Button column3Button = new Button("Column 3");
        Button column4Button = new Button("Column 4");
        Button column5Button = new Button("Column 5");
        Button column6Button = new Button("Column 6");
        Button column7Button = new Button("Column 7");
        Button forwardButton = new Button("Move Forward");
        Button backButton = new Button("Return to Main Menu");

        setAlignment(Pos.CENTER);
        getChildren().addAll(column1Button, column2Button,column3Button,column4Button,column5Button,column6Button,column7Button,backButton);
        column1Button.setOnAction((e)->gameObservable.chooseColumn(0));
        column2Button.setOnAction((e)->gameObservable.chooseColumn(1));
        column3Button.setOnAction((e)->gameObservable.chooseColumn(2));
        column4Button.setOnAction((e)->gameObservable.chooseColumn(3));
        column5Button.setOnAction((e)->gameObservable.chooseColumn(4));
        column6Button.setOnAction((e)->gameObservable.chooseColumn(5));
        column7Button.setOnAction((e)->gameObservable.chooseColumn(6));
        forwardButton.setOnAction((e)->gameObservable.chooseColumn(7));
        backButton.setOnAction((e)-> gameObservable.goBack());
    }

    private void refresh() {
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_COLUMN_CHOICE );
    }

}
