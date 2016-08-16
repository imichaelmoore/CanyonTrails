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



        $('.photos').coverflow({
//            easing:			'easeOutElastic',
//            duration:		'slow',
            index: 3,
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
    });

</script>
<style type="text/css">


    .jumbotron {
        background-color: rgba(255, 255, 255, 0.0);
    }
</style>

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">

        <div class="photos">
            <img class="cover" data-name="Attic" src="components/coverflow/demo/attic.jpg"/>
            <img class="cover" data-name="Aurora Borealis" src="components/coverflow/demo/aurora.jpg"/>
            <img class="cover" data-name="Barbecued steak" src="components/coverflow/demo/barbecue.jpg"/>
            <img class="cover" data-name="Black swan" src="components/coverflow/demo/blackswan.jpg"/>
            <img class="cover" data-name="Chess" src="components/coverflow/demo/chess.jpg"/>
            <img class="cover" data-name="Fire" src="components/coverflow/demo/fire.jpg"/>
            <img class="cover" data-name="Keyboard" src="components/coverflow/demo/keyboard.jpg"/>
            <img class="cover" data-name="Locomotive" src="components/coverflow/demo/locomotive.jpg"/>
            <img class="cover" data-name="Novo-Diveevo monastery" src="components/coverflow/demo/diveevo.jpg"/>
            <img class="cover" data-name="Person" src="components/coverflow/demo/person.jpg"/>
            <img class="cover" data-name="Rose" src="components/coverflow/demo/rose.jpg"/>
            <img class="cover" data-name="Seagull" src="components/coverflow/demo/seagull.jpg"/>
            <img class="cover" data-name="Solar power" src="components/coverflow/demo/solarpower.jpg"/>
        </div>
        <div id="photos-info">
            <div id="photos-name"></div>
        </div>

    </div>

</div> <!-- /container -->

</body>
</html>