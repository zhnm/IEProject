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
        <title>اخذ درس</title>
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

        <div class="takecourse">        
            <h2 class="titlenews">اخذ درس</h2>
            <form class="" method="post" action="#">

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-2">
                            <img src="http://localhost:8084/MVCSesssion3/resources/image/preview.png"/>
                        </div> 

                        <div class="col-md-10">


                            <table id="tab_logic" class="results">
                                <caption class="text-center"></caption>
                                <tr>             
                                    <th style="width:15%;">شماره درس</th>
                                    <th style="width:30%;">نام درس</th>
                                    <th style="width:30%;">نام استاد</th>
                                    <th style="width:10%;">واحد</th>
                                    <th style="width:15%;">نوع درس</th>
                                </tr>
                            </table>
                            <button type="submit" class="btn btn-primary">ثبت اطلاعات</button>

                        </div>

                    </div>
                </div>

            </form>

            <script>
                $(document).ready(function() {
                    var j=0;
                    $(".gradebtn").click(function(e) {
                        var pcs = $(this).parent().parent().children('#term0').html();
                        var profName = $(this).parent().parent().children('#term4').html();
                        
                        e.preventDefault();
                        $.ajax({
                            type: "post",
                            data: {pcs: pcs , profName: profName},
                            url: "/MVCSesssion3/login/submitcourse",
                            dataType: 'json',
                            success: function(response) {
                                // we have the response
                                
                                if (response != 0 && response != 1 && response != 2 && response != 3) {
                                     
                                    $.each(response, function(i,val) {
                                         
                                        $('.results').append('<tr id="addr' + (j) + '"></tr>');
                                        $('#addr' + j).html("<td>" + val[0] + "</td><td>" + val[1] + "</td><td>" + val[2] + "</td><td>" + val[3] + "</td><td>" + val[4]);
                                        j=j+1;
                                    })
                                }
                                else {
                                    var errmsg;
                                    if (response == 0) {
                                        errmsg = "پیش نیاز این درس رعایت نشده است";
                                    }
                                    if (response == 1) {
                                        errmsg = "امکان اخذ مجدد درس گذرانده شده وجود ندارد";
                                    }
                                    if (response == 2) {
                                        errmsg = "درس قبلا انتخاب شده است";
                                    }
                                    if (response == 3) {
                                        errmsg = "حداکثر واحد مجاز اخذ شده است";
                                    }
                                    alert(errmsg);
                                }
                            },
                            error: function(e) {
                                alert('Error: ' + e);
                            }
                        });
                    });
                });
            </script>

            <div class="grade">
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
                            <c:forEach items="${allowedCo}" var="allo">
                                <tr>
                                    <c:forEach items="${allo}" var="all" varStatus="ind">
                                        <td id="term${ind.index}"><c:out value="${all}"/></td>
                                    </c:forEach>
                                    <td><button type="submit" class="btn gradebtn" style="padding: 0 10px;font-size: 1em;line-height: 20px;height: 30px;">اخذ درس</button></td>
                                </tr>
                            </c:forEach>
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