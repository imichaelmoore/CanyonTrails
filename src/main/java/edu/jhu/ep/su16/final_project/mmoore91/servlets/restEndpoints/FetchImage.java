package edu.jhu.ep.su16.final_project.mmoore91.servlets.restEndpoints;

import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * This servlet returns an image from the database.
 */

public class FetchImage extends HttpServlet {

    private SQLAdapter db;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String requestFileName = req.getPathInfo().substring(1);

        db = new SQLAdapter();
        ArrayList<HashMap<String, String>> results = db.sqlQuery("select mimetype from images where id = ?",
                Arrays.asList(requestFileName));

        resp.setContentType(results.get(0).get("mimetype"));

        db = new SQLAdapter();
        byte[] data = new byte[0];
        try {
            data = db.sqlSelectBlob("SELECT data FROM images WHERE id = ?", requestFileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getOutputStream().write(data);

    }
}
