package edu.jhu.ep.su16.final_project.mmoore91.servlets.account_management;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;
import edu.jhu.ep.su16.final_project.mmoore91.pojo.User;
import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;

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
public class Logout extends HttpServlet {
    private Connection conn;

    private SQLAdapter db;

    /*
     * Method executes when the servlet is created.
          * Accesses the DataSource object and creates the Connection object.
          */
    @Override
    public void init()
            throws ServletException {

        db = new SQLAdapter();

    } // init()
    /*
          * Method to handle a Http Get request.
          * The Connection object created in init() is used to access the
          * database data using JDBC API.
     */
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

        s.setAuthenticated(false);
        s.setAuthenticatedUserName(null);
        s.setAuthenticatedUserEmailAddress(null);
        s.setAuthenticatedUserUID(null);

        session.setAttribute("sessionBean", s);

        resp.sendRedirect(req.getContextPath());
    }
}
