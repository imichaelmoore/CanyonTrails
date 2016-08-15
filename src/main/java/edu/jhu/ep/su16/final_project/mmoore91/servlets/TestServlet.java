package edu.jhu.ep.su16.final_project.mmoore91.servlets;

import edu.jhu.ep.su16.final_project.mmoore91.pojo.User;
import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        db.sqlQuery("INSERT INTO test_table (v) VALUES (1198)");
        results = db.sqlQuery("SELECT * FROM test_table");

        User u2 = new User("1d416283-00db-40ae-82fe-c49301fd8a40");
        System.out.println(u2.isValidUser());

        User u3 = new User("123");
        System.out.println(u3.isValidUser());

        System.out.println(u2.getUid());
        User u4 = new User(u2.getUid(), "abc123");
        System.out.println(u4.isValidUser());


        HttpSession session = req.getSession();
        if (session.getAttribute("sqlresponse") == null) {
            session.setAttribute("sqlresponse", results.get(0).get("v"));
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
        dispatcher.forward(req, resp);


    }
} // TestServlet
