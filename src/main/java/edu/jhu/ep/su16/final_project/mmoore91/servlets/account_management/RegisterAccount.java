package edu.jhu.ep.su16.final_project.mmoore91.servlets.account_management;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;
import edu.jhu.ep.su16.final_project.mmoore91.pojo.User;
import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by moorema1 on 8/6/16.
 */
public class RegisterAccount extends HttpServlet {
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
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
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

        SQLAdapter db = new SQLAdapter();
        ArrayList<HashMap<String, String>> r = db.sqlQuery("SELECT * FROM users WHERE email = ?", Arrays.asList(req.getParameter("email")));
        if(r.size() > 0)
        {
            s.setErrorMessage("A user with that email address already exists.");
            sendToURL += "/register.jsp";
        } else {

            User u = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"));

            s.setAuthenticated(true);
            s.setAuthenticatedUserName(u.getName());
            s.setAuthenticatedUserEmailAddress(u.getEmailAddress());
            s.setAuthenticatedUserUID(u.getUid());

        }
        session.setAttribute("sessionBean", s);
        resp.sendRedirect(sendToURL);
    }
}
