package edu.jhu.ep.su16.final_project.mmoore91.servlets.page_controllers;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;
import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by moorema1 on 8/6/16.
 */
public class MyTrails extends HttpServlet {
    private Connection conn;

    private SQLAdapter db;

    @Override
    public void init() throws ServletException {
        db = new SQLAdapter();

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // SessionBean Bean
        HttpSession session = req.getSession();
        SessionBean s = null;
        if (session.getAttribute("sessionBean") == null) {
            s = new SessionBean();
        } else {
            s = (SessionBean) session.getAttribute("sessionBean");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/trails.jsp");
        dispatcher.forward(req, resp);

    }
}
