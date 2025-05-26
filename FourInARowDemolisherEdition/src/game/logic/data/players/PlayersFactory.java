package game.logic.data.players;

public class PlayersFactory {
    public static Player createPlayer(boolean isHuman){
        if(isHuman){
            return new HumanPlayer();
        }else{
            return new CPUPlayer();
        }
    }
}
