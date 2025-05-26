package game.logic.exceptions;

public class InvalidNumberOfCurrentPlayerIDException extends Exception{
    public InvalidNumberOfCurrentPlayerIDException(){
        super("##### InvalidNumberOfCurrentPlayerIDException ##### - The number of the current player ID is impossible.");
    }
}
