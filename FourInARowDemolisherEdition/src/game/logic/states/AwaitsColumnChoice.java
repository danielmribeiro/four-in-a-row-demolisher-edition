package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.exceptions.InvalidColumnChoiceException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AwaitsColumnChoice extends StateAdapter{
    public AwaitsColumnChoice(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goBack() {
        return new AwaitsTheNextMove(getGameData());
    }

    @Override
    public IState chooseColumn(int column) {
        try {
            getGameData().saveMemento();
            if (getGameData().chooseColumn(column)) {
                getGameData().incrementTheNumberOfPlayerMoves();
                if (getGameData().isTheGameFinished()) {
                    return new AwaitsExit(getGameData());
                }
                getGameData().swapCurrentPlayer();
                if (getGameData().isTimeToPlayTheMiniGame()) {
                    return new AwaitsMiniGame(getGameData());
                } else {
                    return new AwaitsTheNextMove(getGameData());
                }
            }
            throw new InvalidColumnChoiceException();
        } catch (InvalidColumnChoiceException e) {
            Logger.getLogger(AwaitsColumnChoice.class.getName()).log(Level.SEVERE, null, e);
        }
        return this;
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_COLUMN_CHOICE;
    }
}
