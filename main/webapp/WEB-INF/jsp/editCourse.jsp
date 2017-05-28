<%-- 
    Document   : createCourse
    Created on : May 26, 2017, 7:38:27 PM
    Author     : WIN 10
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" 
        import="java.sql.*" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="<c:url value = "/resources/image/favicon.ico"/>">
        <link rel="stylesheet" href="<c:url value = "/resources/fonts/font-awesome/css/font-awesome.min.css"/>">
        <link href="<c:url value = "/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value = "/resources/css/bootstrapRTL.min.css"/>" rel="stylesheet">
        <link rel='stylesheet' href="<c:url value = "/resources/css/style.css"/>" type='text/css' />
        <script type="text/javascript" src="<c:url value = "/resources/js/jquery-3.2.0.min.js"/>"></script>
        <script  type="text/javascript"  src="<c:url value = "/resources/js/script.js"/>"></script>

        <title>ویرایش درس</title>
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

        <div class="studentlist course">        
            <div class="container">
                <h2 class="title">ویرایش درس</h2>
                <form charset=utf-8 class="form-inline" method="post" action="<c:url value = "/login/savecourse" context="/MVCSesssion3"/>">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">انتخاب درس
                            <span class="caret"></span></button>
                       <%-- <select name="changables"> --%>
                            
                            <c:forEach items="${courselist}" var="changables">                               
                                <%--    <options value="${changables.value}">
                                    ${changables.key} here </options>   --%>
                                ${changables.key} here
                            </c:forEach>
                                    
                      <%--  </select> --%>
                    </div>
                    <button type="submit" class="btn btn-primary">انتخاب درس</button>
                </form>    
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