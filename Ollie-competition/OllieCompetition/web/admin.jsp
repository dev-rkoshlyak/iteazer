<%-- 
    Document   : admin
    Created on : Oct 18, 2016, 10:58:37 AM
    Author     : Wsl_F@ITeazer
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.iteazer.logic.Team"%>
<%@page import="com.iteazer.logic.Results"%>
<%@page import="com.iteazer.logic.Contest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <h1>Admin mode</h1>
        <h2><a href="adminDoFinalSubmissions.jsp"> Do final submission </a></h2>
        
        <form name="submitionStatus" action="${pageContext.request.contextPath}/SetSbmStatusServlet" method="POST">
            <h2>
                Current status: 
                <input type="text" name="sbmStatus" value="<%=Contest.finalSubmit()%>" size="10" /> 
            </h2>
            <input type="submit" value="Update" name="updateSBM" />
        </form>

        <form name="submissions"action="${pageContext.request.contextPath}/UpdSolutionStatusServlet" method="POST">
            <%
                Results results = Contest.getResults();

                for (int i = 0; i < com.iteazer.logic.Constants.NUMBER_OF_ROUNDS; i++) {%>
            <h2>Round <%= i + 1%></h2>
            <% for (int j = 0; j < com.iteazer.logic.Constants.MAX_NUMBER_OF_ATTEMPTS; j++) {%>
            <h3>Attempt <%= j + 1%></h3>
            <% String tableName = "attemptsR" + i + "_" + j;
                ArrayList<Team> teams = results.getTeams(i, j);
            %>
            <table name=<%=tableName%> border="4" cellpadding="7">
                <tr>
                    <th>Team</th>
                    <th>Time</th>
                    <th>Status</th>
                </tr>
                <%for (Team team : teams) {
                    int status = results.getTeamStatus(team, i, j);
                    String color = "#FFFFFF";
                    if (status == 0) { 
                        color = "#FF0000";
                    } else {
                        color = status == 1 ? "#00FF00" : "#FFFF00";
                    }
                %>
                <tr bgcolor= <%=color%> >
                    <td> <%= team.getName()%> </td>
                    <td> <%= results.getTime(team, i, j)%> </td>
                    <% String cellName = team.getName() + "_" + i + "_" + j + "_status"; %>
                    <td> <input type="text" name=<%=cellName%> value=<%= status %> size="1" /></td>
                </tr>
                <%}%>
            </table>
            <%}%>
            <%}%>
            <input type="submit" value="Update statues" name="updStBtn" />
            
            <br>
            <br>
            <br>
            
        </form>
            
            <form name="restoreResults" action="${pageContext.request.contextPath}/DeserializedResultsServlet" method="POST">
                <input type="text" name="path" value="" size="100" />
                <input type="submit" value="Load" name="bLoad" />
            </form>    
    </body>
</html>
