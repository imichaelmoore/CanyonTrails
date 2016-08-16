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

/**
 * Created by moorema1 on 8/6/16.
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
        ArrayList<HashMap<String, String>> results = db.sqlQuery("select ROW_NUMBER() OVER() as rn, images.id, images.trail_id, images.owner_uid, trails.name, users.name FROM images JOIN trails ON trails.id = images.trail_id JOIN users ON users.uid = trails.owner_uid ORDER BY uploaded DESC FETCH FIRST 10 ROWS ONLY");

        gson.toJson(results);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(results));
        out.flush();

    }
}
