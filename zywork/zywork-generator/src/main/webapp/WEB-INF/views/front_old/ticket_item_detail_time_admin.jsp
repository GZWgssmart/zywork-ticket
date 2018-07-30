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
    <link href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css" rel="stylesheet"/>
    <style>
        .item {
            background-color:#fff;
            width: 98%;
            margin: 5px 5px 20px 5px;
            padding: 5px;
        }

        .img {
            float: left;
            width: 30%;
        }

        .img img {
            width: 100%;
            height: auto;
        }

        .img a {
            font-size: 14px;
        }

        .detail {
            padding: 0 10px;
            float: left;
            width: 60%;
        }

        .detail a {
            display: block;
        }

        .title {
            font-size: 14px;
            font-weight: bold;
            color: #ff7700;
        }

        .des, .address, .play-time, .price {
            font-size: 12px;
            color: #9d9d9d;
        }

        .unit-price {
            font-size: 14px;
            color: #ff7700;
        }

        .bottom {
            width: 100%;
            height: 45px;
            line-height:45px;
            position: fixed;
            left: 0;
            bottom: 0;
            font-size: 16px;
            z-index: 100;
        }

        .other-info {
            margin-top: 15px;
            color:#9d9d9d;
            font-size: 12px;
            padding-right: 5px;
        }

        .time {
            background-color:#fff;
            width: 98%;
            height: 30px;
            line-height:30px;
            margin: 5px;
            padding: 5px;
            color:#ff7700;
            font-size: 14px;
        }

        h4 {
            color:#ff7700;
            font-size: 14px;
            margin-top: 30px;
        }

        .time a {
            display: inline-block;
            margin-left: 100px;
            padding:0 5px;
            background-color: #ff7700;
            border-radius: 10px;
            color: white;
        }

    </style>
</head>

<body>
<div id="app">
    <div class="img">
        <img :src="ticketItemDetail.headImg">
    </div>
    <div class="detail">
        <p class="title">{{ticketItemDetail.title}}</p>
        <p class="play-time">演出时间：{{ticketItemDetail.playTimeStr}}</p>
        <p class="address">
            演出地点：{{ticketItemDetail.addressStr}}
        </p>
        <p class="unit-price"><strong>现价：￥{{ticketItemDetail.unitPriceC}}起</strong></p>
    </div>
    <p style="clear:both;"></p>
    <h4>请选择演出时段</h4>
    <hr/>
    <div class="time" v-for="(item, index) in allTimes">
        {{item}}
        <a href="javascript:;" @click="toSelectSeat(item)">选座购票</a>
    </div>
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
<script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
<script src="<%=path%>/static/js/datetime.js"></script>
<script src="<%=path%>/static/js/address.js"></script>
<script>
    var itemId = '${requestScope.itemId}'
    var app = new Vue(
        {
            el: '#app',
            data: {
                itemId: itemId,
                ticketItemDetail: {},
                allTimes: []
            },
            created () {
                axios.get('/byjc/tickeitem/one/' + this.itemId).then(response => {
                    this.ticketItemDetail = response.data
                    this.ticketItemDetail.headImg = '/byjc/' + this.ticketItemDetail.headImg
                    this.ticketItemDetail.addressStr = allAddrs[this.ticketItemDetail.address]
                    this.allTimes = response.data.playTimeStr.split(';')
                }).catch(error => {
                    console.log(error)
                })
            },
            methods: {
                toSelectSeat (time) {
                    var currentTime = new Date()
                    if (currentTime >= strToTimestamp(time)) {
                        alert('演出已开始，无需设置已售座位')
                    } else {
                        window.location.href = '<%=path%>/ticket-page/seat-admin?itemId=' + itemId + '&time=' + time + '&address=' + this.ticketItemDetail.address
                    }
                }
            }
        }
    );
</script>
</html>