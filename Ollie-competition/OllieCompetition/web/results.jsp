<%-- 
    Document   : results
    Created on : Nov 6, 2016, 1:47:03 AM
    Author     : Wsl_F@ITeazer
--%>

<%@page import="com.iteazer.logic.Results"%>
<%@page import="com.iteazer.logic.Contest"%>
<%@page import="java.util.List"%>
<%@page import="com.iteazer.logic.Team"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
        <h1>Contest results</h1>
        
        <% 
            List<Team> teams = Team.getAllTeams();
            Results results = Contest.getResults();
        %>
        <table border="7">
            <thead>
                <tr>
                    <th>Team</th>
                    <th>Round 1</th>
                    <th>Round 2</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%for (Team team : teams) {%>
                <tr>
                    <td><%= team.getName()%></td>
                    <td><%= results.getCurrentResult(team, 0)%></td>
                    <td><%= results.getCurrentResult(team, 1)%></td>
                    <td><%= results.getCurrentResult(team, 0) + results.getCurrentResult(team, 1)%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
