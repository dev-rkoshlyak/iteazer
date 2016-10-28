package com.iteazer.servlets;

import com.iteazer.logic.Team;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class CalibrateOllieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("Update") != null) {
            int shift;
            try {
                shift = Integer.parseInt(request.getParameter("textShift"));
            } catch (NumberFormatException ex) {
                shift = 0;
                System.err.println("Wrong shift value");
            }

            String teamName = (String) request.getSession().getAttribute("login");
            Team team = Team.getTeam(teamName);
            team.calibrateOllie(shift);

        }
        response.sendRedirect("calibrate.jsp");
    }

}
