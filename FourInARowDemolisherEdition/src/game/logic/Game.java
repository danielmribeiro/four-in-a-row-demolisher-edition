package game.logic;

import game.logic.data.GameData;
import game.logic.files.FileUtility;
import game.logic.states.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Game implements Serializable {
    private GameData gameData;
    private IState state;

    public Game() {
        this.gameData = new GameData();
        this.state = new AwaitsStart(gameData);
    }

    public GameData getGameData(){
        return gameData;
    }

    public IState getState(){
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public Situation getCurrentSituation(){
        return state.getCurrentSituation();
    }

    public void start() {
        setState(getState().start());
    }

    public void watchReplay() {
        setState(getState().watchReplay());
    }

    public void chooseGameMode(int numberOfHumanPlayers) {
        setState(getState().chooseGameMode(numberOfHumanPlayers));
    }

    public void goBack() {
        setState(getState().goBack());
    }

    public void choosePlayerName(String playerName) {
        setState(getState().choosePlayerName(playerName));
    }

    public void chooseGamePiece(boolean isSpecial) {
        setState(getState().chooseGamePiece(isSpecial));
    }

    public void undoMove(){
        setState(getState().undoMove());
    }

    public void chooseColumn(int column) {
        setState(getState().chooseColumn(column));
    }

    public void playMiniGame(boolean decision) {
        setState(getState().playMiniGame(decision));
    }

    public void answerTheWritingMiniGame(String answer){
        setState(getState().answerTheWritingMiniGame(answer));
    }

    public void answerTheMathMiniGame(int answer){
        setState(getState().answerTheMathMiniGame(answer));
    }

    public boolean loadReplay(int replayID) {
        File replayFile = new File("temp\\replays\\Replay"+replayID+".dat");
        if(!replayFile.exists()) {
            return false;
        }
        try {
            gameData = (GameData) FileUtility.loadGame("temp\\replays\\Replay"+replayID+".dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setState(getState().loadReplay(gameData));
        return true;
    }

    public void nextReplayMove(boolean isForward){
        setState(getState().nextReplayMove(isForward));
    }

    public boolean isAwaitingStart() {
        return (getState() instanceof AwaitsStart);
    }

    public boolean isAwaitingGameMode() {
        return (getState() instanceof AwaitsGameMode);
    }

    public boolean isAwaitingPlayerName() {
        return (getState() instanceof AwaitsPlayerName);
    }

    public boolean isAwaitingTheNextMove() {
        return (getState() instanceof AwaitsTheNextMove);
    }

    public boolean isAwaitingColumnChoice() {
        return (getState() instanceof AwaitsColumnChoice);
    }

    public boolean isAwaitingMiniGame() {
        return (getState() instanceof AwaitsMiniGame);
    }

    public boolean isAwaitingWritingMiniGame() {
        return (getState() instanceof AwaitsWritingMiniGame);
    }

    public boolean isAwaitingMathMiniGame() {
        return (getState() instanceof AwaitsMathMiniGame);
    }

    public boolean isAwaitingExit() {
        return (getState() instanceof AwaitsExit);
    }

    public boolean isAwaitingReplayID() {
        return (getState() instanceof AwaitsReplayID);
    }

    public boolean isAwaitingNextReplayMove() {
        return (getState() instanceof AwaitsNextReplayMove);
    }

    public int getCurrentPlayerID() {
        return getGameData().getCurrentPlayerID();
    }

    public boolean isCurrentPlayerHuman() {
        return getGameData().isCurrentPlayerHuman();
    }

    public String getCurrentPlayerName() {
        return getGameData().getCurrentPlayerName();
    }

    public boolean currentPlayerHasSpecialGamePieces() {
        return getGameData().currentPlayerHasSpecialGamePieces();
    }

    public boolean isCurrentPlayerReverseMoveOptionAvailable() {
        return getGameData().isCurrentPlayerReverseMoveOptionAvailable();
    }

    public int getCurrentPlayerNumberOfSpecialPieces() {
        return getGameData().getCurrentPlayerNumberOfSpecialPieces();
    }

    public int getCurrentPlayerNumberOfReverseMovesAvailable() {
        return getGameData().getCurrentPlayerNumberOfReverseMovesAvailable();
    }

    public int getCurrentPlayerNumberOfMovesAfterTheLastMiniGame() {
        return getGameData().getCurrentPlayerNumberOfMovesAfterTheLastMiniGame();
    }

    public boolean isCurrentPlayerPreviousMiniGameAboutMath() {
        return getGameData().isCurrentPlayerPreviousMiniGameAboutMath();
    }

    public String getGridString() {
        return getGameData().getGridString();
    }

    public boolean isCurrentGamePieceSpecial() {
        return getGameData().isCurrentGamePieceSpecial();
    }

    public String getCurrentGamePieceString() {
        return getGameData().getCurrentGamePieceString();
    }

    public String getCurrentPlayerNextMiniGameNameString() {
        return getGameData().getCurrentPlayerNextMiniGameNameString();
    }

    public int getMiniGameTimeLimit() {
        return getGameData().getMiniGameTimeLimit();
    }

    public int getNumberOfCorrectAnswers() {
        return getGameData().getNumberOfCorrectAnswers();
    }

    public String getMiniGameQuestionString() {
        return getGameData().getMiniGameQuestionString();
    }

    public String getFinalResultOfTheGameString() {
        return getGameData().getFinalResultOfTheGameString();
    }

    public int getMiniGameTimeLeft() {
        return getGameData().getMiniGameTimeLeft();
    }

    public int getCurrentMoveIndex() {
        return getGameData().getCurrentMoveIndex();
    }

    public int getTotalNumberOfMovesInTheGame() {
        return getGameData().getTotalNumberOfMovesInTheGame();
    }

    public String getPlayer1Name() {
        return getGameData().getPlayer1Name();
    }

    public String getPlayer2Name() {
        return getGameData().getPlayer2Name();
    }

    public String getPlayer1GamePieceString() {
        return getGameData().getPlayer1GamePieceString();
    }

    public String getPlayer2GamePieceString() {
        return getGameData().getPlayer2GamePieceString();
    }
}
