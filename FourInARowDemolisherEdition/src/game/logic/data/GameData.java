package game.logic.data;

import game.logic.data.gameBoards.GameBoard;
import game.logic.data.gameBoards.GameBoardsFactory;
import game.logic.data.gamePieces.GamePiece;
import game.logic.data.gamePieces.GamePiecesFactory;
import game.logic.data.memento.CareTaker;
import game.logic.data.miniGames.MiniGame;
import game.logic.data.miniGames.MiniGamesFactory;
import game.logic.data.players.Player;
import game.logic.data.players.PlayersFactory;
import game.logic.exceptions.InvalidNumberOfCurrentPlayerIDException;
import game.logic.files.FileUtility;

public class GameData implements IConstantVariables {
    Player player1;
    Player player2;
    int currentPlayerID;
    GamePiece currentGamePiece;
    GameBoard gameboard;
    MiniGame minigame;
    private CareTaker careTaker;

    public GameData(){
    }

    public void resetGame() {
        currentPlayerID=1;
    }

    public boolean setGameMode(int numberOfHumanPlayers) {
        if(numberOfHumanPlayers==0){
            player1 = PlayersFactory.createPlayer(false);
            player2 = PlayersFactory.createPlayer(false);
            return true;
        } else if(numberOfHumanPlayers==1){
            player1 = PlayersFactory.createPlayer(true);
            player2 = PlayersFactory.createPlayer(false);
            return true;
        } else if (numberOfHumanPlayers==2){
            player1 = PlayersFactory.createPlayer(true);
            player2 = PlayersFactory.createPlayer(true);
            return true;
        }
        return false;
    }

