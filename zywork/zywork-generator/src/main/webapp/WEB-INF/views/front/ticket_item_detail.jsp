<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>

<html>
<head>
    <title>剧目详情</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
    <link href="<%=path%>/static/css/iconfont/iconfont.css" rel="stylesheet"/>
    <style>
    </style>
</head>

<body>
<div id="app">
    {{ticketItemDetail.title}}
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
<script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
<script>
    var app = new Vue(
        {
            el: '#app',
            data: {
                ticketItemDetail: {}
            },
            created () {
                axios.get(
                    '/tickeitem/one/1'
                ).then(response => {
                    this.ticketItemDetail = response.data
                }).catch(error => {
                    console.log(error)
                })
            },
            methods: {

            }
        }
    );
</script>
</html>