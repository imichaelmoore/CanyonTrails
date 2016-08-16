<jsp:useBean id="sessionBean"
             class="edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean"
             scope="session">
</jsp:useBean>
<html lang="en">
<jsp:include page="header.jsp"/>
<body>

<jsp:include page="navbar.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {

    });
</script>

<div class="container">
    <% if (sessionBean.isAuthenticated() == true) { %>

    <% if (sessionBean.errorMessage != null) { %>
    <div class="errorBox"><%= sessionBean.errorMessage %>
    </div>
    <% }
        sessionBean.setErrorMessage(null); %>
    <% if (sessionBean.successMessage != null) { %>
    <div class="successBox"><%= sessionBean.successMessage %>
    </div>
    <% }
        sessionBean.setSuccessMessage(null); %>


    <h2>Update account information</h2>

    <form action="update-account" method="post">

        <div class="form-section">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" class="form-control" id="name"
                       value="<%= sessionBean.getAuthenticatedUserName() %>">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" class="form-control" id="email"
                       value="<%= sessionBean.getAuthenticatedUserEmailAddress() %>">
            </div>
            <div class="form-group">
                <label for="email">Change Password (Leave blank for to keep current password)</label>
                <input type="password" name="password" class="form-control" id="password">
            </div>
        </div>


        <div class="submit-section">
            <input type="submit" class="btn btn-info" value="Update"/>
        </div>

    </form>

    <% } else { %>
    <h2>You must log in to see this page.</h2>
    <% } %>

</div>

</body>
</html>