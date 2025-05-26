package game.logic.data.players;

import game.logic.data.IConstantVariables;

public class Player implements IPlayer, IConstantVariables {
    String name;
    final boolean human;
    int specialGamePieces;
    int numberOfReverseMovesAvailable;
    int numberOfMovesAfterTheLastMiniGameChance;
    boolean previousMiniGameAboutMath;

    public Player(boolean human) {
        this.human = human;
        specialGamePieces = 0;
        resetTheNumberOfPlayerMovesAfterTheLastMiniGame();
        setInitialMiniGame();
    }

    private void setInitialMiniGame() {
        int initialMiniGame = (int) (Math.random()*(2));
        if(initialMiniGame==0){
            setPreviousMiniGameAboutMath(true);
        }else{
            setPreviousMiniGameAboutMath(false);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isHuman() {
        return human;
    }

    public int getSpecialGamePieces() {
        return specialGamePieces;
    }

    public int getNumberOfReverseMovesAvailable() {
        return numberOfReverseMovesAvailable;
    }

    public int getNumberOfMovesAfterTheLastMiniGameChance() {
        return numberOfMovesAfterTheLastMiniGameChance;
    }

    public void setPreviousMiniGameAboutMath(boolean previousMiniGameAboutMath) {
        this.previousMiniGameAboutMath = previousMiniGameAboutMath;
    }

    public boolean isPreviousMiniGameAboutMath() {
        return previousMiniGameAboutMath;
    }


    @Override
    public void incrementTheNumberOfPlayerMovesAfterTheLastMiniGame() {
        this.numberOfMovesAfterTheLastMiniGameChance=numberOfMovesAfterTheLastMiniGameChance+1;
    }

    @Override
    public void resetTheNumberOfPlayerMovesAfterTheLastMiniGame() {
        this.numberOfMovesAfterTheLastMiniGameChance=0;
    }

    @Override
    public void incrementTheNumberOfSpecialGamePieces() {
        this.specialGamePieces=specialGamePieces+1;
    }

    @Override
    public void removeOneSpecialGamePiece() {
        this.specialGamePieces=specialGamePieces-1;
    }

    @Override
    public void removeOneReverseMoveAvailable() {
        this.numberOfReverseMovesAvailable=numberOfReverseMovesAvailable-1;
    }
}
