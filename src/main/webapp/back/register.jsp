<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/assets/bootstrap/css/bootstrap.min.css">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/goeasy-1.0.3.js"></script>

    <script>
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/addUser",
                    type: "POST",
                    data: $("#form").serialize(),
                    //dataType:"json",
                    success: function (result) {
                    }
                })
            })
        })
    </script>

</head>
<body>
<div class="container-fluid" style="margin-top: 50px">

    <div class="col-md-4">

    </div>

    <div class="col-md-4">
        <form id="form">
            <div class="form-group">
                <label for="name">用户名</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="用户名">
            </div>
            <div class="form-group">
                <label for="province">省份</label>
                <input type="text" class="form-control" id="province" name="province" placeholder="省份">
            </div>
            <div class="form-group">
                <label for="sex">省份</label>
                <select class="form-control" id="sex" name="sex">
                    <option value="y">男</option>
                    <option value="n">女</option>
                </select>
            </div>

            <button type="submit" id="btn" class="btn btn-default">注册</button>
        </form>

    </div>

    <div class="col-md-4">

    </div>

</div>

</body>
</html>
