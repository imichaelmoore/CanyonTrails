package edu.jhu.ep.su16.final_project.mmoore91.servlets.account_management;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;
import edu.jhu.ep.su16.final_project.mmoore91.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * This servlet receives a update account form post, processes it, and redirects appropriately.
 */


public class UpdateAccount extends HttpServlet {


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

        if (req.getParameter("password") != "") {
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
