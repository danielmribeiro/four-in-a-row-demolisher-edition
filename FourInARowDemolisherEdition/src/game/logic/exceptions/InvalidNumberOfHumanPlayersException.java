package game.logic.exceptions;

public class InvalidNumberOfHumanPlayersException extends Exception{
    public InvalidNumberOfHumanPlayersException(){
        super("##### InvalidNumberOfHumanPlayersException ##### - The number of human players is impossible.");
    }
}
