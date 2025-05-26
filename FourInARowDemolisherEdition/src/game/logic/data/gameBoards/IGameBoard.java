package game.logic.data.gameBoards;

import game.logic.data.gamePieces.GamePiece;

public interface IGameBoard {
    boolean addNormalGamePiece(GamePiece gamePiece, int column);
    boolean addSpecialGamePiece(int column);
}
