package edu.jhu.ep.su16.final_project.mmoore91.beans;

import edu.jhu.ep.su16.final_project.mmoore91.pojo.User;

import java.io.Serializable;

/**
 * Created by moorema1 on 8/14/16.
 */
public class SessionBean implements Serializable {

    public String authenticatedUserName;
    public String authenticatedUserUID;
    public String authenticatedUserEmailAddress;
    public String errorMessage = null;
    public String successMessage = null;
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
