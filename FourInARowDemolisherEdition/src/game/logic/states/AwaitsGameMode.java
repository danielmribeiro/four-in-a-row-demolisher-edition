package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.exceptions.InvalidNumberOfHumanPlayersException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AwaitsGameMode extends StateAdapter{
    public AwaitsGameMode(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goBack() {
        return new AwaitsStart(getGameData());
    }

    @Override
    public IState chooseGameMode(int numberOfHumanPlayers) {
        try{
            if(getGameData().setGameMode(numberOfHumanPlayers)){
                return new AwaitsPlayerName(getGameData());
            }
            throw new InvalidNumberOfHumanPlayersException();
        } catch (InvalidNumberOfHumanPlayersException e) {
            Logger.getLogger(AwaitsGameMode.class.getName()).log(Level.SEVERE, null, e);
        }
        return this;
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_GAME_MODE;
    }
}
