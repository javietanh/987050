<%@page import="mum.cs472.quiz.mvc.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Quiz quiz = (Quiz)session.getAttribute("quiz"); 
    
    
    String count = "";
    String answer = "";
    if(quiz.getTryTimes() > 0){
        count = "(" + quiz.getTryTimes() + " out of 3 times)";
    }
    if(quiz.getTips() != -1){
        answer = "(Out of trying, your correct answer is "+ quiz.getTips() +")";
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Number Quiz</title>
        <link href="Quiz.css" rel="stylesheet" type="text/css"/>
    </head>
    <body> 
        <% if(quiz.finalQuestion()){ %>
            <fieldset>
                <h1>The Number Quiz!</h1>
                <p>Your current score is <span class="error">${quiz.getScoreLetter()}</span>.</p>                
                <p>You have completed the Number Quiz, with a score of ${quiz.getScoreNumber()} out of 50.</p>
            </fieldset>
        <% } else { %>
            <form method="post" action="/Servlets/Quiz">
                <fieldset>                    
                    <div class="age">
                        <label>Your age: <input type="text" name="age" value="<%= request.getParameter("age") != null ? (String)request.getParameter("age") : "" %>" size="5" maxlength="2"/></label>
                    </div>
                    <h1>The Number Quiz!</h1>
                    <p>Your current score is ${quiz.getScoreNumber()}.</p>
                    <p>Question ${quiz.getCurrentQuestionPosition()}: Guess the next number in the sequence.</p>
                    <p>${quiz.getCurrentQuestion()}</p>
                    <% if(quiz.getTips() == -1) { %>                    
                        <p>Your answer: <input type="text" size="5" name="answer"/> <span class="try"><%= count %></span></p> 
                        <p><input class="button" type="submit" value="Submit" name="submit"/></p>
                    <% } else { %>
                        <span class="try"><%= answer %></span>
                        <p><input class="button" type="submit" value="Next Question" name="next"/></p>
                    <% } %>
                    
                    <p class="error"><%= request.getAttribute("error") != null? (String)request.getAttribute("error") : "" %></p>                                
                </fieldset>
            </form>
        <%}%>
    </body>
</html>
