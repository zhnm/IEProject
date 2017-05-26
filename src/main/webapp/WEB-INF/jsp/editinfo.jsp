<%-- 
    Document   : editinfo
    Created on : May 23, 2017, 5:30:26 PM
    Author     : User
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" 
        import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        
<!--        <link rel="icon" type="image/png" href="./resources/image/favicon.ico">
        <link rel="stylesheet" href="./resources/fonts/font-awesome/css/font-awesome.min.css">
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="./resources/css/bootstrapRTL.min.css" rel="stylesheet">
        <link rel='stylesheet' href="./resources/css/style.css" type='text/css' />
        <script type="text/javascript" src="./resources/js/jquery-3.2.0.min.js"></script>
        <script  type="text/javascript"  src="./resources/js/script.js"></script>-->

        <link rel="icon" type="image/png" href="<c:url value = "/resources/image/favicon.ico"/>">
        <link rel="stylesheet" href="<c:url value = "/resources/fonts/font-awesome/css/font-awesome.min.css"/>">
        <link href="<c:url value = "/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value = "/resources/css/bootstrapRTL.min.css"/>" rel="stylesheet">
        <link rel='stylesheet' href="<c:url value = "/resources/css/style.css"/>" type='text/css' />
        <script type="text/javascript" src="<c:url value = "/resources/js/jquery-3.2.0.min.js"/>"></script>
        <script  type="text/javascript"  src="<c:url value = "/resources/js/script.js"/>"></script>
        
        <title>ویرایش اطلاعات</title>
    </head>
    
    <body>

    <div class="menu">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <img src="<c:url value = "/resources/image/logo.jpg"/>" />
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
<div class="edit">        
<div class="container">
    <h2 class="titlenews">ویرایش اطلاعات</h2>
    <h1 id="random">YOUR NAME IS : ${sessionScope.username}</h1>
    <h1>${massage}</h1>
    <form class="" method="post" action="<c:url value = "/login/save" context="/MVCSesssion3"/>" >
    <!--name-->
        <div class="form-group row">
            <label for="name" class="control-label">نام :</label>
            <div class="">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                    <input type="text" class="form-control" name="name" id="name"  placeholder=""/>
                </div>
            </div>
        </div>
    <!--family-->  
        <div class="form-group row">
            <label for="name" class="control-label">نام خانوادگی :</label>
            <div class="">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                    <input type="text" class="form-control" name="surname" id="name"  placeholder=""/>
                </div>
            </div>
        </div>
    <!--phone-->  
        <div class="form-group row">
            <label for="phone" class="control-label">تلفن همراه :</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-mobile" aria-hidden="true"></i></span>
                    <input type="text" class="form-control" name="telephone" id="name"  placeholder=""/>
                </div>
        </div>
     <!--email-->  
        <div class="form-group row">
            <label for="email" class="control-label">پست الکترونیک :</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope" aria-hidden="true"></i></span>
                    <input type="text" class="form-control" name="emailAdd" id="email"  placeholder=""/>
                </div>
        </div>  
    <!--password-->    
    <div class="form-group row">
        <label for="password" class="control-label">رمز عبور</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                <input type="password" class="form-control" name="password" id="password"  placeholder=""/>
            </div>
    </div>    
    <!--re-password-->    
    <div class="form-group row">
        <label for="confirm" class="control-label">تکرار رمز عبور</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                <input type="password" class="form-control" name="confirm" id="confirm"  placeholder=""/>
            </div>
    </div>
         <button type="submit" class="btn btn-primary">ثبت اطلاعات</button>
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
