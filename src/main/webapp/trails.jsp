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
</style>

<script type="text/javascript">

    function addNewEntryRecentUpdates(trail_name, trail_id, user_name, timestamp, update_text) {
        var html = '<div class="timeline_entry">' +
                '<div class="timeline_update_text">' + update_text + "</div>" +
                '<div class="timeline_trail_name"><a href="<%=request.getContextPath()%>/trails/' + trail_id + '">' + trail_name + "</a> updated</div> " +
                '<div class="timeline_user_name">by ' + user_name + "</div> " +
                '<div class="timeline_timestamp">at ' + timestamp + "</div> " + '</div>';
        $('#timeline').append(html);

    }


    function addNewEntryMyTrails(trail_name, trail_id) {
        var html = '<div class="timeline_entry">' +
                '<div class="timeline_trail_name"><a href="<%=request.getContextPath()%>/trails/' + trail_id + '">' + trail_name + "</a></div> " +
                '</div>';

        $('#my_trails').append(html);

    }
    function addNewEntryAllTrails(trail_name, trail_id) {
        var html = '<div class="timeline_entry">' +
                '<div class="timeline_trail_name"><a href="<%=request.getContextPath()%>/trails/' + trail_id + '">' + trail_name + "</a></div> " +
                '</div>';

        $('#all_trails').append(html);

    }

    $(document).ready(function () {

        $.ajax({
            url: '<%=request.getContextPath()%>/api/timeline.json',
            method: 'get',
            success: function (d) {
                $.each(d, function (k, v) {
                    var momentDate = moment(moment(v.mtime, 'YYYY-MM-DD HH:mm:ss.SSS')).fromNow();
                    addNewEntryRecentUpdates(v.trail_name, v.trail_id, v.user_name, momentDate, v.update_text);
                });
            }
        });

        $.ajax({
            url: '<%=request.getContextPath()%>/api/mytrails.json',
            method: 'get',
            success: function (d) {
                if(d.length > 0) { $('#my_trails').html("");}
                $.each(d, function (k, v) {
                    console.log(v);
                    addNewEntryMyTrails(v.trail_name, v.trail_id);
                });
            }
        });

        $.ajax({
            url: '<%=request.getContextPath()%>/api/all_trails.json',
            method: 'get',
            success: function (d) {
                if(d.length > 0) { $('#all_trails').html("");}
                $.each(d, function (k, v) {
                    console.log(v);
                    addNewEntryAllTrails(v.trail_name, v.trail_id);
                });
            }
        });

    });
</script>


<div class="container">
    <div class="row" style="padding-bottom:10px">
        <div class="col-md-9">
            <h3>Recent Updates</h3>
            <%--<% for(Strung u : )--%>
            <div id="timeline"></div>
        </div>
        <div class="col-md-3">
            <h3>My Trails</h3>
            <div id="my_trails">None <a href="/trails/add">yet</a>!</div>

            <h3>Other Trails</h3>
            <div id="all_trails">None found.</div>
        </div>
    </div>
</div> <!-- /container -->

</body>
</html>