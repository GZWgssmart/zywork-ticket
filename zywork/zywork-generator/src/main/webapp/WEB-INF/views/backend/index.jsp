<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>北艺赣州剧场后台管理系统</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link href="<%=path%>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/animate.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.css" rel="stylesheet">
    <link href="<%=path%>/static/css/plugins/sweetalert/sweetalert2.min.css" rel="stylesheet"/>

    <style>

        .login {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            height: 40%;
        }

        .bg-img {
            width: 100%;
            height: 100%;
            left: 0px;
            top: 0px;
            position: absolute;
            opacity: 0.5;
        }
    </style>
</head>
<body class="gray-bg">
<div class="login text-center animated fadeInDown">
    <div>
        <h2>北艺赣州剧场后台登录</h2>

        <form id="loginForm" class="m-t" role="form" style="width: 350px;">
            <div class="form-group">
                <input id="account" name="account" class="form-control" placeholder="请输入手机号" required>
            </div>
            <div class="form-group">
                <input id="password" type="password" name="password" class="form-control" placeholder="请输入登录密码" required>
            </div>
            <button type="button" class="btn btn-primary block full-width m-b" onclick="login()">登 录</button>

        </form>
    </div>
</div>
</body>
<!-- 全局js -->
<script src="<%=path%>/static/js/jquery.min.js"></script>
<script src="<%=path%>/static/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=path%>/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=path%>/static/js/plugins/sweetalert/sweetalert2.min.js"></script>
<script src="<%=path%>/static/js/plugins/md5/md5.min.js"></script>
<script>

    function login() {
        $.post('<%=path%>/user/login',
            {
                account: $('#account').val(),
                password: md5.base64($('#password').val())
            },
            function (data) {
                if (data.status === 'ok') {
                    window.location.href = "<%=path%>/backend/home"
                } else {
                    swal('提示', data.message, 'warning');
                }
            }, 'json'
        );
    }

</script>
</html>
