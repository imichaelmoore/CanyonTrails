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
import java.util.HashMap;


public class RecentUpdates extends HttpServlet {

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
        ArrayList<HashMap<String, String>> results = db.sqlQuery("select ROW_NUMBER() OVER() as rn, " +
                "users.name as user_name,\n" +
                "mtime, trails.name as trail_name, update_text, trail_id from timeline tl\n" +
                "JOIN users ON tl.OWNER_UID = users.uid\n" +
                "JOIN trails on tl.TRAIL_ID = trails.ID\n" +
                "ORDER BY mtime DESC\n" +
                "FETCH FIRST 10 ROWS ONLY");

        gson.toJson(results);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(results));
        out.flush();

    }
}
