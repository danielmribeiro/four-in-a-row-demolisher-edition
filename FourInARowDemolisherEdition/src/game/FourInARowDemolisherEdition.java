package game;

import game.logic.Game;
import game.ui.tui.TextUI;

public class FourInARowDemolisherEdition {
    public static void main(String[] args){
        Game game = new Game();
        TextUI tui = new TextUI(game);
        tui.run();
    }
}
