<%-- 
    Document   : adminDoFinalSubmissions
    Created on : Nov 5, 2016, 11:15:21 PM
    Author     : Wsl_F@ITeazer.com
--%>

<%@page import="java.util.List"%>
<%@page import="com.iteazer.logic.Contest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Do final submission</h1>
        <%
            List<String> submitedTeams = Contest.getTeamsThatSubmited();
            int number = submitedTeams.size();
        %>
        
        <form name="doFinalSubmissions"action="${pageContext.request.contextPath}/FinalSubmissionServlet" method="POST">
            <select name="teams" width="50" size=<%=number%>>
                <%for (String team : submitedTeams) {%>
                <option value=<%=team%>><%=team%></option>
                <%}%>
            </select>
            <br>
            <br>
             <input type="submit" value="Perform commands" name="sbm" />
        </form>
    </body>
</html>
