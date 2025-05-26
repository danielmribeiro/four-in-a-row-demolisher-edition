package game.logic.data.players;

public interface IPlayer {
    void incrementTheNumberOfPlayerMovesAfterTheLastMiniGame();
    void resetTheNumberOfPlayerMovesAfterTheLastMiniGame();
    void incrementTheNumberOfSpecialGamePieces();
    void removeOneSpecialGamePiece();
    void removeOneReverseMoveAvailable();
}
