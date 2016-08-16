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
                    var h = '<img class="cover" data-name="' + v.trail_name + '" src="<%=request.getContextPath()%>/api/image/' + v.image_id + '" style="height:50vh;max-width:700px;width: expression(this.width > 700 ? 700: true);" />';
                    $('#photolist').append(h);
                    console.log(h);
                });

                $('#photolist').coverflow({
                    index: 1,
                    width: 320,
                    height: 240,
                    visible: 'density',
                    selectedCss: {opacity: 1},
                    outerCss: {opacity: 1},

                    confirm: function () {
                        console.log('Confirm');
                    },

                    change: function (event, cover) {
                        var img = $(cover).children().andSelf().filter('img').last();
                        $('#photos-name').text(img.data('name') || 'unknown');
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
    <div class="jumbotron" >

        <div class="photos" id="photolist">
        </div>
        <div id="photos-info">
            <div id="photos-name"></div>
        </div>

    </div>

</div> <!-- /container -->

</body>
</html>