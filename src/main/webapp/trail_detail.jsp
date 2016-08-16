<jsp:useBean id="sessionBean"
             class="edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean"
             scope="session">
</jsp:useBean>
<html lang="en">
<jsp:include page="header.jsp"/>
<body>

<jsp:include page="navbar.jsp"/>

<style type="text/css">
    .timeline_entry {
        padding: 5px;
        border: solid 1px #666;
        margin-bottom: 10px;
    }

    .timeline_trail_name {
        display: inline-block;
    }

    .timeline_user_name {
        display: inline-block;
    }

    .timeline_timestamp {
        display: inline-block;
    }

    #map {
        width: 100%;
        vertical-align: middle;
        horiz-align: center;
    }

    .largeImage {

        zoom: 2;
    / / increase if you have very small images display: block;
        margin: auto;
        height: auto;
        max-height: 100%;

        width: auto;
        max-width: 100%;
    }

    }
</style>

<script type="text/javascript"
        src="https://maps.google.com/maps/api/js?key=AIzaSyBimWY4BxyMENccWJDG1y2Zhpqenbf3MJw"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/components/loadgpx/loadgpx.js"></script>

<script type="text/javascript">

    var trail_id = $(location).attr('pathname').split("/")[3];


    function loadGPXFileIntoGoogleMap(map, filename) {
        $.ajax({
            url: filename,
            dataType: "xml",
            success: function (data) {
                var parser = new GPXParser(data, map);
                parser.setTrackColour("#ff0000");
                parser.setTrackWidth(5);
                parser.setMinTrackPointDelta(0.001);
                parser.centerAndZoom(data);
                parser.addTrackpointsToMap();
                parser.addWaypointsToMap();
            }
        });
    }

    function displayImage(id) {
        $('#map').html("<img src='<%=request.getContextPath()%>/api/image/" + id + "' class='largeImage' />");
        $('#map').css("background-color", "#000000");
        $('#map').css("height", "auto");

    }


    $(document).ready(function () {

        $('#trail_id_hidden_element').val(trail_id);

        $.ajax({
            url: '<%=request.getContextPath()%>/api/images_for_trail/' + trail_id + '.json',
            method: 'get',
            success: function (d) {
                $.each(d, function (k, v) {
                    $('#photoscroll').append("<span onClick='displayImage(\"" + v.id + "\")'>" +
                            "<img src='<%=request.getContextPath()%>/api/image/" + v.id + "' class='trail_thumbnail'></span>");
                });
            }
        });


        $.ajax({
            url: '<%=request.getContextPath()%>/api/trail/' + trail_id + '.json',
            method: 'get',
            success: function (d) {
                $.each(d, function (k, v) {
                    $('#TrailName').html(v.trail_name);
                    $('#TrailDescription').html(v.description);
                    $('#TrailBylineUser').html("<a href='mailto:" + v.user_email + "'>" + v.user_name + "</a>");
                });
            }
        });

        var mapOptions = {
            zoom: 8,
            mapTypeId: 'satellite'
        };
        var map = new google.maps.Map(document.getElementById("map"),
                mapOptions);
        loadGPXFileIntoGoogleMap(map, '<%=request.getContextPath()%>/api/trail/' + trail_id + '.gpx');

    });


</script>


<div class="container">
    <div class="row" style="padding-bottom:10px">
        <div class="col-md-9">
            <h3></h3>
            <%--<% for(Strung u : )--%>
            <div id="timeline"></div>
            <div id="map" style="height:600px;border:solid 1px #666;"></div>
            <div id="info">&nbsp;</div>

        </div>
        <div class="col-md-3">
            <h3 id="TrailName"></h3>
            <div id="TrailDescription"></div>
            <div id="TrailByline">Submitted by
                <div id="TrailBylineUser"></div>
            </div>
            <div id="TrailImages"></div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-9">
            <div id="photoscroll" style="padding:5px;margin-bottom:10px;"></div>
        </div>
        <div class="col-md-3">
            <form action="<%=request.getContextPath()%>/trail/add-image" method="post" enctype="multipart/form-data">
                <div class="form-section">
                    <div class="form-group">
                        <input type="hidden" name="trail_id" id="trail_id_hidden_element"/>
                        <input type="file" name="gpx" class="form-control" id="gpx">
                    </div>
                </div>
                <div class="submit-section" style="text-align:center">
                    <input type="submit" class="btn btn-info" value="Add Image"/>
                </div>
        </div>
        </form>
    </div>
</div> <!-- /container -->

</body>
</html>