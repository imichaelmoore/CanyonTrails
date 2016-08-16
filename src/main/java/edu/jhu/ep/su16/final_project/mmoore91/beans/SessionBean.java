package edu.jhu.ep.su16.final_project.mmoore91.beans;

import edu.jhu.ep.su16.final_project.mmoore91.pojo.User;

import java.io.Serializable;

/*
 * This bean stores information about the current session.
 * I chose to use a Bean rather than the session object in order to enforce some naming conventions
 * and be sure I was consistent in my use of the session.
 */

public class SessionBean implements Serializable {

    public String authenticatedUserName;
    public String authenticatedUserUID;  // UUID4 format as a string
    public String authenticatedUserEmailAddress;
    public String errorMessage = null;  // Used to display an error message on a form submission
    public String successMessage = null;// Used to display an success message on a form submission
    public boolean isAuthenticated = false;


    public String getAuthenticatedUserName() {
        return authenticatedUserName;
    }

    public void setAuthenticatedUserName(String authenticatedUserName) {
        this.authenticatedUserName = authenticatedUserName;
    }

    public String getAuthenticatedUserUID() {
        return authenticatedUserUID;
    }

    public void setAuthenticatedUserUID(String authenticatedUserUID) {
        this.authenticatedUserUID = authenticatedUserUID;
    }

    public String getAuthenticatedUserEmailAddress() {
        return authenticatedUserEmailAddress;
    }

    public void setAuthenticatedUserEmailAddress(String authenticatedUserEmailAddress) {
        this.authenticatedUserEmailAddress = authenticatedUserEmailAddress;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }


}
