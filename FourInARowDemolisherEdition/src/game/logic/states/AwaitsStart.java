package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsStart extends StateAdapter{
    public AwaitsStart(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState start() {
        getGameData().resetGame();
        return new AwaitsGameMode(getGameData());
    }

    @Override
    public IState watchReplay() {
        return new AwaitsReplayID(getGameData());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_START;
    }
}
