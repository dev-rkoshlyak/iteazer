package com.iteazer.servlets;

import com.iteazer.logic.Contest;
import com.iteazer.logic.Team;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wslf
 */
public class FinalSubmissionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String teamName = request.getParameter("teams");
        if (teamName != null) {
            Team team = Team.getTeam(teamName);
            team.doFinalSubmition(Contest.getCurrentSubmission(teamName), Contest.getCurrentRound());
            Contest.removeCurrentSubmission(teamName);
        }

        response.sendRedirect("admin.jsp");
    }

}
