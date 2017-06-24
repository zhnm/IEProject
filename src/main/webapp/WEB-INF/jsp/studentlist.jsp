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

        <title>لیست دانشجویان</title>
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

        <div class="mark">        
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <ul>
                            <li>عنوان درس : ${coursename}</li>                       
                        </ul>
                    </div>

                    <div class="col-md-6">
                        <ul>
                            <li>ترم : ${term}</li>
                        </ul>
                    </div>

                </div>
                <form charset=utf-8 method="post" action="<c:url value = "/login/studentlist/save" context="/MVCSesssion3"/>">
                    <h1>${message}</h1>
                    <table id="tab_logic">
                        <caption class="text-center"></caption>

                        <thead>
                            <tr>
                                <th>ردیف</th>
                                <th>شماره دانشجویی</th>
                                <th>نام خانوادگی و نام</th>
                                <th>رشته</th> 
                                <th>نمره</th>
                                <th>وضع نمره</th>                                  
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${students}" var="list" varStatus="radif">
                                <tr>
                                    <td><c:out value="${radif.getCount()}"/></td>
                                    <c:set var="grade" value="g${radif.getIndex()}"/>
                                    <c:set var="st" value="s${radif.getIndex()}"/>
                                    <c:forEach items="${list}" var="student" varStatus="myIndex">                                          
                                        <c:if test = "${myIndex.getIndex() == 0}"> <td><input type="hidden" name="${st}" value="${student}"><c:out value="${student}"/></td> </c:if>
                                        <c:if test = "${myIndex.getIndex() == 3}"> <td><input type="text" name="${grade}" value="${student}" placeholder="${student}"/></td> </c:if>
                                        <c:if test = "${(myIndex.getIndex() != 3) && (myIndex.getIndex() != 0)}"> <td><c:out value="${student}"/></td> </c:if>
                                    </c:forEach>
                                </tr>
                            </c:forEach>

                        </tbody>

                    </table>
                     <button type="submit" >اعمال تغییرات</button>
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