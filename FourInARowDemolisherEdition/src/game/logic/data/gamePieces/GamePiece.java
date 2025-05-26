package game.logic.data.gamePieces;

import game.logic.data.IConstantVariables;

public class GamePiece implements IGamePiece, IConstantVariables {
    int playerID;
    boolean special;

    public GamePiece(int playerID, boolean special) {
        this.playerID = playerID;
        this.special = special;
    }

    public int getPlayerID() {
        return playerID;
    }

    public boolean isSpecial() {
        return special;
    }
}
