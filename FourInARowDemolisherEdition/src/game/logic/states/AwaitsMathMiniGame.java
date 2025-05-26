package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsMathMiniGame extends StateAdapter{
    public AwaitsMathMiniGame(GameData gameData) {
        super(gameData);
        getGameData().startCountingTheTime();
    }

    @Override
    public IState answerTheMathMiniGame(int answer) {
        getGameData().stopCountingTheTime();
        getGameData().playMathMiniGame(answer);
        if(getGameData().isTheMathMiniGameFinished()){
            getGameData().restartCountingCurrentPlayerMovesAfterMiniGame();
            getGameData().changeCurrentPlayerNextMiniGame(true);
            if(getGameData().getNumberOfCorrectAnswers()>=5){
                getGameData().incrementTheNumberOfSpecialGamePieces();
            }else{
                getGameData().swapCurrentPlayer();
                if(getGameData().isTimeToPlayTheMiniGame()){
                    return new AwaitsMiniGame(getGameData());
                }
            }
            return new AwaitsTheNextMove(getGameData());
        }
        getGameData().generateANewQuestion();
        getGameData().startCountingTheTime();
        return this;
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_MATH_MINI_GAME;
    }
}
