package edu.jhu.ep.su16.final_project.mmoore91.servlets;

import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by moorema1 on 8/6/16.
 */
public class TestServlet extends HttpServlet {
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ArrayList<HashMap<String, String>> results;
        db.sqlQuery("INSERT INTO test_table (T) VALUES (19)");
        results = db.sqlQuery("SELECT * FROM test_table");


        HttpSession session = req.getSession();
        if (session.getAttribute("sqlresponse") == null) {
            session.setAttribute("sqlresponse", results.get(5).get("T"));
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
        dispatcher.forward(req, resp);


    }
} // TestServlet
