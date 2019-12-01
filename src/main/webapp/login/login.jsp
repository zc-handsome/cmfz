<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/login/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
          href="${pageContext.request.contextPath}/login/assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/login/assets/js/jquery.backstretch.min.js"></script>
    <script src="${pageContext.request.contextPath}/login/assets/js/scripts.js"></script>
    <script>
        $(function () {
            var u;
            var p;
            var c;
            $("#form-username").blur(function () {
                var username = $(this).val();
                if (username == "") {
                    $("#msgDiv").html("<font color='red'>用户名不能为空</font>")
                } else {
                    $("#msgDiv").html("<font color='red'></font>")
                    u = true;
                }
            })

            $("#form-password").blur(function () {
                var password = $(this).val();
                if (password == "") {
                    $("#msgDiv").html("<font color='red'>密码不能为空</font>")
                } else {
                    $("#msgDiv").html("<font color='red'></font>")
                    p = true;
                }
            })

            $("#form-sms").blur(function () {
                var code = $(this).val();
                if (code == "") {
                    $("#msgDiv").html("<font color='red'>验证码不能为空</font>")
                } else {
                    $("#msgDiv").html("<font color='red'></font>")
                    c = true;
                }
            })

            $("#loginButtonId").click(function () {
                if (u && p && c) {
                    var name = $("#form-username").val();
                    var pwd = $("#form-password").val();
                    var code = $("#form-sms").val();
                    // alert(name+"  "+pwd)
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/login",
                        type: "POST",
                        data: {
                            "name": name,
                            "pwd": pwd,
                            "code": code
                        },
                        //dataType:"json",
                        success: function (data) {
                            alert(data)
                            if (data == "success") {
                                location.href = "${pageContext.request.contextPath}/back/main.jsp";
                            } else {
                                $("#msgDiv").html("<font color='red'>data</font>")
                                location.href = "${pageContext.request.contextPath}/login/login.jsp";
                            }
                        }
                    })
                }
            })


            $("#captchaImage").click(function () {
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/sms/getCode?t=" + new Date());
            })

            $("#a").click(function () {
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/sms/getCode?t=" + new Date());
            })

        })
    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your username and password to log on:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <!-- <form role="form" action="" method="post" class="login-form" id="loginForm">-->
                        <span id="msgDiv"></span>
                        <div class="form-group">
                            <label class="sr-only" for="form-username">Username</label>
                            <input type="text" name="name" placeholder="请输入用户名..."
                                   class="form-username form-control" id="form-username">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="form-password">Password</label>
                            <input type="password" name="pwd" placeholder="请输入密码..."
                                   class="form-password form-control" id="form-password">
                        </div>
                        <div class="form-group">
                            <img id="captchaImage" style="height: 50px;width: 86px" class="captchaImage"
                                 src="${pageContext.request.contextPath}/code/getCode">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
                                style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                type="test" name="enCode" id="form-code">
                            <br>
                            <a href="#" id="a">点击？换张图</a>
                        </div>
                        <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;"
                               id="loginButtonId" value="登录">
                        <!--</form>-->
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>