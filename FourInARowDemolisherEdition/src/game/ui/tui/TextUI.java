package game.ui.tui;

import game.logic.Game;
import game.logic.exceptions.InvalidTextUIOptionException;
import game.logic.files.FileUtility;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextUI {
    private Game game;
    private boolean exit = false;
    private Scanner sc;
    String chosenOptionString;

    public TextUI(Game game) {
        this.game = game;
    }

    public void run() {
        while (!exit) {
            if (game.isAwaitingStart()) {
                uiAwaitingStart();
            } else if (game.isAwaitingGameMode()) {
                uiAwaitingGameMode();
            } else if (game.isAwaitingPlayerName()) {
                uiAwaitingPlayerName();
            } else if (game.isAwaitingTheNextMove()) {
                uiAwaitingTheNextMove();
            } else if (game.isAwaitingColumnChoice()) {
                uiAwaitingColumnChoice();
            } else if (game.isAwaitingMiniGame()) {
                uiAwaitingMiniGame();
            } else if (game.isAwaitingWritingMiniGame()) {
                uiAwaitingWritingMiniGame();
            } else if (game.isAwaitingMathMiniGame()) {
                uiAwaitingMathMiniGame();
            } else if (game.isAwaitingExit()) {
                uiAwaitingExit();
            } else if (game.isAwaitingReplayID()) {
                uiAwaitingReplayID();
            } else if (game.isAwaitingNextReplayMove()) {
                uiAwaitingNextReplayMove();
            }
        }
    }

    private void uiAwaitingStart() {
        System.out.println("\n\n#########################################");
        System.out.println("#########################################");
        System.out.println("#### 4 IN A ROW - DEMOLISHER EDITION ####");
        System.out.println("#########################################");
        System.out.println("#########################################");
        System.out.println("#### L - Load Game ######################");
        System.out.println("#### N - New Game  ######################");
        System.out.println("#### R - Replays   ######################");
        System.out.println("#### Q - Quit      ######################");
        System.out.println("#########################################");
        System.out.println("#########################################");
        sc = new Scanner(System.in);
        while(!sc.hasNext("[LlNnRrQq]")){
            sc.next();
        }
        chosenOptionString = sc.next();
        switch(chosenOptionString) {
            case "L":
            case "l":
                System.out.print("Load: ");
                sc = new Scanner(System.in);
                chosenOptionString = sc.nextLine().toUpperCase().trim();
                if(chosenOptionString == null || chosenOptionString.length() < 1){
                    break;
                }
                try {
                    game = (Game) FileUtility.loadGame("temp\\saved\\"+chosenOptionString+".dat");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "N":
            case "n":
                game.start();
                break;
            case "R":
            case "r":
                game.watchReplay();
                break;
            case "Q":
            case "q":
                exit = true;
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingGameMode() {
        System.out.println("\n\n#### Please choose the Game Mode ####");
        System.out.println("0 - CPU vs CPU");
        System.out.println("1 - Human vs CPU");
        System.out.println("2 - Human vs Human");
        System.out.println("R - Return to Main Menu");
        sc = new Scanner(System.in);
        while(!sc.hasNext("[012Rr]")){
            sc.next();
        }
        chosenOptionString = sc.next();
        switch(chosenOptionString) {
            case "0":
                game.chooseGameMode(0);
                break;
            case "1":
                game.chooseGameMode(1);
                break;
            case "2":
                game.chooseGameMode(2);
                break;
            case "R":
            case "r":
                game.goBack();
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingPlayerName() {
        System.out.println("\n\n#### Player"+game.getCurrentPlayerID()+", please choose your name! ####");
        System.out.println("W - Write Name");
        System.out.println("D - Use Default Name");
        if(game.getCurrentPlayerID()==1){
            System.out.println("R - Return to Game Mode Selection");
        }else{
            System.out.println("R - Return back");
        }
        sc = new Scanner(System.in);
        while(!sc.hasNext("[WwDdRr]")){
            sc.next();
        }
        chosenOptionString = sc.next();
        switch(chosenOptionString) {
            case "W":
            case "w":
                System.out.print("Player" + game.getCurrentPlayerID() + " name: ");
                sc = new Scanner(System.in);
                chosenOptionString = sc.next();
                game.choosePlayerName(chosenOptionString);
                break;
            case "D":
            case "d":
                if(game.isCurrentPlayerHuman()) {
                    game.choosePlayerName("Player"+game.getCurrentPlayerID());
                }else{
                    game.choosePlayerName("CPU"+game.getCurrentPlayerID());
                }
                break;
            case "R":
            case "r":
                game.goBack();
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingTheNextMove() {
        System.out.println("\n\n**** Player Complete Information **** ID:"+game.getCurrentPlayerID()+ ", Name:"+game.getCurrentPlayerName()+", isHuman:"+game.isCurrentPlayerHuman()+", number of Special Game Pieces:"+game.getCurrentPlayerNumberOfSpecialPieces()+", number of Reverse Moves available:"+game.getCurrentPlayerNumberOfReverseMovesAvailable()+", number of moves after the last MiniGame:"+game.getCurrentPlayerNumberOfMovesAfterTheLastMiniGame()+", previous minigame was about math?:"+game.isCurrentPlayerPreviousMiniGameAboutMath());
        System.out.println("#### "+game.getCurrentPlayerName()+", please play a Game Piece! ####");
        System.out.println("N - Play a Normal Game Piece");
        if(game.currentPlayerHasSpecialGamePieces()) {
            System.out.println("G - Play a Special Game Piece");
        }
        if(game.isCurrentPlayerReverseMoveOptionAvailable()) {
            System.out.println("B - Reverse Move");
        }
        System.out.println("S - Save Game");
        System.out.println("R - Return to Main Menu");
        System.out.print(game.getGridString());
        sc = new Scanner(System.in);
        if((game.currentPlayerHasSpecialGamePieces()==true) && (game.isCurrentPlayerReverseMoveOptionAvailable()==true)){
            while(!sc.hasNext("[NnGgBbSsRr]")){
                sc.next();
            }
        }else if((game.currentPlayerHasSpecialGamePieces()==false) && (game.isCurrentPlayerReverseMoveOptionAvailable()==true)){
            while(!sc.hasNext("[NnBbSsRr]")){
                sc.next();
            }
        }else if((game.currentPlayerHasSpecialGamePieces()==true) && (game.isCurrentPlayerReverseMoveOptionAvailable()==false)){
            while(!sc.hasNext("[NnGgSsRr]")){
                sc.next();
            }
        }else{
            while(!sc.hasNext("[NnSsRr]")){
                sc.next();
            }
        }
        chosenOptionString = sc.next();
        switch (chosenOptionString){
            case "N":
            case "n":
                game.chooseGamePiece(false);
                break;
            case "G":
            case "g":
                game.chooseGamePiece(true);
                break;
            case "B":
            case "b":
                game.undoMove();
                break;
            case "S":
            case "s":
                System.out.print("Save As: ");
                sc = new Scanner(System.in);
                chosenOptionString = sc.nextLine().toUpperCase().trim();
                if(chosenOptionString == null || chosenOptionString.length() < 1){
                    break;
                }
                try {
                    FileUtility.saveGame("temp\\saved\\"+chosenOptionString+".dat", game);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "R":
            case "r":
                game.goBack();
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingColumnChoice() {
        System.out.println("\n\n**** Player Complete Information **** ID:"+game.getCurrentPlayerID()+ ", Name:"+game.getCurrentPlayerName()+", isHuman:"+game.isCurrentPlayerHuman()+", number of Special Game Pieces:"+game.getCurrentPlayerNumberOfSpecialPieces()+", number of Reverse Moves available:"+game.getCurrentPlayerNumberOfReverseMovesAvailable()+", number of moves after the last MiniGame:"+game.getCurrentPlayerNumberOfMovesAfterTheLastMiniGame()+", previous minigame was about math?:"+game.isCurrentPlayerPreviousMiniGameAboutMath());
        System.out.println("**** Game Piece Complete Information **** isSpecial:"+game.isCurrentGamePieceSpecial()+", GamePiece:"+game.getCurrentGamePieceString());
        System.out.println("#### "+game.getCurrentPlayerName()+", please choose a column! ####");
        if(game.isCurrentPlayerHuman()) {
            System.out.println("1 - Choose column 1");
            System.out.println("2 - Choose column 2");
            System.out.println("3 - Choose column 3");
            System.out.println("4 - Choose column 4");
            System.out.println("5 - Choose column 5");
            System.out.println("6 - Choose column 6");
            System.out.println("7 - Choose column 7");
        }else{
            System.out.println("M - Move Forward");
        }
        System.out.println("R - Return back");
        System.out.print(game.getGridString());
        sc = new Scanner(System.in);
        if(game.isCurrentPlayerHuman()) {
            while (!sc.hasNext("[1234567Rr]")) {
                sc.next();
            }
        }else{
            while (!sc.hasNext("[MmRr]")) {
                sc.next();
            }
        }
        chosenOptionString = sc.next();
        switch (chosenOptionString){
            case "1":
                game.chooseColumn(0);
                break;
            case "2":
                game.chooseColumn(1);
                break;
            case "3":
                game.chooseColumn(2);
                break;
            case "4":
                game.chooseColumn(3);
                break;
            case "5":
                game.chooseColumn(4);
                break;
            case "6":
                game.chooseColumn(5);
                break;
            case "7":
                game.chooseColumn(6);
                break;
            case "m":
            case "M":
                game.chooseColumn(7);
                break;
            case "R":
            case "r":
                game.goBack();
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingMiniGame() {
        System.out.println("\n\n**** Player Complete Information **** ID:"+game.getCurrentPlayerID()+ ", Name:"+game.getCurrentPlayerName()+", isHuman:"+game.isCurrentPlayerHuman()+", number of Special Game Pieces:"+game.getCurrentPlayerNumberOfSpecialPieces()+", number of Reverse Moves available:"+game.getCurrentPlayerNumberOfReverseMovesAvailable()+", number of moves after the last MiniGame:"+game.getCurrentPlayerNumberOfMovesAfterTheLastMiniGame()+", previous minigame was about math?:"+game.isCurrentPlayerPreviousMiniGameAboutMath());
        System.out.println("#### "+game.getCurrentPlayerName()+", would you like to play the "+game.getCurrentPlayerNextMiniGameNameString()+" MiniGame? ####");
        System.out.println("Y - Yes");
        System.out.println("N - No");
        sc = new Scanner(System.in);
        while(!sc.hasNext("[YyNn]")){
            sc.next();
        }
        chosenOptionString = sc.next();
        switch (chosenOptionString){
            case "Y":
            case "y":
                game.playMiniGame(true);
                break;
            case "N":
            case "n":
                game.playMiniGame(false);
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingWritingMiniGame() {
        System.out.println("\n\n**** Player Complete Information **** ID:"+game.getCurrentPlayerID()+ ", Name:"+game.getCurrentPlayerName()+", isHuman:"+game.isCurrentPlayerHuman()+", number of Special Game Pieces:"+game.getCurrentPlayerNumberOfSpecialPieces()+", number of Reverse Moves available:"+game.getCurrentPlayerNumberOfReverseMovesAvailable()+", number of moves after the last MiniGame:"+game.getCurrentPlayerNumberOfMovesAfterTheLastMiniGame()+", previous minigame was about math?:"+game.isCurrentPlayerPreviousMiniGameAboutMath());
        System.out.println("#### "+game.getCurrentPlayerName()+", please answer the Mini Game ####");
        System.out.println("!!!! You have to write the presented 5 words in less than "+game.getMiniGameTimeLimit()+" seconds. !!!!");
        System.out.println(game.getMiniGameQuestionString());
        sc = new Scanner(System.in);
        chosenOptionString = sc.nextLine();
        game.answerTheWritingMiniGame(chosenOptionString);
    }

    private void uiAwaitingMathMiniGame() {
        System.out.println("\n\n**** Player Complete Information **** ID:"+game.getCurrentPlayerID()+ ", Name:"+game.getCurrentPlayerName()+", isHuman:"+game.isCurrentPlayerHuman()+", number of Special Game Pieces:"+game.getCurrentPlayerNumberOfSpecialPieces()+", number of Reverse Moves available:"+game.getCurrentPlayerNumberOfReverseMovesAvailable()+", number of moves after the last MiniGame:"+game.getCurrentPlayerNumberOfMovesAfterTheLastMiniGame()+", previous minigame was about math?:"+game.isCurrentPlayerPreviousMiniGameAboutMath());
        System.out.println("#### "+game.getCurrentPlayerName()+", please answer the Mini Game ####");
        System.out.println("!!!! You have to make 5 correct answers in less than "+game.getMiniGameTimeLimit()+" seconds. !!!!");
        System.out.println("!!!! Progress:("+game.getNumberOfCorrectAnswers()+"/5), Time Left:"+game.getMiniGameTimeLeft()+" seconds. !!!!");
        System.out.print("What's "+game.getMiniGameQuestionString()+"=");
        sc = new Scanner(System.in);
        while(!sc.hasNextInt()){
            sc.nextLine();
        }
        int chosenOptionInt = sc.nextInt();
        game.answerTheMathMiniGame(chosenOptionInt);
    }

    private void uiAwaitingExit() {
        System.out.println("\n\n**** Player Complete Information **** ID:"+game.getCurrentPlayerID()+ ", Name:"+game.getCurrentPlayerName()+", isHuman:"+game.isCurrentPlayerHuman()+", number of Special Game Pieces:"+game.getCurrentPlayerNumberOfSpecialPieces()+", number of Reverse Moves available:"+game.getCurrentPlayerNumberOfReverseMovesAvailable()+", number of moves after the last MiniGame:"+game.getCurrentPlayerNumberOfMovesAfterTheLastMiniGame()+", previous minigame was about math?:"+game.isCurrentPlayerPreviousMiniGameAboutMath());
        System.out.println("#########################################");
        System.out.println("#### 4 IN A ROW - DEMOLISHER EDITION ####");
        System.out.println("#########################################");
        System.out.println("GAMEOVER:\n"+game.getFinalResultOfTheGameString());
        System.out.println("#########################################");
        System.out.print(game.getGridString());
        System.out.println("#########################################");
        System.out.println("R - Return to Main Menu");
        System.out.println("Q - Quit");
        sc = new Scanner(System.in);
        while(!sc.hasNext("[RrQq]")){
            sc.next();
        }
        chosenOptionString = sc.next();
        switch (chosenOptionString){
            case "R":
            case "r":
                game.goBack();
                break;
            case "Q":
            case "q":
                exit=true;
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingReplayID() {
        System.out.println("\n\n#### Please choose the Replay ####");
        System.out.println("1 - Replay 1");
        System.out.println("2 - Replay 2");
        System.out.println("3 - Replay 3");
        System.out.println("4 - Replay 4");
        System.out.println("5 - Replay 5");
        System.out.println("R - Return to Main Menu");
        sc = new Scanner(System.in);
        while(!sc.hasNext("[12345Rr]")){
            sc.next();
        }
        chosenOptionString = sc.next();
        switch(chosenOptionString) {
            case "1":
                game.loadReplay(1);
                break;
            case "2":
                game.loadReplay(2);
                break;
            case "3":
                game.loadReplay(3);
                break;
            case "4":
                game.loadReplay(4);
                break;
            case "5":
                game.loadReplay(5);
                break;
            case "R":
            case "r":
                game.goBack();
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    private void uiAwaitingNextReplayMove() {
        System.out.println("\n\n#########################################");
        System.out.println("#### 4 IN A ROW - DEMOLISHER EDITION ####");
        System.out.println("################ Replay #################");
        System.out.println("Move ["+game.getCurrentMoveIndex()+"] out of ["+game.getTotalNumberOfMovesInTheGame()+"]");
        System.out.println("#########################################");
        System.out.println("Player1: "+game.getPlayer1Name()+", GamePiece:"+game.getPlayer1GamePieceString());
        System.out.println("Player2: "+game.getPlayer2Name()+", GamePiece:"+game.getPlayer2GamePieceString());
        System.out.println("#########################################");
        System.out.print(game.getGridString());
        System.out.println("#########################################");
        if(game.getCurrentMoveIndex()<game.getTotalNumberOfMovesInTheGame()){
            System.out.println("N - Next Move");
        }
        if(game.getCurrentMoveIndex()>0){
            System.out.println("P - Previous Move");
        }
        System.out.println("R - Return to Replay Menu");
        sc = new Scanner(System.in);
        if((game.getCurrentMoveIndex()>=game.getTotalNumberOfMovesInTheGame())&&(game.getCurrentMoveIndex()>0)){
            while (!sc.hasNext("[pPRr]")) {
                sc.next();
            }
        }else if((game.getCurrentMoveIndex()<game.getTotalNumberOfMovesInTheGame())&&(game.getCurrentMoveIndex()==0)){
            while (!sc.hasNext("[nNRr]")) {
                sc.next();
            }
        }else if((game.getCurrentMoveIndex()>=game.getTotalNumberOfMovesInTheGame())&&(game.getCurrentMoveIndex()==0)){
            while (!sc.hasNext("[Rr]")) {
                sc.next();
            }
        }else{
            while (!sc.hasNext("[nNpPRr]")) {
                sc.next();
            }
        }
        chosenOptionString = sc.next();
        switch (chosenOptionString){
            case "P":
            case "p":
                game.nextReplayMove(false);
                break;
            case "N":
            case "n":
                game.nextReplayMove(true);
                break;
            case "R":
            case "r":
                game.goBack();
                break;
            default: {
                try {
                    throw new InvalidTextUIOptionException();
                } catch (InvalidTextUIOptionException e) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
