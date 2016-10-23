package com.iteazer.servlets;

import com.iteazer.logic.Contest;
import com.iteazer.logic.Results;
import com.iteazer.logic.Team;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class updSolutionStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("updStBtn") != null) {
            Results results = Contest.getResults();

            for (int roundN = 0; roundN < com.iteazer.logic.Constants.NUMBER_OF_ROUNDS; roundN++) {
                for (int attempN = 0; attempN < com.iteazer.logic.Constants.MAX_NUMBER_OF_ATTEMPTS; attempN++) {
                    ArrayList<Team> teams = results.getTeams(roundN, attempN);
                    for (Team team : teams) {
                        String cellName = team.getName() + "_" + roundN + "_" + attempN + "_status";
                        if (request.getParameter(cellName) != null) {
                            try {
                                int status = Integer.valueOf(request.getParameter(cellName));
                                results.setTeamStatus(team, roundN, attempN, status);
                            } catch (NumberFormatException ex) {
                                System.err.println("Text field " + cellName + " doesn't contain number");
                            }
                        }
                    }
                }
            }

        }
        response.sendRedirect("admin.jsp");
    }

}
