<%-- 
    Document   : profile
    Created on : Oct 17, 2016, 4:47:17 PM
    Author     : Wsl_F@ITeazer
--%>

<%@page import="com.iteazer.logic.*"%>
<%
    String teamName = (String) session.getAttribute("login");
    Team team = Team.getTeam(teamName);
    Results results = Contest.getResults();
    int timeRound1 = results.getTime(team, 0);
    int timeRound2 = results.getTime(team, 1);
    double scoreRound1 = results.getCurrentResult(team, 0);
    double scoreRound2 = results.getCurrentResult(team, 1);
    int attemptsLeft1 = results.AttemptLeft(team, 0);
    int attemptsLeft2 = results.AttemptLeft(team, 1);
    //String webRes = OllieManager.sendHTTPRequest("http://127.0.0.1:1337/ollie/isConnected?MAC=dc712fb5b631");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= teamName%> profile </title>
    </head>
    <body>
        <h1><%= teamName%> profile</h1>
        <h3><a href="https://docs.google.com/document/d/10C-auIyuGlNLr78_k3pfYUG2wl_GueQHOzbGm7HS4lU/edit">Rules</a></h3>
        <h3><a href="https://github.com/ITeazer/Ollie_contest">GitHub</a></h3>
        <%--        <h5>Webres: <%=webRes%> </h5> ---%>
        <h2> Your results: </h2>
        <table border="5">
            <thead>
                <tr>
                    <th>Round</th>
                    <th>Score</th>
                    <th>Time</th>
                    <th>Attempts left</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Round 1</td>
                    <td><%= scoreRound1%></td>
                    <td><%= timeRound1%></td>
                    <td><%=attemptsLeft1%></td>
                </tr>
                <tr>
                    <td>Round 2</td>
                    <td><%= scoreRound2%></td>
                    <td><%= timeRound2%></td>
                    <td><%=attemptsLeft2%></td>
                </tr>
            </tbody>
        </table>

        <br>
        <br>
        <form name="doCommands" action="performCommands.jsp" method="POST">
            <input type="submit" value="PerformCommands" name="pCmd" />
        </form>

        <br>
        <br>
        <form name="doCommandsFinal" action="performCommandsFinal.jsp" method="POST">
            <% if (Contest.finalSubmit() >= 0) {%>
            <input type="submit" value="Final submition" name="pCmd" />
            <%}%>
        </form>

        <br>
        <br>
        <form name="calibrateB" action="calibrate.jsp" method="POST" target="_blank">
            <input type="submit" value="Calibrate" name="startCalibr" />
        </form>
    </body>
</html>
