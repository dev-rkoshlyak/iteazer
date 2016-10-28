<%-- 
    Document   : doCommandsFinal
    Created on : Oct 18, 2016, 10:18:29 AM
    Author     : Wsl_F@ITeazer
--%>

<%@page import="com.iteazer.logic.Contest"%>
<%@page import="com.iteazer.logic.Team"%>
<%
    Team team = Team.getTeam((String) session.getAttribute("login"));
    int roundN = Contest.finalSubmit();
    int executionTime = team.doFinalSubmition(request.getParameter("textCommands"), roundN);
    Contest.serializeResults();
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
