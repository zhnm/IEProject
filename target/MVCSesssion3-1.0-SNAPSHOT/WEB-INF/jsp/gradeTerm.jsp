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

        <title>کارنامه ترمی</title>
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

        <div class="grade">       
            <div class="container">
                <div class="information">

                    <div class="row">

                        <div class="col-md-4">
                            <ul>
                                <li>شماره دانشجو : ${sessionScope.username}</li>
                                <li>نام و نام خانوادگی : ${name}</li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <img src="http://localhost:8084/MVCSesssion3/resources/image/preview.png" />
                        </div>
                    </div>
                    <div class="row">

                        <script>
                            $(document).ready(function() {
                                $(".gradebtn").click(function(e) {
                                    var semester = $(this).parent().parent().children('#term0').html();
                                    
                                    e.preventDefault();
                                    $.ajax({
                                        type: "post",
                                        data: {semester: semester},
                                        url: "/MVCSesssion3/login/semesterGrades",
                                        dataType: 'json',
                                        success: function(response) {
                                            $('.results').empty();
                                            $.each(response, function(i, val) {
                                                $('.results').append('<tr id="addr' + (i) + '"></tr>');
                                                $('#addr' + i).html("<td>"+i+"</td><td>"+val[0]+"</td><td>"+val[1]+"</td><td>"+val[2]+"</td><td>"+val[3]+"</td><td>"+val[4]+"</td><td>"+val[5]+"</td>");
                                            })
                                        },
                                        error: function(e) {
                                            alert('Error: ' + e);
                                        }
                                    });
                                });
                            });
                        </script>

                        <table id="tab_logic">
                            <caption class="text-center">اطلاعات جامع ترم</caption>

                            <thead>
                                <tr>
                                    <th style=""> ردیف</th>
                                    <th style=""> ترم</th>
                                    <th style="">واحد</th>
                                    <th style="">معدل</th>
                                    <th style="">مشاهده</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${studentInfo}" var="info" varStatus="status">
                                    <tr>
                                        <td>${status.index}</td>
                                        <c:forEach items="${info}" varStatus="ind">
                                            <td id="term${ind.index}"><c:out value="${info[ind.index]}"/></td>
                                        </c:forEach>
                                        <td><button type="submit" class="btn gradebtn" style="padding: 0 10px;font-size: 1em;line-height: 20px;height: 30px;">مشاهده اطلاعات ترم</button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>



                        <div class="row">
                            <table id="tab_logic" class="results">
                                <caption class="text-center">کارنامه ترمی </caption>

                                <tr>
                                    <th>ردیف</th>
                                    <th>نام درس</th>
                                    <th>نام استاد</th>
                                    <th>واحد</th>
                                    <th>نمره</th>
                                    <th>نتیجه نمره</th>
                                    <th>نوع درس</th>
                                </tr>

                            </table>


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