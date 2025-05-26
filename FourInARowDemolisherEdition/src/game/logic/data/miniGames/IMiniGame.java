package game.logic.data.miniGames;

public interface IMiniGame {
    void generateANewQuestion();
    void setTimeLimit();
    <T> void playMiniGame(T answer);
}
