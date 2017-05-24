<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" 
        import="java.sql.*" %>

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
        <title>به سیستم مدیریت آموزش خوش آمدید</title>
    </head>

    <body>
        <div class="menu">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="./resources/image/logo.jpg" />
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

        <div class="slider">

        </div>

        <div class="login">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                            <div class="form-top">
                                <h3>ورود به سامانه مدیریت آموزشی</h3>
                            </div>
                            <div class="form-bottom">
                                <% 
                                    {
                                %>
                                <form role="form" action="<c:url value = "/welcome/login"/>" method="post">
                                    <div class="form-group">
                                        <label class="sr-only" for="form-username">Username</label>
                                        <input type="text" name="form-username" placeholder="شناسه کاربری..." class="form-username form-control" id="username">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-password">Password</label>
                                        <input type="password" name="form-password" placeholder="گذرواژه..." class="form-password form-control" id="password">
                                    </div>
                                    <button type="submit" class="btn">ورود</button>
                                </form>
                                    <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <h2 class="titlenews">اطلاعیه های آموزشی</h2>
        <div class="news">
            <div class="container">
                <div class="row">

                    <div class="col-md-4"> 
                        <div class="newsbox">
                            <h4>
                                پیام تبریک به مناسبت سال نو
                            </h4>
                            <div class="text">
                                از نوروز، روز و روزگاری نو انتظار می رود. و تو که نباشی، بهار، همان پاییز است به رنگی دیگر. <br />
                                معاونت فرهنگی، اجتماعی و دانشجویی ضمن شادباش سال نو به خانواده ارجمند دانشگاه شهیدبهشتی و تلاقی خجسته آن با میلاد گرانقدرترین بانوی عالم، امیدوار است سال پیش­رو سالی سرشار از بهترین و فراخ‏ترین ارزاق مادی و معنوی برای دانشگاهیان محترم باشد و توفیق و تایید الهی، سایبان زندگیشان باشد.

                            </div>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="newsbox">
                            <h4>
                                قابل توجه دانشجویان مهندسی هسته ای
                            </h4>
                            <div class="text">
                                بدینوسیله به اطلاع  دانشجویان رشته مهندسی هسته ای که در نیمسال دوم سال تحصیلی 96-95 از طریق پورتال دانشجویی صندوق رفاه دانشجویان نسبت به ثبت درخواست وام خود اقدام نموده اند می رساند ، هر چه سریعتر نسبت به ارائه گواهی اشتغال به تحصیل خود در نیمسال دوم سال جاری جهت ارسال و ثبت در سامانه صندوق رفاه دانشجویان تا تاریخ 1/12/95 اقدام نمایند ،  در غیر اینصورت تائید برقراری وام این دسته از دانشجویان در نیمسال جاری از سامانه حذف خواهد شد 
                            </div>
                        </div>
                    </div>


                    <div class="col-md-4">
                        <div class="newsbox">
                            <h4>
                                دریافت تاییدیه تحصیلی
                            </h4>  
                            <div class="text">
                                دانشجويان محترم مقاطع تحصيلات تكميلي جهت اطلاع از وضعيت ارسال تاييديه تحصيلي از دانشگاه مبدا به آدرس ذيل مراجعه نماييد:

                                اطلاعات جامع دانشجو >  پرسنلي > در قسمت تحصيلات قبلي روي كلمه دارد كليك نماييد ، اگر در ستون نياز به تاييديه كلمه دارد نمايان است يعني هنوز تاييديه ايشان از دانشگاه مبدا ارسال نشده و در صورت ظاهرشدن كلمه ندارد يعني دانشجو نيازي به پيگيري دريافت تاييديه تحصيلي ندارد

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <h2 class="titlenews">معرفی سامانه</h2>
        <div class="aboutus">
            <div class="container">
                <div class="row">
                    <div class="text">
                        طراحی و پياده سازی سيستم جامع دانشگاهی گلستان با هدف مکانيزه کردن کليه فعاليتهای دانشگاهی از اسفندماه سال 1379 آغاز گرديد . در ابتدا سيستم جامع آموزش گلستان در دانشگاه صنعتی اصفهان و در راستای اجرای مکانيزه کليه امور آموزشی دانشگاه، طراحی و پياده سازی شده است.

                        تجزيه و تحليل، طراحی، پياده سازی، تست و تحويل اين سيستم از اسفند ماه سال 1379 شروع و در اسفندماه 1382 به پايان رسيد. پس از آن درسال 1383 زيرسيستم های ارزشيابی و تسويه حساب دانشجويی و زيرسيستم حق التدريس اساتيد به عنوان زيرسيستم های مکمل سيستم آموزش تهيه شد .

                        در سال 1384 سيستم اطلاعات پژوهشی دانشگاه به عنوان زِيرسيستمی که پوشش دهنده فعاليتهای حوزه معاونت پژوهشی دانشگاه ها است به مجموعه تحت پوشش سيستم گلستان اضافه شد.

                        در سال 1386 سيستم دانشجويی شامل زيرسيستم های خوابگاه، وام، کار دانشجويی و تشکل های دانشجويی به عنوان زيِرسيستمی جديد که پوشش دهنده بخشی از فعاليت های حوزه معاونت دانشجويی است توسط شرکت نوپرداز اصفهان و با همکاری تعدادی از دانشگاه های کشور آماده بهره برداری شده و در بعضی از دانشگاهها مورد استفاده قرار گرفته است.

                        سيستم جامع گلستان حاصل بيش از دويست هزار نفر ساعت کار فشرده کارشناسی اعضا تيم، هزاران ساعت همکاری مديريت و کارشناسان دانشگاههای کشور و تجربه بيش از سی سال فعاليت در بخش های مختلف و طراحی و پياده سازی سيستم های دانشگاهی توسط کارشناسان تيم است که فرايندهای دانشگاهی را با کيفيت و کميت مناسب و کم نظيری پوشش می دهد.

                        پس از نهايی شدن مراحل طراحی و پياده سازی سيستم جامع دانشگاهی گلستان و با توجه به قابليتهای منحصر به فرد آن، سيستم گلستان توسط شرکت نوپرداز اصفهان در دانشگاههای مختلف نصب و راه اندازی گرديد. هم اکنون سيستم گلستان درکليه امور اجرايی تحت پوشش خود در بسياری از دانشگاههای کشور مورد استفاده قرار مي گيرد.

                        شرکت نوپرداز اصفهان مصمم است در جهت افزايش سرويس های قابل ارائه توسط سيستم جامع دانشگاهي گلستان ، اين سيستم را در ساير حوزه های دانشگاهی نيز گسترش دهد.
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
