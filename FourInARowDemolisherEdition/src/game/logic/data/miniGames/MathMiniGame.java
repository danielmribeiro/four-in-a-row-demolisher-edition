package game.logic.data.miniGames;

public class MathMiniGame extends MiniGame{
    String operator;
    int a,b;
    int result;

    public MathMiniGame() {
        super(MATH_MINIGAME);
        setTimeLimit();
    }

    @Override
    public void generateANewQuestion() {
        operator = setOperator();
        a = getRandomNumber();
        b = getRandomNumber();
        questionString = a + operator + b;
        if (operator.equals("+")){
            result=a+b;
        }else if(operator.equals("*")){
            result=a*b;
        }else if(operator.equals("/")) {
            result=a/b;
        }else{
            result=a-b;
        }
    }

    private int getRandomNumber() {
        int min=1,max=99;
        return (int) (Math.random()*(max-min+1))+min; //[min,...,max]
    }

    private String setOperator() {
        int index=(int)(Math.random()*(4)); //[0,...,3]
        if(index==0){
            return "/";
        }else if(index==1){
            return "+";
        }else if(index==2){
            return "*";
        }else{
            return "-";
        }
    }

    @Override
    public void setTimeLimit() {
        timeLimit=30;
    }

    @Override
    public <T> void playMiniGame(T answer) {
        int x = (int)answer;
        if(x==getResult()){
            correctAnswers=correctAnswers+1;
        }
    }

    private int getResult() {
        return result;
    }
}
