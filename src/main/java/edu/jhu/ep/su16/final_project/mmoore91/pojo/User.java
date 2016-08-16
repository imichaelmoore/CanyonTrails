package edu.jhu.ep.su16.final_project.mmoore91.pojo;

import edu.jhu.ep.su16.final_project.mmoore91.utilities.SQLAdapter;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;


public class User implements Serializable {

    private String name;
    private String emailAddress;
    private String uid;
    private boolean validUser = false;

    public User(String name, String emailAddress, String password) throws ServletException // Create a new user
    {
        this.name = name;
        this.emailAddress = emailAddress;
        this.uid = UUID.randomUUID().toString();

        SQLAdapter db = new SQLAdapter();
        db.sqlQuery("INSERT INTO users (uid, name, email) VALUES (?, ?, ?)", Arrays.asList(this.uid, this.name, this.emailAddress));

        setPassword(password);
        this.validUser = true;
    }

    public User(String emailAddress, String password) throws ServletException // Get existing user and validate password
    {
        SQLAdapter db = new SQLAdapter();
        ArrayList<HashMap<String, String>> r = db.sqlQuery("SELECT * FROM users WHERE email = ?", Arrays.asList(emailAddress));
        if (r.size() > 0) {
            this.uid = r.get(0).get("uid");
            this.name = r.get(0).get("name");
            this.emailAddress = r.get(0).get("email");
            if (verifyPassword(password)) {
                this.validUser = true;
            }
        }

    }

    public User(String uid) throws ServletException  // Get existing user and populate object
    {
        SQLAdapter db = new SQLAdapter();
        ArrayList<HashMap<String, String>> r = db.sqlQuery("SELECT * FROM users WHERE uid = ?", Arrays.asList(uid));
        if (r.size() > 0) {
            this.uid = r.get(0).get("uid");
            this.name = r.get(0).get("name");
            this.emailAddress = r.get(0).get("email");
            this.validUser = true;
        }
    }

    public void updateName(String name) throws ServletException {
        SQLAdapter db = new SQLAdapter();
        db.sqlQuery("UPDATE users SET name = ? WHERE uid = ?", Arrays.asList(name, this.uid));
    }

    public boolean isValidUser() {
        return validUser;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean verifyPassword(String password) throws ServletException {

        SQLAdapter db = new SQLAdapter();
        ArrayList<HashMap<String, String>> r = db.sqlQuery("SELECT password_hash FROM users WHERE uid = ?", Arrays.asList(this.uid));

        if (BCrypt.checkpw(password, r.get(0).get("password_hash"))) {
            return true;
        } else {
            return false;
        }
    }

    public void setPassword(String password) throws ServletException {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        SQLAdapter db = new SQLAdapter();
        db.sqlQuery("UPDATE users SET password_hash = ? WHERE uid = ?", Arrays.asList(hashed, this.uid));
    }

}
