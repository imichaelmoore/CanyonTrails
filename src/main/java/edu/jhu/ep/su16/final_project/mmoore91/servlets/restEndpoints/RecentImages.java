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
import java.util.HashSet;

/*
 * This servlet returns a JSON file containing a list of recent images in the system
 */

public class RecentImages extends HttpServlet {

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

        // I kind-of don't like Derby.  I miss more extended SQL dialects.
        ArrayList<HashMap<String, String>> results = db.sqlQuery("select ROW_NUMBER() OVER() as rn, " +
                "images.id as image_id, images.trail_id, images.owner_uid, trails.name as trail_name," +
                " users.name as user_name FROM images JOIN trails ON trails.id = images.trail_id " +
                "JOIN users ON users.uid = trails.owner_uid ORDER BY uploaded DESC FETCH FIRST 50 ROWS ONLY");

        HashSet<String> foundProjects = new HashSet<>();
        ArrayList<HashMap<String, String>> cleanResults = new ArrayList<>();

        for (HashMap<String, String> row : results) {
            if (!foundProjects.contains(row.get("trail_id"))) {
                cleanResults.add(row);
                foundProjects.add(row.get("trail_id"));
            }
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(cleanResults));
        out.flush();

    }
}
