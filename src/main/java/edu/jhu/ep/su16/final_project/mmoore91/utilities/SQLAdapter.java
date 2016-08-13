package edu.jhu.ep.su16.final_project.mmoore91.utilities;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by moorema1 on 8/12/16.
 */
public class SQLAdapter {

    private Connection conn;
    private InitialContext cxt = null;
    private  DataSource ds = null;

    public SQLAdapter() throws ServletException {

        try {
            cxt = new InitialContext();
            ds = (DataSource)cxt.lookup("java:comp/env/jdbc/derby");
        }
        catch (NamingException ex) {
            throw new ServletException("naming context error", ex);
        }
        try {
            conn = ds.getConnection();
        }
        catch (SQLException ex) {
            throw new ServletException("connection error", ex);
        }
    }

    public ArrayList<HashMap<String, String>> sqlQuery(String sql)
    {
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<HashMap<String, String>> response = new ArrayList<>();
        try {
            stmnt = conn.createStatement();
        } catch (SQLException e) {
        }
        try {
            System.out.println("EXECUTING: " + sql);

            if(sql.toLowerCase().startsWith("insert ") || sql.toLowerCase().startsWith("update "))
            {
                stmnt.executeUpdate(sql);
            }
            else
            {
                rs = stmnt.executeQuery(sql);
                ResultSetMetaData metadata = rs.getMetaData();

                while (rs.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    for(int i = 1; i <= metadata.getColumnCount(); i++) {
                        row.put(metadata.getColumnName(i), rs.getString(i));
                    }
                    response.add(row);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

}
