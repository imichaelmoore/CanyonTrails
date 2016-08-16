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
    <h2>Add a new trail</h2>

    <form action="<%=request.getContextPath()%>/trails/add-submit" method="post" enctype="multipart/form-data">

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
                <label for="name">Trail Name</label>
                <input type="text" name="name" class="form-control" id="name">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" rows="5" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="gpx">GPX File</label>
                <input type="file" name="gpx" class="form-control" id="gpx">
            </div>
            <div class="form-group">
                You will have the ability to attach images after the trail is created.
            </div>

        </div>



        <div class="submit-section">
            <input type="submit" class="btn btn-info" value="Create New Trail" />
        </div>

    </form>


</div> <!-- /container -->

</body>
</html>




