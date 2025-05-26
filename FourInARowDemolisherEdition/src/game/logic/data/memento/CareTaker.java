package game.logic.data.memento;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class CareTaker implements Serializable {
    private final IMementoOriginator originator;

    private Deque<Memento> stackHist = new ArrayDeque<>();
    private Deque<Memento> stackRedo = new ArrayDeque<>();

    public CareTaker(IMementoOriginator org) {
        this.originator = org;
    }

    public void saveMemento() {
        stackRedo.clear();
        try {
            stackHist.push(originator.getMemento());
        } catch (IOException e) {
            stackHist.clear();
            stackRedo.clear();
            e.printStackTrace();
        }
    }

    public boolean undo() {
        if (stackHist.isEmpty()) {
            return false;
        }
        try {
            Memento current = originator.getMemento();
            stackRedo.push(current);
            Memento previous = stackHist.pop();
            originator.setMemento(previous);
        } catch (IOException | ClassNotFoundException e) {
            stackHist.clear();
            stackRedo.clear();
            e.printStackTrace();
        }
        return true;
    }

    public boolean redo() {
        if (stackRedo.isEmpty()) {
            return false;
        }

        try {
            Memento sredo = stackRedo.pop();
            stackHist.push(originator.getMemento());
            originator.setMemento(sredo);
        } catch (IOException | ClassNotFoundException e) {
            stackHist.clear();
            stackRedo.clear();
            e.printStackTrace();
        }
        return true;
    }

    public int getCurrentMoveIndex(){
        return stackHist.size();
    }

    public int getTotalNumberOfMovesInTheGame(){
        return stackHist.size()+stackRedo.size();
    }
}
