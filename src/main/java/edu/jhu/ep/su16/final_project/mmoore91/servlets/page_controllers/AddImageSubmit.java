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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*
 * This servlet receives an image upload form post, inserts the image and metadata into the database,
 * and redirects appropriately.
 */


public class AddImageSubmit extends HttpServlet {

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

        String trail_id = null;
        String contentType = null;

        byte[] image = null;

        // As this is a multipart-form-upload, iterate through each part and determine if it is a form field
        // or an image.
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    if (fieldName.equals("trail_id")) {
                        trail_id = fieldValue;
                    }
                } else {
                    // Get the image as a Byte array
                    InputStream inputStream = item.getInputStream();
                    contentType = item.getContentType();
                    image = IOUtils.toByteArray(inputStream);
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }

        // Insert the metadata
        String image_id = UUID.randomUUID().toString();
        List params = Arrays.asList(image_id, contentType, trail_id, s.getAuthenticatedUserUID());
        db = new SQLAdapter();
        db.sqlQuery("INSERT INTO images (id, mimetype, trail_id, owner_uid) VALUES (?,?,?,?)", params);

        // Insert the image as a blob, using a special SQLAdapter function for blobs.
        try {
            db = new SQLAdapter();
            db.sqlInsertBlob("UPDATE images SET data = ? WHERE id = ?", image, image_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Insert an update to the timeline table
        String updateText = "New image added";
        db = new SQLAdapter();
        db.sqlQuery("INSERT INTO timeline (owner_uid, update_text, trail_id) VALUES (?,?,?)",
                Arrays.asList(s.getAuthenticatedUserUID(), updateText, trail_id));


        resp.sendRedirect(req.getContextPath() + "/trails/" + trail_id);

    }
}
