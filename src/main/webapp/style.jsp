<style type="text/css">

    #login-dp {
        min-width: 250px;
        padding: 14px 14px 0;
        overflow: hidden;
        background-color: rgba(255, 255, 255, .8);
    }

    #login-dp .help-block {
        font-size: 12px
    }

    #login-dp .bottom {
        background-color: rgba(255, 255, 255, .8);
        border-top: 1px solid #ddd;
        clear: both;
        padding: 14px;
    }

    #login-dp .social-buttons {
        margin: 12px 0
    }

    #login-dp .social-buttons a {
        width: 49%;
    }

    #login-dp .form-group {
        margin-bottom: 10px;
    }

    .btn-fb {
        color: #fff;
        background-color: #3b5998;
    }

    .btn-fb:hover {
        color: #fff;
        background-color: #496ebc
    }

    .btn-tw {
        color: #fff;
        background-color: #55acee;
    }

    .btn-tw:hover {
        color: #fff;
        background-color: #59b5fa;
    }

    @media (max-width: 768px) {
        #login-dp {
            background-color: inherit;
            color: #fff;
        }

        #login-dp .bottom {
            background-color: inherit;
            border-top: 0 none;
        }
    }

    .coverflow {
        height: 100px;
        border-bottom: solid 2px black;
    }

    .coverflow .cover {
        width: 100px;
        height: 100px;
        cursor: pointer;
        font-size: 500%;
        border: solid 2px black;
        text-align: center;

        background: #e2e2e2; /* Old browsers */
        /* IE9 SVG, needs conditional override of 'filter' to 'none' */
        background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2UyZTJlMiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjUwJSIgc3RvcC1jb2xvcj0iI2RiZGJkYiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjUxJSIgc3RvcC1jb2xvcj0iI2QxZDFkMSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZWZlZmUiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
        background: -moz-linear-gradient(top, #e2e2e2 0%, #dbdbdb 50%, #d1d1d1 51%, #fefefe 100%); /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #e2e2e2), color-stop(50%, #dbdbdb), color-stop(51%, #d1d1d1), color-stop(100%, #fefefe)); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top, #e2e2e2 0%, #dbdbdb 50%, #d1d1d1 51%, #fefefe 100%); /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top, #e2e2e2 0%, #dbdbdb 50%, #d1d1d1 51%, #fefefe 100%); /* Opera 11.10+ */
        background: -ms-linear-gradient(top, #e2e2e2 0%, #dbdbdb 50%, #d1d1d1 51%, #fefefe 100%); /* IE10+ */
        background: linear-gradient(to bottom, #e2e2e2 0%, #dbdbdb 50%, #d1d1d1 51%, #fefefe 100%); /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e2e2e2', endColorstr='#fefefe', GradientType=0); /* IE6-8 */
    }

    .coverflow .cover.current {
        opacity: 1;
        border-bottom: none;

        box-shadow: 0 0 16px rgba(0, 0, 0, .5);

        background: #ffffff; /* Old browsers */
        /* IE9 SVG, needs conditional override of 'filter' to 'none' */
        background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZmZmZiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjUwJSIgc3RvcC1jb2xvcj0iI2YzZjNmMyIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjUxJSIgc3RvcC1jb2xvcj0iI2VkZWRlZCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmZmZmYiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
        background: -moz-linear-gradient(top, #ffffff 0%, #f3f3f3 50%, #ededed 51%, #ffffff 100%); /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #ffffff), color-stop(50%, #f3f3f3), color-stop(51%, #ededed), color-stop(100%, #ffffff)); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top, #ffffff 0%, #f3f3f3 50%, #ededed 51%, #ffffff 100%); /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top, #ffffff 0%, #f3f3f3 50%, #ededed 51%, #ffffff 100%); /* Opera 11.10+ */
        background: -ms-linear-gradient(top, #ffffff 0%, #f3f3f3 50%, #ededed 51%, #ffffff 100%); /* IE10+ */
        background: linear-gradient(to bottom, #ffffff 0%, #f3f3f3 50%, #ededed 51%, #ffffff 100%); /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#ffffff', GradientType=0); /* IE6-8 */
    }

    .coverflow .cover.current:after {
        content: ' ';
        background-color: white;
        display: block;
        position: absolute;
        width: 132px;
        height: 16px;
        left: -16px;
        bottom: -16px;
    }

    /* CD covers */

    #photos-info {
        position: relative;
        text-align: center;
        z-index: 1000;
        text-shadow: 0 0 8px white;
    }

    #photos-name {
        font-size: 200%;
        font-weight: bold;
    }

    .clearfix {
        clear: both;
    }

    body {
        background-image: url('<%=request.getContextPath()%>/images/frontbg.jpg');
        padding-top: 65px;
    }

    .container {
        background-color: rgba(255, 255, 255, 0.7);
    }

    .errorBox {
        color: red;
        font-weight: 900;
        border: solid 1px red;
        /*-webkit-border-radius: 5px;*/
        /*-moz-border-radius: 5px;*/
        /*border-radius: 5px;*/
        padding: 5px;
    }

    .successBox {
        color: green;
        font-weight: 900;
        border: solid 1px green;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        padding: 5px;
    }

    .trailsDisplay {
        width: 70vw;
        border: solid 1px red;
    }

    #TrailByline {
        margin-top: 10px;
        font-size: 10px;
    }

    #TrailBylineUser {
        display: inline;
    }

    .trail_thumbnail {
        display: inline-block;
        width: 100px;
        height: 100px;
        margin: 0px;
        border: 0;
        background-position: center center;
        background-size: cover;
        margin-right:1px;
    }

    .frontpageText
    {
        padding:15px;
    }

</style>