    public void setCurrentPlayerID() {
        int playerID = (int) ((Math.random()*2)+1);
        setCurrentPlayerID(playerID);
    }

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public boolean swapCurrentPlayer() {
        try {
            if (getCurrentPlayerID() == 1) {
                setCurrentPlayerID(2);
                return true;
            } else if (getCurrentPlayerID() == 2) {
                setCurrentPlayerID(1);
                return true;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

        public boolean setCurrentPlayerName(String playerName) {
        try{
            if(getCurrentPlayerID()==1){
                player1.setName(playerName);
                return true;
            }else if(getCurrentPlayerID()==2){
                player2.setName(playerName);
                return true;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getCurrentPlayerName() {
        try{
            if(getCurrentPlayerID()==1){
                return player1.getName();
            }else if(getCurrentPlayerID()==2){
                return player2.getName();
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean thePlayerNameIsNotBeingUsed(String playerName) {
        if(playerName.equals(player1.getName())){
            return false;
        }else if(playerName.equals(player2.getName())){
            return false;
        }
        return true;
    }

    public boolean isCurrentPlayerHuman() {
        try{
            if(getCurrentPlayerID()==1){
                return player1.isHuman();
            }else if(getCurrentPlayerID()==2){
                return player2.isHuman();
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean currentPlayerHasSpecialGamePieces() {
        try{
            if(getCurrentPlayerID()==1){
                return player1.getSpecialGamePieces()>0;
            }else if(getCurrentPlayerID()==2){
                return player2.getSpecialGamePieces()>0;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isCurrentPlayerReverseMoveOptionAvailable() {
        try{
            if(getCurrentPlayerID()==1){
                return player1.getNumberOfReverseMovesAvailable()>0;
            }else if(getCurrentPlayerID()==2){
                return player2.getNumberOfReverseMovesAvailable()>0;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCurrentPlayerNumberOfSpecialPieces() {
        try{
            if (getCurrentPlayerID()==1){
                return player1.getSpecialGamePieces();
            }else if(getCurrentPlayerID()==2) {
                return player2.getSpecialGamePieces();
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCurrentPlayerNumberOfReverseMovesAvailable() {
        try{
            if (getCurrentPlayerID()==1){
                return player1.getNumberOfReverseMovesAvailable();
            }else if(getCurrentPlayerID()==2) {
                return player2.getNumberOfReverseMovesAvailable();
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCurrentPlayerNumberOfMovesAfterTheLastMiniGame() {
        try{
            if (getCurrentPlayerID() == 1){
                return player1.getNumberOfMovesAfterTheLastMiniGameChance();
            }else if(getCurrentPlayerID()==2) {
                return player2.getNumberOfMovesAfterTheLastMiniGameChance();
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isCurrentPlayerPreviousMiniGameAboutMath() {
        try{
            if (getCurrentPlayerID()==1){
                return player1.isPreviousMiniGameAboutMath();
            }else if(getCurrentPlayerID()==2) {
                return player2.isPreviousMiniGameAboutMath();
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createGameBoard() {
        gameboard = GameBoardsFactory.createGameBoard();
        careTaker = new CareTaker(gameboard);
    }


    public String getGridString() {
        return gameboard.getGridString();
    }

    public void playGamePiece(boolean isSpecial) {
        currentGamePiece = GamePiecesFactory.createGamePiece(isSpecial, getCurrentPlayerID());
    }

    public void saveMemento(){
        careTaker.saveMemento();
    }

    public boolean removeOneReverseMoveAvailableForTheCurrentPlayer() {
        try{
            if (getCurrentPlayerID()==1){
                player1.removeOneReverseMoveAvailable();
                return true;
            }else if(getCurrentPlayerID()==2) {
                player2.removeOneReverseMoveAvailable();
                return true;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean restartCountingCurrentPlayerMovesAfterMiniGame() {
        try{
            if (getCurrentPlayerID()==1){
                player1.resetTheNumberOfPlayerMovesAfterTheLastMiniGame();
                return true;
            }else if(getCurrentPlayerID()==2) {
                player2.resetTheNumberOfPlayerMovesAfterTheLastMiniGame();
                return true;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isCurrentGamePieceSpecial() {
        return currentGamePiece.isSpecial();
    }

    public String getCurrentGamePieceString() {
        try{
            if(currentGamePiece.getPlayerID()==1){
                return getPlayer1GamePieceString();
            }else if(currentGamePiece.getPlayerID()==2){
                return getPlayer2GamePieceString();
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean undo() {
        return careTaker.undo();
    }

    public boolean redo() {
        return careTaker.redo();
    }

    public boolean chooseColumn(int column) {
        if(currentGamePiece.isSpecial()){
            if(getCurrentPlayerID()==1) {
                player1.removeOneSpecialGamePiece();
            }else if(getCurrentPlayerID()==2){
                player2.removeOneSpecialGamePiece();
            }
            gameboard.addSpecialGamePiece(column);
        }else{
            gameboard.addNormalGamePiece(currentGamePiece, column);
        }
        return true;
    }

    public boolean columnIsFull(int column) {
        return gameboard.columnIsFull(column);
    }

    public boolean incrementTheNumberOfSpecialGamePieces() {
        try{
            if (getCurrentPlayerID() == 1){
                player1.incrementTheNumberOfSpecialGamePieces();
                return true;
            }else if(getCurrentPlayerID()==2) {
                player2.incrementTheNumberOfSpecialGamePieces();
                return true;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean incrementTheNumberOfPlayerMoves() {
        try{
            if (getCurrentPlayerID() == 1){
                player1.incrementTheNumberOfPlayerMovesAfterTheLastMiniGame();
                return true;
            }else if(getCurrentPlayerID()==2) {
                player2.incrementTheNumberOfPlayerMovesAfterTheLastMiniGame();
                return true;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTheGameFinished() {
        return gameboard.isTheGameFinished();
    }

    public boolean isTimeToPlayTheMiniGame() {
        if(isCurrentPlayerHuman()){
            if(getCurrentPlayerID()==1) {
                return player1.getNumberOfMovesAfterTheLastMiniGameChance() == 4;
            }else if(getCurrentPlayerID()==2){
                return player2.getNumberOfMovesAfterTheLastMiniGameChance() == 4;
            }
        }
        return false;
    }

    public String getCurrentPlayerNextMiniGameNameString() {
        try{
            if (getCurrentPlayerID()==1){
                if(player1.isPreviousMiniGameAboutMath()){
                    return WRITING_MINIGAME;
                }else{
                    return MATH_MINIGAME;
                }
            }else if(getCurrentPlayerID()==2) {
                if(player1.isPreviousMiniGameAboutMath()){
                    return WRITING_MINIGAME;
                }else{
                    return MATH_MINIGAME;
                }
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createMiniGame(boolean previousMiniGameWasAboutMath) {
        minigame = MiniGamesFactory.createMiniGame(previousMiniGameWasAboutMath);
    }

    public void generateANewQuestion() {
        minigame.generateANewQuestion();
    }

    public int getMiniGameTimeLimit() {
        return minigame.getTimeLimit();
    }

    public int getNumberOfCorrectAnswers() {
        return minigame.getCorrectAnswers();
    }

    public String getMiniGameQuestionString() {
        return minigame.getQuestionString();
    }

    public String getFinalResultOfTheGameString() {
        StringBuffer result = new StringBuffer();
        if(gameboard.isTheGameFinished()){
            if(gameboard.someoneWonTheGame()){
                result.append("Congratulations ");
                if(gameboard.getWinnerID()==1){
                    result.append(player1.getName());
                }else{
                    result.append(player2.getName());
                }
                result.append(". You won the Game!");
            }else{
                result.append("It's a tie!");
            }
        }
        return result.toString();
    }

    public boolean isTheMathMiniGameFinished() {
        return minigame.getCorrectAnswers() >= 5 || minigame.isCountdownFinished();
    }

    public boolean isTheWritingMiniGameCompletedCorrectly() {
        return minigame.getCorrectAnswers()==1 && !minigame.isCountdownFinished();
    }

    public boolean changeCurrentPlayerNextMiniGame(boolean previousMiniGameWasAboutMath) {
        try{
            if (getCurrentPlayerID() == 1){
                player1.setPreviousMiniGameAboutMath(previousMiniGameWasAboutMath);
                return true;
            }else if(getCurrentPlayerID()==2) {
                player2.setPreviousMiniGameAboutMath(previousMiniGameWasAboutMath);
                return true;
            }
            throw new InvalidNumberOfCurrentPlayerIDException();
        } catch (InvalidNumberOfCurrentPlayerIDException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void playMathMiniGame(int answer) {
        minigame.playMiniGame(answer);
    }

    public void playWritingMiniGame(String answer) {
        minigame.playMiniGame(answer);
    }

    public void startCountingTheTime() {
        minigame.startCountingTheTime();
    }

    public void stopCountingTheTime() {
        minigame.stopCountingTheTime();
    }

    public int getMiniGameTimeLeft() {
        return (int) (minigame.getTimeLimit()-minigame.getElapsedTimeS());
    }

    public void saveReplay(GameData gameData) {
        FileUtility.saveReplay(gameData);
    }

    public int getCurrentMoveIndex() {
        return careTaker.getCurrentMoveIndex();
    }

    public int getTotalNumberOfMovesInTheGame() {
        return careTaker.getTotalNumberOfMovesInTheGame();
    }

    public String getPlayer1Name() {
        return player1.getName();
    }

    public String getPlayer2Name() {
        return player2.getName();
    }

    public String getPlayer1GamePieceString() {
        return PLAYER1_GAMEPIECE;
    }

    public String getPlayer2GamePieceString() {
        return PLAYER2_GAMEPIECE;
    }

    public void resetReplay() {
        while(careTaker.undo()){};
    }
}
