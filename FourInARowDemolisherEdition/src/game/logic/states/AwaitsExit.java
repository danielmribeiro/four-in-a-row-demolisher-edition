package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsExit extends StateAdapter{
    public AwaitsExit(GameData gameData) {
        super(gameData);
        getGameData().saveReplay(gameData);
    }

    @Override
    public IState goBack() {
        return new AwaitsStart(getGameData());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_EXIT;
    }
}
