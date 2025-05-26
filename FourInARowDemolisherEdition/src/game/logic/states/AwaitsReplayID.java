package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitsReplayID extends StateAdapter{
    public AwaitsReplayID(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goBack() {
        return new AwaitsStart(getGameData());
    }

    @Override
    public IState loadReplay(GameData gamedata) {
        return new AwaitsNextReplayMove(gamedata);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AWAITS_REPLAY_ID;
    }
}
