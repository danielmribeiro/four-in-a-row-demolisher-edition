package game.logic;

import game.logic.data.GameData;
import game.logic.files.FileUtility;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

import static game.logic.Constants.PROPERTY_NAME_FOUR_IN_A_ROW;

public class GameObservable {
    private Game game;
    private final PropertyChangeSupport propertyChangeSupport;

    public GameObservable(Game game) {
        this.game = game;
        propertyChangeSupport = new PropertyChangeSupport(game);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public Situation getCurrentSituation(){
        return game.getCurrentSituation();
    }

    public void start() {
        game.start();
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void watchReplay() {
        game.watchReplay();
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void chooseGameMode(int numberOfHumanPlayers) {
        game.chooseGameMode(numberOfHumanPlayers);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void goBack() {
        game.goBack();
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void choosePlayerName(String playerName) {
        game.choosePlayerName(playerName);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void chooseGamePiece(boolean isSpecial) {
        game.chooseGamePiece(isSpecial);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void undoMove(){
        game.undoMove();
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void chooseColumn(int column) {
        game.chooseColumn(column);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void playMiniGame(boolean decision) {
        game.playMiniGame(decision);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void answerTheWritingMiniGame(String answer){
        game.answerTheWritingMiniGame(answer);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void answerTheMathMiniGame(int answer){
        game.answerTheMathMiniGame(answer);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void loadReplay(int replayID) {
        game.loadReplay(replayID);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public void nextReplayMove(boolean isForward){
        game.nextReplayMove(isForward);
        propertyChangeSupport.firePropertyChange(PROPERTY_NAME_FOUR_IN_A_ROW, null, null);
    }

    public int getCurrentPlayerID() {
        return game.getCurrentPlayerID();
    }

    public boolean isCurrentPlayerHuman() {
        return game.isCurrentPlayerHuman();
    }

    public String getCurrentPlayerName() {
        return game.getCurrentPlayerName();
    }

    public boolean save(File filename)  {
        try {
            FileUtility.saveGame(filename, getGame());
            return true;
        }catch (IOException e) {
            System.err.println("Error saving");
            return false;
        }
    }

    public boolean loadGame(File filename) {
        try {
            Game game = (Game) FileUtility.loadGame(filename);
            if (game != null) {
                setGame(game);
            }
        }catch (IOException | ClassNotFoundException e) {
            System.err.println("ERRO ao ler");
            return false;
        }
        return true;
    }
}