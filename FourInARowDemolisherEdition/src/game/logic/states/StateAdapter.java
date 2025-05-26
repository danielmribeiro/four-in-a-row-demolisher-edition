package game.logic.states;

import game.logic.data.GameData;

public abstract class StateAdapter implements IState{
    private GameData gameData;

    public StateAdapter(GameData gameData) {
        this.gameData = gameData;
    }

    protected GameData getGameData() {
        return gameData;
    }

    @Override
    public IState start() {
        return this;
    }

    @Override
    public IState watchReplay() {
        return this;
    }

    @Override
    public IState goBack() {
        return this;
    }

    @Override
    public IState chooseGameMode(int numberOfHumanPlayers) {
        return this;
    }

    @Override
    public IState choosePlayerName(String playerName) {
        return this;
    }

    @Override
    public IState chooseGamePiece(boolean isSpecial) {
        return this;
    }

    @Override
    public IState undoMove() {
        return this;
    }

    @Override
    public IState chooseColumn(int column) {
        return this;
    }

    @Override
    public IState playMiniGame(boolean decision) {
        return this;
    }

    @Override
    public IState answerTheWritingMiniGame(String answer) {
        return this;
    }

    @Override
    public IState answerTheMathMiniGame(int answer) {
        return this;
    }

    @Override
    public IState loadReplay(GameData gameData) {
        return this;
    }

    @Override
    public IState nextReplayMove(boolean isForward) {
        return this;
    }

}
