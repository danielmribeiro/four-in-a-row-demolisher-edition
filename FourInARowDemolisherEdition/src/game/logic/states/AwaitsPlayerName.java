package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.exceptions.InvalidNumberOfCurrentPlayerIDException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AwaitsPlayerName extends StateAdapter{
    public AwaitsPlayerName(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goBack() {
        try {
            if (getGameData().getCurrentPlayerID() == 1) {
                return new AwaitsGameMode(getGameData());
            } else if (getGameData().getCurrentPlayerID() == 2) {
                getGameData().setCurrentPlayerID(1);
                return new AwaitsPlayerName(getGameData());
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            Logger.getLogger(AwaitsPlayerName.class.getName()).log(Level.SEVERE, null, e);
        }
        return this;
    }

    @Override
    public IState choosePlayerName(String playerName) {
        try {
            if (getGameData().getCurrentPlayerID() == 1) {
                getGameData().setCurrentPlayerName(playerName);
                getGameData().setCurrentPlayerID(2);
                return new AwaitsPlayerName(getGameData());
            } else if (getGameData().getCurrentPlayerID() == 2) {
                if(getGameData().thePlayerNameIsNotBeingUsed(playerName)){
                    getGameData().setCurrentPlayerName(playerName);
                    getGameData().setCurrentPlayerID(); //random ID
                    getGameData().createGameBoard();
                    return new AwaitsTheNextMove(getGameData());
                }else{
                    return this;
                }
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            Logger.getLogger(AwaitsPlayerName.class.getName()).log(Level.SEVERE, null, e);
        }
        return this;
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_PLAYER_NAME;
    }
}
