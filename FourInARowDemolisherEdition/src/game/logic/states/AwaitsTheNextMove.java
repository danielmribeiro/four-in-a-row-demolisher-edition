package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsTheNextMove extends StateAdapter{
    public AwaitsTheNextMove(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goBack() {
        return new AwaitsStart(getGameData());
    }

    @Override
    public IState chooseGamePiece(boolean isSpecial) {
        getGameData().playGamePiece(isSpecial);
        return new AwaitsColumnChoice(getGameData());
    }

    @Override
    public IState undoMove() {
        if(getGameData().getCurrentPlayerNumberOfReverseMovesAvailable()>0){
            if(getGameData().undo()){
                getGameData().removeOneReverseMoveAvailableForTheCurrentPlayer();
                getGameData().restartCountingCurrentPlayerMovesAfterMiniGame();
            }
        }
        return this;
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_THE_NEXT_MOVE;
    }
}
