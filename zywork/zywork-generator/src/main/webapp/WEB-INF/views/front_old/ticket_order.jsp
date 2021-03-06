<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>

<html>
<head>
    <title>我的购票</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
    <link href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css" rel="stylesheet"/>
    <style>
        .order {
            padding: 5px;
            background-color: #fff;
            margin-bottom: 10px;
        }

        .order img {
            width:20%;
            height: auto;
            float: left;
            margin-right: 10px;
        }

        .detail {
            width: 70%;
            float: left;
        }

        .title {
            font-size: 14px;
            font-weight: bold;
            color: #ff7700;
        }

        .order-time, .play-time, .total-seat, .all-seats {
            font-size: 12px;
            color: #9d9d9d;
        }

        .all-seats {
            color: #cc0000;
            font-weight:bold;
        }

        .unit-price {
            font-size: 14px;
            color: #ff7700;
        }
    </style>
</head>

<body>
<div id="app">
    <div v-if="ticketOrders.length > 0">
        <div v-for="(item, index) in ticketOrders" class="order">
            <img :src="item.ticketItemHeadImg"/>
            <div class="detail">
                <p class="title">{{item.ticketItemTitle}}</p>
                <p class="order-time">订单号：{{item.ticketOrderOrderNo}}</p>
                <p class="play-time">演出时间：{{item.ticketOrderPlayTimeStr}}</p>
                <p class="order-time">购票时间：{{item.ticketOrderOrderTime}}</p>
                <p class="total-seat">总座位数：{{item.ticketOrderTotalSeat}}</p>
                <p class="unit-price">总价：{{item.ticketOrderTotalPrice}}</p>
                <p class="all-seats">座位信息：{{item.allSeatsString}}</p>
            </div>
            <p style="clear:both;"></p>
        </div>
    </div>
    <div v-else class="title" style="text-align:center;">您暂无购票订单，去公众号菜单我要购票中选座购票吧</div>
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
<script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
<script src="<%=path%>/static/js/datetime.js"></script>
<script>
    var openid = '${requestScope.openid}'
    var app = new Vue(
        {
            el: '#app',
            data: {
                ticketOrders:[],
                allSeats:[]
            },
            created () {
                axios.post(
                    '/byjc/user-order/pager-cond',
                    Qs.stringify(
                        {
                            offset: 0,
                            limit: 100,
                            ticketOrderOpenid: openid
                        }
                    )
                ).then(response => {
                    this.ticketOrders = response.data.rows
                    this.ticketOrders.forEach((item, index) => {
                        item.ticketOrderOrderTime = timestampToDatetime(item.ticketOrderOrderTime)
                        item.ticketItemHeadImg = '/byjc/' + item.ticketItemHeadImg
                    })

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