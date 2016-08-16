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
 * Depending on the file extension (.json or .gpx) this servlet returns a JSON file containing information about the
 * current trail, or the .gpx file containing the GPS track for the current trail respectively.
 */

public class TrailInfo extends HttpServlet {

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

        String requestFileName = req.getPathInfo().substring(1); // Cuts off the "/" to leave just the trail ID

        if (requestFileName.contains(".json")) {

            String trail_id = requestFileName.replace(".json", "");

            SQLAdapter db = new SQLAdapter();
            ArrayList<HashMap<String, String>> results = db.sqlQuery("SELECT id, owner_uid, trails.name" +
                    " as trail_name, users.name as user_name, users.email as user_email, description FROM " +
                    "trails JOIN users ON trails.OWNER_UID = users.uid WHERE trails.id = ?", Arrays.asList(trail_id));

            gson.toJson(results);

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(results));
            out.flush();
        }
        if (requestFileName.contains(".gpx")) {

            String trail_id = requestFileName.replace(".gpx", "");

            SQLAdapter db = new SQLAdapter();
            ArrayList<HashMap<String, String>> results = db.sqlQuery("SELECT gpx FROM trails WHERE id = ?",
                                                                     Arrays.asList(trail_id));


            resp.setContentType("application/gpx+xml");
            PrintWriter out = resp.getWriter();
            out.print(results.get(0).get("gpx"));
            out.flush();
        }

    }
}
