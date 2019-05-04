<%-- 
    Document   : index
    Created on : May 4, 2019, 9:45:52 AM
    Author     : vieta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Improved Calculator</title>
    </head>
    <body>
        <div>
            <form method="post" action="/Servlets/ImprovedCalculator">
                <fieldset>
                    <legend>Calculator</legend>
                    <p>
                        <input type="text" name="tbNum1" value="<%= request.getAttribute("num1") != null ? request.getAttribute("num1") : "" %>" size="5"/> + <input type="text" name="tbNum2" size="5" value="<%= request.getAttribute("num2") != null ? request.getAttribute("num2") : "" %>"/> = <input type="text" name="tbResult1" size="5" value="<%= request.getAttribute("result1") != null? request.getAttribute("result1") : ""%>"/>
                    </p>
                    <p>
                        <input type="text" name="tbNum3" value="<%= request.getAttribute("num3") != null ? request.getAttribute("num3") : "" %>" size="5"/> * <input type="text" name="tbNum4" size="5" value="<%= request.getAttribute("num4") != null ? request.getAttribute("num4") : "" %>"/> = <input type="text" name="tbResult2" size="5" value="<%= request.getAttribute("result2") != null ? request.getAttribute("result2") : "" %>"/>
                    </p>
                    <p>
                        <input type="submit" value="Submit"/>
                    </p>                
                </fieldset>                
            </form>
        </div>
    </body>
</html>
