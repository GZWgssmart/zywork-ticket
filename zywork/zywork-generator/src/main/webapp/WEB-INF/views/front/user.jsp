<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>

<html>
    <head>
        <title>个人中心</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
        <link href="<%=path%>/static/css/iconfont/iconfont.css" rel="stylesheet"/>
        <link href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css" rel="stylesheet"/>
        <style>
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
        </style>
    </head>

    <body>
    <div id="app">
        <div class="user-info">
            <img :src="user.headicon"/>
            <br/>
            <span>{{user.nickname}}</span>
        </div>
        <div class="weui-cells">

            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__hd"><i class="iconfont icon-icon"></i></div>
                <div class="weui-cell__bd">
                    <p>手机号</p>
                </div>
                <div class="weui-cell__ft">{{user.phone}}</div>
            </a>
            <a class="weui-cell weui-cell_access" href="<%=path%>/ticket-page/ticket-order/${requestScope.user.openid}">
                <div class="weui-cell__hd"><i class="iconfont icon-icon-"></i></div>
                <div class="weui-cell__bd">
                    <p>我的购票订单</p>
                </div>
                <div class="weui-cell__ft"></div>
            </a>
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
        var openid = '${requestScope.user.openid}'
        var app = new Vue(
            {
                el: '#app',
                data: {
                    user: {
                        userid: userid,
                        nickname: nickname,
                        headicon: headicon,
                        phone: phone,
                        openid: openid
                    }
                },
                methods: {

                }
            }
        );
    </script>
</html>