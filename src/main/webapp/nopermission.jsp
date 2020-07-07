<%--
  Created by IntelliJ IDEA.
  User: 30315
  Date: 2019/11/23
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //custom-theme -->
    <link href="${pageContext.request.contextPath}/static/404/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!-- js -->
    <script src="${pageContext.request.contextPath}/static/404/js/jquery.min.js"></script>
    <!-- //js -->
    <!-- font-awesome icons -->
    <link href="${pageContext.request.contextPath}/static/404/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <link href="${pageContext.request.contextPath}/static/404/css/404-01.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/404/css/404-02.css" rel="stylesheet">
    <style>
        .main h1{
            color: red;
        }
    </style>
</head>
<body>
<div class="main">
    <canvas id="myCanvas"></canvas>
    <div class="agileinfo_404_main">
        <h1>没有权限进行操作</h1>
        <div class="w3_agile_main">
            <h2>Oooops.... Could not find it</h2>
            <p>For some reason the page you requested could not be found on Our server.</p>
            <div class="agile_404 w3layouts">
                <div class="agile_404_pos">
                    <h3>4<span>0</span>4<img src="${pageContext.request.contextPath}/static/404/images/1.png" alt=" " /> </h3>
                </div>
                <img src="${pageContext.request.contextPath}/static/404/images/3.png" alt=" " class="w3l"/>
            </div>
        </div>
        <div class="agileits_w3layouts_nav">
            <div class="w3_agileits_nav_left w3">
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <div class="wthree_nav_right">
                <ul class="agileits_social_list w3ls">
                    <li><a href="#" class="w3_agile_facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agile_twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="w3_agile_dribble"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="w3_agile_vimeo"><i class="fa fa-vimeo" aria-hidden="true"></i></a></li>
                </ul>
            </div>
            <div class="clear"> </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/404/js/particles.min.js"></script>
<script>
    window.onload = function() {
        Particles.init({
            selector: '#myCanvas',
            color: '#6b6b6b',
            connectParticles: true,
            minDistance: 100
        });
    };
</script>
</body>
</html>