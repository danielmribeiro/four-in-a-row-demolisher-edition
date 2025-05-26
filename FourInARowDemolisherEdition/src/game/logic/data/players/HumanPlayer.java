package game.logic.data.players;

public class HumanPlayer extends Player{
    public HumanPlayer() {
        super(true);
        numberOfReverseMovesAvailable = 5;
    }
}
