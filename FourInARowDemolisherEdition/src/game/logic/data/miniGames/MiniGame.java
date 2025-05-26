package game.logic.data.miniGames;

import game.logic.data.IConstantVariables;

public abstract class MiniGame implements IMiniGame, IConstantVariables {
    String name;
    int correctAnswers;
    long startMS,endMS,elapsedTimeS;
    String questionString;
    int timeLimit;

    public MiniGame(String name) {
        this.name = name;
        elapsedTimeS=0;
        correctAnswers=0;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public long getElapsedTimeS() {
        return elapsedTimeS;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public String getQuestionString() {
        return questionString;
    }

    public boolean isCountdownFinished(){
        return getElapsedTimeS()>=getTimeLimit();
    }

    public void startCountingTheTime() {
        startMS=System.currentTimeMillis();
    }

    public void stopCountingTheTime() {
        endMS=System.currentTimeMillis();
        elapsedTimeS=elapsedTimeS+((endMS-startMS)/1000);
    }
}
