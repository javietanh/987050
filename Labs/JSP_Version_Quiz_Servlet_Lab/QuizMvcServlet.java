/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.quiz.mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vieta
 */
@WebServlet(
        name = "QuizMvcServlet",
        urlPatterns = "/Quiz.jsp")
public class QuizMvcServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        // get quiz object save in session.        
        HttpSession s = request.getSession();
        Quiz quiz = s.getAttribute("quiz") != null ? 
                (Quiz) s.getAttribute("quiz") : new Quiz();
        
        // reset validator.
        if (request.getAttribute("error") != null) {
            request.removeAttribute("error");
        }
        
        // validate the input and age
        if (request.getParameter("age") == null || request.getParameter("age").equals("")) {
            request.setAttribute("error", "Please input your age!");
            return;
        }
        
        // validate age is valid?
        try{            
            int age = Integer.parseInt(request.getParameter("age"));
            if(age > 100 || age < 0){
                request.setAttribute("error", "Age value is invalid!");
                return;
            }            
        }catch(NumberFormatException e){
            request.setAttribute("error", "Age value is invalid!");
            return;
        }
        
        // move next question
        if(request.getParameter("next") != null){
            quiz.moveNextQuestion();
            // saving quiz to session
            s.setAttribute("quiz", quiz);
            return;
        }
        
        // validate answer.
        if (request.getParameter("answer") == null || request.getParameter("answer").equals("")) {
            request.setAttribute("error", "Please input your answer!");
            return;
        }
        
        // validate answer is valid?
        int answer = Integer.MIN_VALUE;
        try{
            answer = Integer.parseInt(request.getParameter("answer"));            
        }catch(NumberFormatException e){
            answer = Integer.MIN_VALUE;
            request.setAttribute("error", "Answer is invalid!");
            return;
        }        

        // only process if the answer is valid.
        if(answer != Integer.MIN_VALUE){
            // provide the answer
            quiz.answer(answer);                        
        }
        
        // saving quiz to session
        s.setAttribute("quiz", quiz);
    }

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

        // initial new quiz and dispatch to view        
        Quiz quiz = request.getSession().getAttribute("quiz") != null
                ? (Quiz) request.getSession().getAttribute("quiz") : new Quiz();

        // saving quiz to session
        request.getSession().setAttribute("quiz", quiz);

        // dispatching result to client.
        request.getRequestDispatcher("Quiz.jsp").forward(request, response);
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
        
        // process submit process
        processRequest(request, response);
        
        // redirect back to quiz.jsp
        //response.sendRedirect("Quiz.jsp");
        request.getRequestDispatcher("Quiz.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
