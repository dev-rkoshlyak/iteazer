<%-- 
    Document   : performCommandsFinal
    Created on : Oct 18, 2016, 12:41:51 PM
    Author     : Wsl_F@ITeazer
--%>

<%

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perform commands</title>
    </head>
    <body>
        <h1><%=session.getAttribute("login")%>: perform commands</h1>
        <h2><a href="profile.jsp"> Your profile </a></h2>
        
        <form name="commands" action="doCommandsFinal.jsp" method="POST" target="_blank">
            <textarea name="textCommands" rows="25" cols="20"></textarea>
            <br>
            <input type="submit" value="Official submit" name="perform"/>
            <br>
            <input type="reset" value="Clear" name="clear" />
        </form>
    </body>
</html>
