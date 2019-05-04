/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.quiz;

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

    public Quiz() {

    }

    public String getCurrentQuestion() {
        return QUESTIONS[currentQuestion];
    }

    public void moveNextQuestion() {
        currentQuestion++;
    }

    public boolean finalQuestion() {
        return currentQuestion >= QUESTIONS.length - 1;
    }

    public void answer(int value) {
        if (ANSWERS[currentQuestion] == value) {
            correctAnswer++;
        }
    }

    public int score() {
        return correctAnswer;
    }
}
