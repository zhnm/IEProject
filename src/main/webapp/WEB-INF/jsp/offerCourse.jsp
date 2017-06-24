<%-- 
    Document   : createCourse
    Created on : May 26, 2017, 7:38:27 PM
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
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="<c:url value = "/resources/image/favicon.ico"/>">
        <link rel="stylesheet" href="<c:url value = "/resources/fonts/font-awesome/css/font-awesome.min.css"/>">
        <link href="<c:url value = "/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value = "/resources/css/bootstrapRTL.min.css"/>" rel="stylesheet">
        <link rel='stylesheet' href="<c:url value = "/resources/css/style.css"/>" type='text/css' />
        <script type="text/javascript" src="<c:url value = "/resources/js/jquery-3.2.0.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/bootstrap-select.min.js"/>"></script>
        <script  type="text/javascript"  src="<c:url value = "/resources/js/script.js"/>"></script>

        <title>ارائه درس</title>
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
        <!--Show offered courses-->
        <div class="grade">
            <h3 class="titlenews">دروس تعریف شده در ترم جاری</h3>
            <div class="container">
                <div class="row">
                    <table>
                        <tr>
                            <th>شناسه</th>
                            <th>نام درس</th>
                            <th>گرایش</th>
                            <th>واحد</th>
                            <th>نام استاد</th>
                        </tr>
                        <c:forEach items="${current}" var="curr">
                            <tr>
                                <c:forEach items="${curr}" var="cur">
                                    <td><c:out value="${cur}"/></td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>             
                </div>
            </div>
        </div>

        <div style="margin-bottom: 3em">
            <h3 class="titlenews">تعریف درس جدید در ترم جاری</h3>
            <div class="container">
                <div class="row">
                    <form charset=utf-8 class="form-inline" method="post" action="<c:url value = "/login/editCurrentSemester/save" context="/MVCSesssion3"/>">
                        <div class="col-md-6">
                            <select class="bootstrap-select" name="course" style="display: block;margin: 2em auto;">
                                <c:forEach items="${courselist}" var="course">                               
                                    <option name="" value="${course.value}">${course.key}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <select class="bootstrap-select" name="prof" style="display: block;margin: 2em auto;">
                                <c:forEach items="${proflist}" var="prof">                               
                                    <option name="" value="${prof.key}">${prof.value}</option>
                                </c:forEach>
                            </select>
                             
                        </div>
                        <button type="submit" class="btn btn-primary">ثبت درس در ترم جاری</button>
                    </form>
                        <h1>${massage}</h1> 
                </div>
            </div>
        </div>
        <!--create new term.-->
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