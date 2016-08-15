<jsp:useBean id="sessionBean"
             class="edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean"
             scope="session" >
</jsp:useBean>
<html lang="en">
<jsp:include page="header.jsp" />
<body>

<jsp:include page="navbar.jsp" />

<script type="text/javascript">
    $(document).ready(function() {

    });
</script>

<div class="container">
  <% if (sessionBean.isAuthenticated() == false) { %>

    <% if (sessionBean.errorMessage != null) { %>
    <div class="errorBox"><%= sessionBean.errorMessage %></div>
    <% } sessionBean.setErrorMessage(null); %>



  <h2>Register a new Account</h2>

  <form action="register-account" method="post">

    <%--<% if(errorMessage != null) { %>--%>
    <%--<div class="form-section-header" style="color:red">Error</div>--%>
    <%--<div class="form-section">--%>
      <%--<div class="form-group">--%>
        <%--<%= errorMessage %>--%>
      <%--</div>--%>
    <%--</div>--%>
    <%--<% } %>--%>

    <div class="form-section">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" name="name" class="form-control" id="name">
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" name="email" class="form-control" id="email">
      </div>
      <div class="form-group">
        <label for="email">Password</label>
        <input type="password" name="password" class="form-control" id="password">
      </div>
    </div>



    <div class="submit-section">
      <input type="submit" class="btn btn-info" value="Register" />
    </div>

  </form>

  <% } else { %>
    <h2>You already have a valid account.</h2>
  <% } %>

</div>

</body>
</html>