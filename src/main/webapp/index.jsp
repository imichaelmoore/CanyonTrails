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

        $.ajax({
            url: '<%=request.getContextPath()%>/api/recent_images.json',
            method: 'get',
            success: function (d) {
                $.each(d, function (k, v) {

                    //brilliant CSS style from http://stackoverflow.com/questions/3678378/is-there-a-way-to-specify-a-max-height-or-width-for-an-image
                    var h = '<img class="cover" data-name="' + v.trail_name + '" data-submitter="' + v.user_name + '" data-trailid="' + v.trail_id + '" src="<%=request.getContextPath()%>/api/image/' + v.image_id + '" style="height:500px;max-width:700px;width: expression(this.width > 700 ? 700: true);" />';
                    $('#photolist').append(h);
                    console.log(h);
                });

                $('#photolist').coverflow({
                    index: 1,
                    width: 700,
                    height: 500,
                    visible: 'density',
                    selectedCss: {opacity: 1},
                    outerCss: {opacity: 1},

                    confirm: function () {
                        console.log('Confirm');
                    },

                    change: function (event, cover) {
                        var img = $(cover).children().andSelf().filter('img').last();
                        $('#photos-name').html("<a href='<%=request.getContextPath()%>/trails/" + img.data('trailid') + "'> " + img.data('name') || 'unknown' + "</a>");
                        $('#photos-submitter').text("Submitted by " + img.data('submitter') || 'unknown');
                    }
                });

            }
        });


    });

</script>
<style type="text/css">


    .jumbotron {
        background-color: rgba(255, 255, 255, 0.0);
    }
</style>

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="row">
        <div class="col-md-12">
            <h2></h2>
            <div class="photos" id="photolist">
            </div>
            <div id="photos-info">
                <div id="photos-name"></div>
                <div id="photos-submitter"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="frontpageText"><h3 align="center">Share your GPS trails</h3>
                    <p>
                        Your track your hikes in the wilderness, and want a place to share them! CanyonTrails is built
                        for you
                        - with native support of the <a href="http://www.topografix.com/gpx.asp">GPX</a> file format
                        supported by most GPS trackers. If you have an iPhone, we recommend the great
                        <a href="https://itunes.apple.com/us/app/gps-tracks/id425589565?mt=8">GPS Tracks</a> app to
                        track
                        your hikes and waypoints.
                    </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="frontpageText"><h3 align="center">Share your photos</h3>
                    <p>
                        Who doesn't take a photo or two (or twenty) while hiking? With CanyonTrails, you can share these
                        photos with the world, right alongside your GPS tracks.
                    </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="frontpageText"><h3 align="center">Share your stories</h3>
                    <p>
                        What did you see? Who did you meet? Use CanyonTrails to not only record GPS and image histories
                        of your
                        hikes, but also include stories and tips for other hikers.
                    </p>
                </div>
            </div>

        </div>
    </div>

</div> <!-- /container -->


</body>
</html>