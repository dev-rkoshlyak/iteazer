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
public class DeserializedResultsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("path") != null) {
            String path = request.getParameter("path");
            Contest.deserializedResultr(path);
        }
        response.sendRedirect("admin.jsp");

    }

}
