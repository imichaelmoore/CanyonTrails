package edu.jhu.ep.su16.final_project.mmoore91.servlets.page_controllers;

import edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean;
import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*
 * This servlet receives an "add trail" form post, inserts the image and metadata into the database,
 * and redirects appropriately.
 */
public class AddTrailSubmit extends HttpServlet {

    private SQLAdapter db;

    // Source: http://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet
    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // SessionBean Bean
        HttpSession session = req.getSession();
        SessionBean s = null;
        if (session.getAttribute("sessionBean") == null) {
            s = new SessionBean();
        } else {
            s = (SessionBean) session.getAttribute("sessionBean");
        }

        String trailName = null;
        String description = null;

        String gpxFile = null;

        // As this is a multipart-form-upload, iterate through each part and determine if it is a form field
        // or a gpx file.
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    if (fieldName.equals("name")) {
                        trailName = fieldValue;
                    } else if (fieldName.equals("description")) {
                        description = fieldValue;
                    }
                } else {
                    InputStream inputStream = item.getInputStream();
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(inputStream, writer);
                    gpxFile = writer.toString();

                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }

        String trailID = UUID.randomUUID().toString();
        List params = Arrays.asList(trailID, s.getAuthenticatedUserUID(), trailName, description, gpxFile);
        db = new SQLAdapter();
        db.sqlQuery("INSERT INTO trails (id, owner_uid, name, description, gpx) VALUES (?,?,?,?,?)", params);

        String updateText = "Trail Added";
        db = new SQLAdapter();
        db.sqlQuery("INSERT INTO timeline (owner_uid, update_text, trail_id) VALUES (?,?,?)",
                Arrays.asList(s.getAuthenticatedUserUID(), updateText, trailID));


        resp.sendRedirect(req.getContextPath() + "/trails");

    }
}
