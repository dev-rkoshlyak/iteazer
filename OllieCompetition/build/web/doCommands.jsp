<%-- 
    Document   : doCommands
    Created on : Oct 18, 2016, 10:18:29 AM
    Author     : Wsl_F@ITeazer
--%>

<%@page import="com.iteazer.logic.Team"%>
<%-- 
<%@page isThreadSafe="true" %>
--%>
<%
    Team team = Team.getTeam((String) session.getAttribute("login"));
    int executionTime = team.doSubmition(request.getParameter("textCommands"));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Commands successfully submitted</h1>
        <h2>Your time: <%= executionTime%></h2>
    </body>
</html>
