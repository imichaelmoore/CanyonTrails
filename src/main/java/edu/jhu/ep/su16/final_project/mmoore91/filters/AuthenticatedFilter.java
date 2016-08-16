package edu.jhu.ep.su16.final_project.mmoore91.filters;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * This filter checks the session bean to see if a user is currently logged in.
 * If not, it redirects to a login_required page.
 */

public class AuthenticatedFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        try {

            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse res = (HttpServletResponse) servletResponse;

            HttpSession session = req.getSession();
            SessionBean s = null;
            if (session.getAttribute("sessionBean") == null) {
                s = new SessionBean();
            } else {
                s = (SessionBean) session.getAttribute("sessionBean");
            }
            s.setErrorMessage("You must login to see this page.");

            if (s.isAuthenticated() == false) res.sendRedirect(req.getContextPath() + "/login_required.jsp");

            chain.doFilter(req, res);

        } catch (Exception f) {
            f.printStackTrace();
        }

    }
}