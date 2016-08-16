package edu.jhu.ep.su16.final_project.mmoore91.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by moorema1 on 8/12/16.
 */
public class SQLAdapter {

    public Connection conn;
    private InitialContext cxt = null;
    private DataSource ds = null;

    Logger logger = LoggerFactory.getLogger(SQLAdapter.class);

    public SQLAdapter() throws ServletException {

        try {
            cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:comp/env/jdbc/derby");
        } catch (NamingException ex) {
            throw new ServletException("naming context error", ex);
        }

        try {
            conn = ds.getConnection();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            throw new ServletException("connection error", ex);
        }
    }

    public ArrayList<HashMap<String, String>> sqlQuery(String sql) {
        List<String> l = Arrays.asList();
        return this.sqlQuery(sql, l);
    }

    public ArrayList<HashMap<String, String>> sqlQuery(String sql, List<String> parameters) {
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<HashMap<String, String>> response = new ArrayList<>();

        try {

            logger.info("EXECUTING: " + sql);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            int i = 1;
            for (String s : parameters) {
                pstmt.setString(i, s);
                i++;
            }

            if (sql.toLowerCase().startsWith("insert ") || sql.toLowerCase().startsWith("update ")) {
                pstmt.executeUpdate();
            } else {

                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();

                while (rs.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    for (i = 1; i <= metadata.getColumnCount(); i++) {
                        row.put(metadata.getColumnName(i).toLowerCase(), rs.getString(i));  //Derby stores all columns as caps?!
                    }
                    response.add(row);
                }
            }

        } catch (SQLException e) {
            logger.error("SQL Failed: " + sql + " Parameters: " + parameters.toString());
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void sqlInsertBlob(String sql, byte[] bytesToInsert, String id) throws SQLException {

        PreparedStatement ps;
        ps = conn.prepareStatement(sql);

        ps.setBinaryStream(1, new ByteArrayInputStream(bytesToInsert), bytesToInsert.length);
        ps.setString(2, id);

        ps.execute();

        conn.commit();
        conn.close();

    }

    public byte[] sqlSelectBlob(String sql, String id) throws SQLException {

        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        byte[] data = null;
        while (rs.next()) {
            data = rs.getBytes("data");
        }

        conn.close();

        return data;

    }

    public void close() throws SQLException {
        conn.commit();
        conn.close();
    }


}
