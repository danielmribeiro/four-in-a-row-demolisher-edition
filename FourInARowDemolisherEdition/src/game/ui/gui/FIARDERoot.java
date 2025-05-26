package game.ui.gui;

import game.logic.GameObservable;
import game.ui.gui.resources.ImageLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class FIARDERoot extends BorderPane {
    private GameObservable gameObservable;
    private MainPane mainPane;

    public FIARDERoot(GameObservable gameObservable) {
        this.gameObservable = gameObservable;
        createView();
        menus();
    }

    private void createView(){
        Image img = ImageLoader.getImage(GUIConstants.BACKGROUND);
        if(img != null) {
            BackgroundSize backgroundSize = new BackgroundSize(0, 0, false, false, false, true);
            BackgroundImage myBI = new BackgroundImage(img,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    backgroundSize);

            setBackground(new Background(myBI));
        }
        mainPane = new MainPane(gameObservable);
        setCenter(mainPane);
    }

    private void menus() {
        MenuBar menuBar = new MenuBar();
        setTop(menuBar);

        // menu Jogo
        Menu helpMenu = new Menu("Help");  // underscore: abre com alt + j

        MenuItem howToPlayMI = new MenuItem("How to Play");
        howToPlayMI.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));

        MenuItem aboutMI = new MenuItem("About");
        aboutMI.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

        helpMenu.getItems().addAll(howToPlayMI, aboutMI);

        howToPlayMI.setOnAction(new HowToPlayListener());
        aboutMI.setOnAction(new AboutListener());

        menuBar.getMenus().addAll(helpMenu);
    }

    class HowToPlayListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("How To Play");
            alert.setContentText("Step 1 - Choose a game Piece\nStep 2 - Choose a column\n\nYou can choose a special piece on step 1 if you have at least 1 special piece available. This kind of game pieces will demolish the selected column.\nYou can also undo a maximum of 5 plays.");
            alert.showAndWait();
        }
    }

    class AboutListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("About");
            alert.setContentText("Daniel Moreira Ribeiro - 2017013425 - ISEC - Programação Avançada 20/21");
            alert.showAndWait();
        }
    }

}
