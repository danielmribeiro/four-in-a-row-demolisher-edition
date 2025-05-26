package game.logic.data.miniGames;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WritingMiniGame extends MiniGame{
    List<String> wordsList;

    public WritingMiniGame() {
        super(WRITING_MINIGAME);
        wordsList=readWordsRepository("src\\game\\logic\\files\\WritingGameFile\\WordsRepository.txt");
    }

    private static List<String> readWordsRepository(String fileName) {
        List<String> wordsList = new LinkedList<>();
        File wordsFile = new File(fileName);
        String word;
        Scanner sc = null;
        try {
            sc = new Scanner(wordsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            assert sc != null;
            if (!sc.hasNextLine()) break;
            word=sc.nextLine();
            if(word.length()>=5) {
                wordsList.add(word);
            }
        }
        sc.close();
        return wordsList;
    }

    @Override
    public void generateANewQuestion() {
        int wordsSelected=0, indexOfWord;
        StringBuffer buildQuestion= new StringBuffer();
        while(wordsSelected<5) {
            indexOfWord = getRandomIndexOfWord(wordsList.size());
            buildQuestion.append(wordsList.get(indexOfWord));
            wordsSelected++;
            if(wordsSelected!=5){
                buildQuestion.append(" ");
            }
        }
        questionString=buildQuestion.toString();
        setTimeLimit();
    }

    private int getRandomIndexOfWord(int size) {
        return (int) (Math.random()*(size));
    }


    @Override
    public void setTimeLimit() {
        timeLimit=questionString.length()/2;
    }

    @Override
    public <T> void playMiniGame(T answer) {
        String x = (String) answer;
        if(x.equals(getQuestionString())){
            correctAnswers=correctAnswers+1;
        }
    }
}
