package game.logic.exceptions;

public class InvalidColumnChoiceException extends Exception{
    public InvalidColumnChoiceException(){
        super("##### InvalidColumnChoiceException ##### - There's a problem in the column selection impossible to solve.");
    }
}
