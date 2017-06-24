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

        <title>تغییر درس</title>
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
                <h2 class="title">تغییر درس</h2>
                <form charset=utf-8 class="form-inline" method="post" action="<c:url value = "/login/editcourse/save" context="/MVCSesssion3"/>">

                    <div class="row">
                        <div class="col-md-2"><label for="name" class="control-label">نام درس :</label></div>
                        <div class="col-md-10"><input type="text" class="form-control" value="${sessionScope.cname}" name="name" id="name"  placeholder=""/></div>
                    </div>       

                    <div class="row">
                        <div class="col-md-2"><label for="name" class="control-label">تعداد واحد:</label></div>
                        <div class="col-md-10"><input type="text" class="form-control" name="unit" id="name"  value="${sessionScope.unit}" placeholder=""/></div>
                    </div>

                    <div class="row">
                        <div class="col-md-2"><label for="phone" class="control-label">نوع درس :</label></div>
                        <div class="col-md-10">
                            <select class="bootstrap-select" name="type">
                                <option value="theoritical" selected="selected">اختیاری</option>
                                <option value="practical">اصلی</option>
                                <option value="theoritical/practical">آزمایشگاه</option>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-2"><label for="name" class="control-label">گروه درس :</label></div>
                        <div class="col-md-10"><input type="text" class="form-control" name="major" id="username"  value="${sessionScope.major}" value="${sessionScope.major}" placeholder=""/></div>
                    </div>

                    <div class="row">
                        <div class="col-md-2"><label for="name" class="control-label">گرایش درس :</label></div>
                        <div class="col-md-10"><input type="text" class="form-control" name="concentration" id="username"  value="${sessionScope.concentration}" placeholder=""/></div>
                    </div>
                    <div class="row">
                        <div class="col-md-2"><label for="name" class="control-label">گرایش های مجاز :</label></div>
                        <c:forEach items="${sessionScope.allowed}" var="alo">                               
                            <label><input name="allow" type="checkbox" checked value="<c:out value="${alo}" />" style="position: relative"><c:out value="${alo}" /></label>
                        </c:forEach>
                            
                        <c:forEach items="${sessionScope.allowedun}" var="aloun">                               
                            <label><input name="allow" type="checkbox" value="${aloun}" style="position: relative">${aloun}</label>
                        </c:forEach>
                            
                    </div>
                    <div class="row">
                        <div class="col-md-2"><label for="name" class="control-label">پیش نیاز درس :</label></div>
                        <div class="col-md-10 checkbox">
                            <c:forEach items="${sessionScope.precourses}" var="precou">                               
                                <label><input name="preco" type="checkbox" value="${precou}" checked style="position: relative">${precou}</label>
                                </c:forEach>
                                <c:forEach items="${sessionScope.precouncheck}" var="unch">                               
                                <label><input name="preco" type="checkbox" value="${unch}" style="position: relative">${unch}</label>
                                </c:forEach>
                        </div>
                    </div>

                    <h1>${massage}</h1>   
                    <button type="submit" class="btn btn-primary">ثبت درس</button>
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