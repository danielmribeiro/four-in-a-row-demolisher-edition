package game.ui.gui.states;

import game.logic.GameObservable;
import game.logic.Situation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class AwaitsPlayerNamePane extends HBox {

    private GameObservable gameObservable;
    private TextField playerNameTextField;
    Button backButton;
    Button readyButton;

    public AwaitsPlayerNamePane(GameObservable gameObservable) {
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
        playerNameTextField = new TextField("Daniel");
        playerNameTextField.setMaxWidth(50);
        readyButton = new Button("Ready");
        backButton = new Button();
        backButton.setText("Return Back");

        setAlignment(Pos.CENTER);
        getChildren().addAll(playerNameTextField, readyButton, backButton);
        readyButton.setOnAction((e)->gameObservable.choosePlayerName((playerNameTextField.getText()).trim()));
        backButton.setOnAction((e) -> gameObservable.goBack());
    }

    private void refresh() {
        /*if(gameObservable.getCurrentPlayerID()==1 && gameObservable.isCurrentPlayerHuman()){
            playerNameTextField.setText("Player 1");
        }else if(gameObservable.getCurrentPlayerID()==1 && (!gameObservable.isCurrentPlayerHuman())){
            playerNameTextField.setText("CPU 1");
        }else if(gameObservable.getCurrentPlayerID()==2 && gameObservable.isCurrentPlayerHuman()){
            playerNameTextField.setText("Player 2");
        }else{
            playerNameTextField.setText("CPU 2");
        }
        if(gameObservable.getCurrentPlayerID()==1) {
            backButton.setText("Return to Game Mode");
        }else {
            backButton.setText("Return to Player 1 Name");
        }*/
        this.setVisible(gameObservable.getCurrentSituation() == Situation.AWAITS_PLAYER_NAME );
    }
}
