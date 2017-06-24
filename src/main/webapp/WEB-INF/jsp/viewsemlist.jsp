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

        <script>
            $(document).ready(function() {
                $('button[name="semester"]').click(function(e) {
                    var semester = $(this).val();
                    alert(semester);
                    e.preventDefault();
                    $.ajax({
                        type: "post",
                        data: {semester: semester},
                        url: "/MVCSesssion3/login/viewcourses",
                        dataType: 'json',
                        success: function(response) {
                            var i = 1;
                            // we have the response
                            $('.results').empty();
                            $.each(response, function(key, val) {
                                $('.results').append('<tr style="border: 1px solid black;"><td><form charset=utf-8 method="post" action="<c:url value = "/login/studentlist" context="/MVCSesssion3"/>" style="padding: 10px;"><table><tr id="addr' + (i) + '"></tr></table></form></td></tr>');
                                $('#addr' + i).html("<td>" + i + "</td><td>" + val + "</td><td><button name='pcsid' type='submit' value='" + key + "'>انتخاب</button></td>");
                                i = i + 1;
                            })
                        },
                        error: function(e) {
                            alert('Error: ' + e);
                        }
                    });
                });
            });
        </script>

        <div class="grade">
            <h3 class="titlenews">ترم های تدریس شده</h3>
            <div class="container">
                <div class="row">
                    <form charset=utf-8 method="" action="">
                        <table>
                            <c:forEach items="${semesters}" var="semester">
                                <tr>
                                    <td id="term0">${semester.value}</td>
                                    <td><button name="semester" type="submit" value="${semester.key}" />انتخاب</button></td>
                                </tr>
                            </c:forEach>
                        </table>   
                    </form>					
                </div>
            </div>
        </div>

        <div class="">
            <h3 class="titlenews">دروس تدریس شده در ترم</h3>
            <div class="container">
                <div class="row">
                    <table class="results" style="border: 1px solid black;margin: auto;margin-bottom: 3em;">
                        
                    </table>
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