package edu.jhu.ep.su16.final_project.mmoore91.servlets.restEndpoints;

import com.google.gson.Gson;
import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;
import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * This servlet returns a JSON file containing a list of trails for the current user.
 */

public class MyTrails extends HttpServlet {

    private SQLAdapter db;

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

        Gson gson = new Gson();
        db = new SQLAdapter();
        ArrayList<HashMap<String, String>> results = db.sqlQuery("select name as traiL_name, " +
                "id as trail_id from trails where owner_uid = ?", Arrays.asList(s.getAuthenticatedUserUID()));

        gson.toJson(results);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(results));
        out.flush();

    }
}
