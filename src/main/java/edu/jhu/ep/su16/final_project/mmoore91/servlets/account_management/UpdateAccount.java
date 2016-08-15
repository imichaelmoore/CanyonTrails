package edu.jhu.ep.su16.final_project.mmoore91.servlets.account_management;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;
import edu.jhu.ep.su16.final_project.mmoore91.pojo.User;
import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class UpdateAccount extends HttpServlet {
    private Connection conn;

    private SQLAdapter db;

    Logger logger = LoggerFactory.getLogger(UpdateAccount.class);


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



        // SessionBean Bean
        HttpSession session = req.getSession();
        SessionBean s = null;
        if (session.getAttribute("sessionBean") == null) {
            s = new SessionBean();
        } else {
            s = (SessionBean) session.getAttribute("sessionBean");
        }

        User u = new User(s.getAuthenticatedUserUID());
        u.updateName(req.getParameter("name"));
        u.setEmailAddress(req.getParameter("email"));

        if(req.getParameter("password") != "") {
            u.setPassword(req.getParameter("password"));
        }


        s.setAuthenticatedUserName(u.getName());
        s.setAuthenticatedUserEmailAddress(u.getEmailAddress());
        s.setAuthenticatedUserUID(u.getUid());

        s.setSuccessMessage("Information successfully updated.");

        session.setAttribute("sessionBean", s);

        resp.sendRedirect(req.getContextPath() + "/account.jsp");
    }
}
