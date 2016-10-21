<%-- 
    Document   : login
    Created on : Oct 18, 2016, 1:14:45 PM
    Author     : Wsl_F@ITeazer
--%>
<%
    try {
        session.removeAttribute("login");
    } catch (Exception ex) {
    }
    String teamName = request.getParameter("login");
    session.setAttribute("login", teamName);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <%
        String redirectURL = "profile.jsp";
        response.sendRedirect(redirectURL);
    %>
</html>
