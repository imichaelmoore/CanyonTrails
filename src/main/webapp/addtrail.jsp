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

        $("#add_trail_form").submit(function (event) {

            if ($('#gpx').val().indexOf('.gpx') == -1 && $('#gpx').val().indexOf('.GPX') == -1) {
                event.preventDefault();
                alert("You must upload a GPX file.");
            }

        });
    });
</script>

<div class="container">
    <h2>Add a new trail</h2>

    <form action="<%=request.getContextPath()%>/trails/add-submit" method="post" id='add_trail_form'
          enctype="multipart/form-data">

        <div class="form-section">
            <div class="form-group">
                <label for="name">Trail Name</label>
                <input type="text" name="name" class="form-control" id="name" required="true">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" rows="5" name="description" required="true"></textarea>
            </div>
            <div class="form-group">
                <label for="gpx">GPX File</label>
                <input type="file" name="gpx" class="form-control" id="gpx" required="true">
            </div>
            <div class="form-group">
                <b>All fields are required.</b> You will have the ability to attach images after the trail is created.
            </div>

        </div>

        <div class="submit-section">
            <input type="submit" class="btn btn-info" value="Create New Trail"/>
        </div>

    </form>


</div>

</body>
</html>




