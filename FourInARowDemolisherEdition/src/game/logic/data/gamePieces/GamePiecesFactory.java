package game.logic.data.gamePieces;

public class GamePiecesFactory {
    public static GamePiece createGamePiece(boolean isSpecial, int playerID){
        if(isSpecial){
            return new SpecialGamePiece(playerID);
        }else{
            return new NormalGamePiece(playerID);
        }
    }
}
