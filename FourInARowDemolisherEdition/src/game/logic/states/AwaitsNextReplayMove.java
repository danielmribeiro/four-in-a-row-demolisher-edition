package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsNextReplayMove extends StateAdapter{
    public AwaitsNextReplayMove(GameData gameData) {
        super(gameData);
        getGameData().resetReplay();
    }

    @Override
    public IState goBack() {
        return new AwaitsReplayID(getGameData());
    }

    @Override
    public IState nextReplayMove(boolean isForward) {
        if(isForward){
            getGameData().redo();
        }else{
            getGameData().undo();
        }
        return this;
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_NEXT_REPLAY_MOVE;
    }
}
