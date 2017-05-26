<%-- 
    Document   : loginstd
    Created on : May 26, 2017, 9:41:27 AM
    Author     : WIN 10
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" 
        import="java.sql.*" %>
<html>
    <head>
        <link rel="icon" type="image/png" href="<c:url value = "/resources/image/favicon.ico"/>">
        <link rel="stylesheet" href="<c:url value = "/resources/fonts/font-awesome/css/font-awesome.min.css"/>">
        <link href="<c:url value = "/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value = "/resources/css/bootstrapRTL.min.css"/>" rel="stylesheet">
        <link rel='stylesheet' href="<c:url value = "/resources/css/style.css"/>" type='text/css' />
        <script type="text/javascript" src="<c:url value = "/resources/js/jquery-3.2.0.min.js"/>"></script>
        <script  type="text/javascript"  src="<c:url value = "/resources/js/script.js"/>"></script>
        <title>صفحه شخصی </title>
    </head>

    <body>
        <div class="menu">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="http://localhost:8084/MVCSesssion3/resources/image/logo.jpg" />
                    </div>

                    <div class="col-md-8">
                    
                    <ul>
                        <li><a>سیستم مدیریت آموزش</a></li>
                        <li><a>ورود به سامانه</a></li>
                        <li><a>معرفی سامانه</a></li>
                    </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="container">
            <div class="row">
                <div class="col-md-3 rightmenu">
                    <ul>
                    <li><a href="">اطلاعات جامع دانشجو</a></li>
                    <li><a href="">مشاهده دروس ارائه شده</a></li>
                    <li><a href="">وضعیت درسی ترم جاری</a></li>
                    <li><a href="">مشاهده اطلاعات شخصی</a></li>
                    <li><a href="">ویرایش اطلاعات شخصی</a></li>
                    <li><a href="/MVCSesssion3/login/logout">خروج</a></li>
                    </ul>
                </div>
                <div class="col-md-9">
                    <div class="welcomemsg">
                        <p><span>${message}</span> عزیز! خوش آمدی</p>
                    </div>
                </div>
            </div>
        </div>
        
        
        <div class="footer">
            
            <div class="container">
                <div class="row">
                    <div class="cr">
                         کلیه حقوق مادی و معنوی برای سامانه مدیریت آموزشی محفوظ است
                    </div>
                </div>
            </div>
            
        </div>
    </body>

</html>