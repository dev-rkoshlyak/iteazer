<%-- 
    Document   : calibrate
    Created on : Oct 19, 2016, 1:58:45 PM
    Author     : Wsl_F@ITeazer
--%>

<%@page import="com.iteazer.logic.Team"%>
<%
    String teamName = (String) session.getAttribute("login");
    Team team = Team.getTeam(teamName);
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= team.getName() %> calibrating Ollie</h1>
        <h2><a href="profile.jsp"> Your profile </a></h2>
        
        <form name="calibrateForm" action="${pageContext.request.contextPath}/CalibrateOllieServlet" method="POST">
            <input type="text" name="textShift" value=<%=team.getCurrentShift()%> size="3" />
            <input type="submit" value="update" name="Update" />
        </form>
    </body>
</html>
