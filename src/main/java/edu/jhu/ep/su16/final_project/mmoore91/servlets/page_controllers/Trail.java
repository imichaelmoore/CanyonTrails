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

/**
 * Created by moorema1 on 8/6/16.
 */
public class Trail extends HttpServlet {
    private SQLAdapter db;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String sendToURL = req.getContextPath();

        // SessionBean Bean
        HttpSession session = req.getSession();
        SessionBean s = null;
        if (session.getAttribute("sessionBean") == null) {
            s = new SessionBean();
        } else {
            s = (SessionBean) session.getAttribute("sessionBean");
        }

//        String trail_id = req.getPathInfo().substring(1);
//
//        req.setAttribute("trail_id", trail_id);
//
//        SQLAdapter db = new SQLAdapter();
//        ArrayList<HashMap<String, String>> r = db.sqlQuery("SELECT id, owner_uid, name, description FROM trails WHERE id = ?", Arrays.asList(req.getParameter("trail_id")));

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/trail_detail.jsp");
        dispatcher.forward(req, resp);
    }
}
