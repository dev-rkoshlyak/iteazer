<%-- 
    Document   : index
    Created on : Oct 17, 2016, 4:28:04 PM
    Author     : Wsl_F@ITeazer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Ollie competition</title>
    </head>
    <body>
        <h1>Please login</h1>
        <form name="loginForm" action="${pageContext.request.contextPath}/LoginServlet" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td>Team: </td>
                        <td><input type="text" name="login" value="" size="20" /></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="password" value="" size="20" /></td>
                    </tr>
                </tbody>
            </table>

            <input type="submit" value="Login" name="login" />
        </form>
    </body>
</html>
