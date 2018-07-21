<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>

<html>
    <head>
        <title>我的</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
        <link href="<%=path%>/static/css/iconfont/iconfont.css" rel="stylesheet"/>
        <style>
            * {
                margin: 0;
                padding: 0;
            }
            .user-info {
                width: 100%;
                text-align: center;
                margin-top: 50px;
            }

            .user-info img {
                width: 50px;
                height: 50px;
                -webkit-border-radius: 25px;
                -moz-border-radius: 25px;
                border-radius: 25px;
            }

            .user-info span {
                font-size: 16px;
                color: #2A2E36;
            }

            .ticket-order {
                width: 100%;
                padding: 5px;
                margin-top: 50px;
            }

            .ticket-order ul li {
                height: 40px;
                line-height:40px;
                padding: 5px;
                border-bottom: 1px solid #cccccc;
                list-style: none;
            }

            .ticket-order li a {
                display: block;
            }

            .ticket-order li a:link, .ticket-order li a:visited {
                color: #ff7700;
                text-decoration: none;
            }
        </style>
    </head>

    <body>
    <div id="app">
        <div class="user-info">
            <img :src="user.headicon"/>
            <br/>
            <span>{{user.nickname}}</span>
        </div>
        <div class="ticket-order">
            <ul>
                <li>
                    <a href="#">
                        <span><i class="iconfont icon-icon"></i>&nbsp;手机号</span>
                        <span><small>{{user.phone}}</small></span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span><i class="iconfont icon-icon-"></i>&nbsp;我的购票订单</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
    <script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
    <script>
        var userid = '${requestScope.user.id}'
        var nickname = '${requestScope.user.nickname}'
        var headicon = '${requestScope.user.headicon}'
        var phone = '${requestScope.user.phone}'
        var app = new Vue(
            {
                el: '#app',
                data: {
                    user: {
                        userid: userid,
                        nickname: nickname,
                        headicon: headicon,
                        phone: phone
                    }
                },
                methods: {

                }
            }
        );
    </script>
</html>