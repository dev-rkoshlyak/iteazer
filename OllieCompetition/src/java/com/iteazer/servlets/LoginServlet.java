package com.iteazer.servlets;

import com.iteazer.logic.Team;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        if (!Team.exists(login)) {
            response.sendRedirect("index.jsp");
            return;
        }

        Team team = Team.getTeam(login);
        if (team.matchesPassword(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            response.sendRedirect("profile.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}
