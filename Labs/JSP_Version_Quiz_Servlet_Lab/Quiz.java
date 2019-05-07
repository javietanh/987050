/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.quiz.mvc;

/**
 *
 * @author vieta
 */
public class Quiz {

    /**
     * initialized database
     */
    private static final String[] QUESTIONS = {
        "3, 1, 4, 1, 5",
        "1, 1, 2, 3, 5",
        "1, 4, 9, 16, 25",
        "2, 3, 5, 7, 11",
        "1, 2, 4, 8, 16"
    };
    private static final int[] ANSWERS = {9, 8, 36, 13, 32};

    /**
     * initial quiz variables
     */
    private int correctAnswer = 0;
    private int currentQuestion = 0;
    private int tryCount = 0;
    private int score = 0;
    private int tips = -1;

    public Quiz() {
        tryCount = 0;
    }

    public String getCurrentQuestion() {
        return QUESTIONS[currentQuestion];
    }
    
    public int getCurrentQuestionPosition(){
        return currentQuestion + 1;
    }

    public void moveNextQuestion() {
        if(!finalQuestion()){
            currentQuestion++;
            tips = -1;
            tryCount = 0;
        }            
    }

    public boolean finalQuestion() {
        return currentQuestion > QUESTIONS.length - 1;
    }

    public void answer(int value) {
        int answer = ANSWERS[currentQuestion];
        if (answer == value) {
            switch (tryCount) {
                case 0:
                    correctAnswer++;                    
                    score += 10;
                    moveNextQuestion();
                    break;
                case 1:
                    correctAnswer++;                    
                    score += 5;
                    moveNextQuestion();
                    break;
                case 2:
                    correctAnswer++;                    
                    score += 2;
                    moveNextQuestion();
                    break;
                default:
                    // over 3 times try, give the user the answer and move to next question.                    
                    tips = answer;
                    // reset couter.
                    tryCount = 0;
                    break;
            }
        } else {
            if (tryCount >= 2) {
                // give the user the answer tips.
                tips = answer;
                // reset couter.
                tryCount = 0;
            }else {
                // else just increase the counter
                tryCount++;
            }            
        }
    }    

    public int getTotalCorrectAnswer(){
        return correctAnswer;
    }
    
    public int getScoreNumber() {
        return score;
    }
    
    public int getTips(){
        return tips;
    }
    
    public int getTryTimes(){
        return tryCount;
    }
    
    public String getScoreLetter(){
        if(score <= 50 && score >= 45){
            return "A";
        }else if(score < 45 && score > 35){
            return "B";
        }else if(score < 35 && score > 25){
            return "C";
        }else{
            return "NC";
        }
    }    
}
