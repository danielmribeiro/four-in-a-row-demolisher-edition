package game.logic.data.miniGames;

public class MiniGamesFactory {
    public static MiniGame createMiniGame(boolean previousMiniGameWasAboutMath) {
        if(previousMiniGameWasAboutMath){
            return new WritingMiniGame();
        }else{
            return new MathMiniGame();
        }
    }
}
