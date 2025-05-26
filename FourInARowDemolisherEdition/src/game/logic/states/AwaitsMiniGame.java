package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsMiniGame extends StateAdapter{
    public AwaitsMiniGame(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState playMiniGame(boolean decision) {
        if(decision==false){
            getGameData().restartCountingCurrentPlayerMovesAfterMiniGame();
            return new AwaitsTheNextMove(getGameData());
        }else if(decision==true){
            if(getGameData().isCurrentPlayerPreviousMiniGameAboutMath()){
                getGameData().createMiniGame(true);
                getGameData().generateANewQuestion();
                return new AwaitsWritingMiniGame(getGameData());
            }else{
                getGameData().createMiniGame(false);
                getGameData().generateANewQuestion();
                return new AwaitsMathMiniGame(getGameData());
            }
        }
        return this;
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_MINI_GAME;
    }
}
