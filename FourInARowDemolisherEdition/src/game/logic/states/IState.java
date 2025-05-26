package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

import java.io.Serializable;

public interface IState extends Serializable {
    IState start();
    IState watchReplay();
    IState goBack();
    IState chooseGameMode(int numberOfHumanPlayers);
    IState choosePlayerName(String playerName);
    IState chooseGamePiece(boolean isSpecial);
    IState undoMove();
    IState chooseColumn(int column);
    IState playMiniGame(boolean decision);
    IState answerTheWritingMiniGame(String answer);
    IState answerTheMathMiniGame(int answer);
    IState loadReplay(GameData gameData);
    IState nextReplayMove(boolean isForward);
    Situation getCurrentSituation();
}
