package edu.jhu.ep.su16.final_project.mmoore91.servlets.page_controllers;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * This servlet receives the /trail/add GET request and displays the proper JSP.
 * This is mainly for making the URL structure more clear.
 */

public class AddTrail extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addtrail.jsp");
        dispatcher.forward(req, resp);

    }
}
