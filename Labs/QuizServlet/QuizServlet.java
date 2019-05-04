/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.quiz;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vieta
 */
public class QuizServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String htmlForm
                = "<html>"
                + "<head>"
                + "<title>Quiz Servlet Lab</title>"
                + "<meta charset=\"UTF-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "</head>"
                + "<body>"
                + "<div>"
                + "<fieldset>"
                + "<h1>The Number Quiz</h1>"
                + "<form action=\"/Servlets/QuizServlet\" method=\"post\">"
                + "<p>Your current score is $score$.</p>"
                + "<p>Guess the next number in the sequence.</p>"
                + "<p>$question$</p>"
                + "<p>Your answer: <input type=\"text\" size=\"5\" name=\"answer\"/></p>"
                + "<p><input type=\"submit\" value=\"Submit\"/></p>"
                + "</form>"
                + "</fieldset>"
                + "</div>"
                + "</body>"
                + "</html>";

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Quiz quiz = new Quiz();
            request.getSession().setAttribute("quiz", quiz);
            htmlForm = htmlForm.replace("$score$", String.valueOf(quiz.score()));
            htmlForm = htmlForm.replace("$question$", quiz.getCurrentQuestion());
            out.println(htmlForm);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String htmlForm
                    = "<html>"
                    + "<head>"
                    + "<title>Quiz Servlet Lab</title>"
                    + "<meta charset=\"UTF-8\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                    + "</head>"
                    + "<body>"
                    + "<div>"
                    + "<fieldset>"
                    + "<h1>The Number Quiz</h1>"
                    + "<form action=\"/Servlets/QuizServlet\" method=\"post\">"
                    + "<p>Your current score is $score$.</p>"
                    + "<p>Guess the next number in the sequence.</p>"
                    + "<p>$question$</p>"
                    + "<p>Your answer: <input type=\"text\" size=\"5\" name=\"answer\"/></p>"
                    + "<p><input type=\"submit\" value=\"Submit\"/></p>"
                    + "</form>"
                    + "</fieldset>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            String resultForm
                    = "<html>"
                    + "<head>"
                    + "<title>Quiz Servlet Lab</title>"
                    + "<meta charset=\"UTF-8\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                    + "</head>"
                    + "<body>"
                    + "<div>"
                    + "<fieldset>"
                    + "<h1>The Number Quiz</h1>"
                    + "<p>Your current score is $score$.</p>"
                    + "<p>You have completed the Number Quiz, with a score of $score$ out of 5.</p>"
                    + "</fieldset>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            Quiz quiz;
            if (request.getSession().getAttribute("quiz") != null) {
                quiz = (Quiz) request.getSession().getAttribute("quiz");
            } else {
                quiz = new Quiz();
            }

            // get submitted answer for current questtion
            int answer = Integer.parseInt(request.getParameter("answer"));

            // answer the question.
            quiz.answer(answer);

            // moving next question
            quiz.moveNextQuestion();

            // check if the final question then display the result
            if (quiz.finalQuestion()) {
                resultForm = resultForm.replace("$score$", String.valueOf(quiz.score()));
                out.println(resultForm);
            } else {
                // prepare html form and send back to client.                
                htmlForm = htmlForm.replace("$score$", String.valueOf(quiz.score()));
                htmlForm = htmlForm.replace("$question$", quiz.getCurrentQuestion());
                out.print(htmlForm);
            }

            // saving quiz to the context                   
            request.getSession().setAttribute("quiz", quiz);

        }

        request.getRequestDispatcher("/Servlets/QuizServlet").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Quiz Servlet";
    }// </editor-fold>

}
