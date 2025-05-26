package game.logic.data.gameBoards;

import game.logic.data.IConstantVariables;
import game.logic.data.gamePieces.GamePiece;
import game.logic.data.memento.IMementoOriginator;
import game.logic.data.memento.Memento;

import java.io.IOException;

public class GameBoard implements IGameBoard, IConstantVariables, IMementoOriginator {
    private static final int rows=6;
    private static final int columns=7;
    GamePiece [][] gameboard;
    private int winnerID;

    public GameBoard() {
        gameboard = new GamePiece[rows][columns];
        resetGameBoard();
    }

    private void resetGameBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameboard[i][j]=null;
            }
        }
    }


    @Override
    public boolean addNormalGamePiece(GamePiece gamePiece, int column) {
        if(column==7){
            playInARandomColumn(gamePiece);
            return true;
        }
        if(!columnIsFull(column)){
            for (int i = rows-1; i>-1; i--) {
                if(cellIsEmpty(i,column)){
                    gameboard[i][column]=gamePiece;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addSpecialGamePiece(int column) {
        if(column<columns) {
            for (int i = rows - 1; i > -1; i--) {
                gameboard[i][column] = null;
            }
            return true;
        }
        return false;
    }

    private boolean playInARandomColumn(GamePiece gamePiece) {
        int column;
        do{
            column=(int) (Math.random()*(7));
        }while(columnIsFull(column));
        for (int i = rows-1; i>-1; i--) {
            if(cellIsEmpty(i,column)){
                gameboard[i][column]=gamePiece;
                return true;
            }
        }
        return false;
    }

    public boolean columnIsFull(int column) {
        return gameboard[0][column] != null;
    }

    private boolean cellIsEmpty(int row, int column) {
        return gameboard[row][column] == null;
    }

    public boolean isTheGameFinished(){
        if(someoneWonTheGame())
            return true;
        if(noMoreSpaceToPlay())
            return true;
        return false;
    }

    public boolean someoneWonTheGame() {
        if(verticalWin()){
            return true;
        }else if(horizontalWin()){
            return true;
        }else if(diagonalWin()){
            return true;
        }
        return false;
    }

    private boolean verticalWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(i+3<rows) {
                    if (gameboard[i][j]!=null && gameboard[i + 1][j]!=null && gameboard[i + 2][j]!=null && gameboard[i + 3][j]!=null) {
                        if (gameboard[i][j].getPlayerID() == gameboard[i + 1][j].getPlayerID() && gameboard[i + 1][j].getPlayerID() == gameboard[i + 2][j].getPlayerID() && gameboard[i + 2][j].getPlayerID() == gameboard[i + 3][j].getPlayerID()) {
                            setWinnerID(gameboard[i][j].getPlayerID());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean horizontalWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j + 3 < columns) {
                    if (gameboard[i][j]!=null && gameboard[i][j+1]!=null && gameboard[i][j+2]!=null && gameboard[i][j+3]!=null) {
                        if (gameboard[i][j].getPlayerID() == gameboard[i][j + 1].getPlayerID() && gameboard[i][j + 1].getPlayerID() == gameboard[i][j + 2].getPlayerID() && gameboard[i][j + 2].getPlayerID() == gameboard[i][j + 3].getPlayerID()) {
                            setWinnerID(gameboard[i][j].getPlayerID());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean diagonalWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(j+3<columns && i+3<rows) {
                    if (gameboard[i][j]!=null && gameboard[i + 1][j+1]!=null && gameboard[i + 2][j+2]!=null && gameboard[i + 3][j+3]!=null) {
                        if (gameboard[i][j].getPlayerID() == gameboard[i + 1][j + 1].getPlayerID() && gameboard[i + 1][j + 1].getPlayerID() == gameboard[i + 2][j + 2].getPlayerID() && gameboard[i + 2][j + 2].getPlayerID() == gameboard[i + 3][j + 3].getPlayerID()) {
                            setWinnerID(gameboard[i][j].getPlayerID());
                            return true;
                        }
                    }
                }
                if(j-3>=0 && i+3<rows) {
                    if (gameboard[i][j]!=null && gameboard[i + 1][j-1]!=null && gameboard[i + 2][j-2]!=null && gameboard[i + 3][j-3]!=null) {
                        if (gameboard[i][j].getPlayerID() == gameboard[i + 1][j - 1].getPlayerID() && gameboard[i + 1][j - 1].getPlayerID() == gameboard[i + 2][j - 2].getPlayerID() && gameboard[i + 2][j - 2].getPlayerID() == gameboard[i + 3][j - 3].getPlayerID()) {
                            setWinnerID(gameboard[i][j].getPlayerID());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean noMoreSpaceToPlay() {
        int columnsFilled=0;
        for (int i = 0; i < columns; i++) {
            if(gameboard[0][i]!=null)
                columnsFilled++;
        }
        return columnsFilled == columns;
    }

    private void setWinnerID(int playerID) {
        winnerID=playerID;
    }

    public int getWinnerID() {
        return winnerID;
    }

    @Override
    public Memento getMemento() throws IOException {
        Memento m = new Memento(gameboard);
        return m;
    }

    @Override
    public void setMemento(Memento m) throws IOException, ClassNotFoundException {
        gameboard = (GamePiece[][]) m.getSnapshot();
    }

    public String getGridString() {
        StringBuffer grid = new StringBuffer();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(gameboard[i][j] == null){
                    grid.append(NULL_GAMEPIECE);
                }else if(gameboard[i][j].getPlayerID()==1){
                    grid.append(PLAYER1_GAMEPIECE);
                }else if(gameboard[i][j].getPlayerID()==2) {
                    grid.append(PLAYER2_GAMEPIECE);
                }
            }
            grid.append("\n");
        }
        return grid.toString();
    }
}
