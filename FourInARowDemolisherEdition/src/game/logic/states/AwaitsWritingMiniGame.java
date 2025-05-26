package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsWritingMiniGame extends StateAdapter{
    public AwaitsWritingMiniGame(GameData gameData) {
        super(gameData);
        getGameData().startCountingTheTime();
    }

    @Override
    public IState answerTheWritingMiniGame(String answer) {
        getGameData().stopCountingTheTime();
        getGameData().playWritingMiniGame(answer);
        getGameData().restartCountingCurrentPlayerMovesAfterMiniGame();
        getGameData().changeCurrentPlayerNextMiniGame(false);
        if(getGameData().isTheWritingMiniGameCompletedCorrectly()){
            getGameData().incrementTheNumberOfSpecialGamePieces();
        }else{
            getGameData().swapCurrentPlayer();
            if(getGameData().isTimeToPlayTheMiniGame()){
                return new AwaitsMiniGame(getGameData());
            }
        }
        return new AwaitsTheNextMove(getGameData());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_WRITING_MINI_GAME;
    }
}
