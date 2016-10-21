package com.iteazer.servlets;

import com.iteazer.logic.Contest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class setSbmStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("updateSBM") != null) {
            int status;
            try {
                status = Integer.parseInt(request.getParameter("sbmStatus"));
            } catch (NumberFormatException ex) {
                status = -1;
            }

            Contest.setFinalSubmitStatus(status);
        }
        response.sendRedirect("admin.jsp");
    }

}
