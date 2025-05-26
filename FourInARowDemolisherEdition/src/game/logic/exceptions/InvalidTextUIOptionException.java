package game.logic.exceptions;

public class InvalidTextUIOptionException extends Exception{
    public InvalidTextUIOptionException(){
        super("##### InvalidTextUIOptionException ##### - The chosen option in the TextUI is incorrect.");
    }
}
