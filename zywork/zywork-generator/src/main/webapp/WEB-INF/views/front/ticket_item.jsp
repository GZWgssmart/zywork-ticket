<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>

<html>
<head>
    <title>剧目列表</title>
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
        }

        .img img {
            width: 120px;
            height: 150px;
        }

        .img a {
            font-size: 14px;
        }

        .detail {
            padding: 0 10px;
            float: left;
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

        .pager {
            text-align: center;
        }

        .pager a {
            width: 150px;
            font-size: 14px;
            display: inline-block;
        }
    </style>
</head>

<body>
<div id="app">
    <div v-for="(item, index) in ticketItems" class="item">
        <div class="img">
            <a :href="item.itemDetailUrl">
                <img :src="item.headImg">
            </a>
            <br/>
            <a v-if="item.canSelectSeat" :href="item.seatUrl" class="weui-btn weui-btn_plain-primary">选座购票</a>
            <span v-else class="des" style="color:#ff7700; text-align:center;">
                马上开演或已演出<br/>
                无法购票
            </span>
        </div>
        <div class="detail">
            <a :href="item.itemDetailUrl">
                <p class="title">{{item.title}}</p>
                <p class="des">简介：{{item.description}}</p>
                <p class="play-time">放映时间：{{item.playTime}}</p>
                <p class="address">
                    放映地点：
                    <span v-if="item.address == 1">剧场1楼</span>
                    <span v-else>剧场2楼</span>
                </p>
                <p class="price"><small>原价：￥{{item.price}}</small></p>
                <p class="unit-price"><strong>现价：￥{{item.unitPrice}}</strong></p>
            </a>
        </div>
        <p style="clear:both;"></p>
    </div>
    <!--
    <div class="pager">
        <a href="#" class="weui-btn weui-btn_plain-primary">上一页</a>
        <a href="#" class="weui-btn weui-btn_plain-primary">下一页</a>
    </div>
    -->
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
<script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
<script src="<%=path%>/static/js/datetime.js"></script>
<script>
    var openid = '${requestScope.user.openid}'
    var app = new Vue(
        {
            el: '#app',
            data: {
                ticketItems: [],
                pager: {
                    offset: 0,
                    limit: 100
                }
            },
            created () {
                this.getItems()
            },
            methods: {
                getItems () {
                    var currentTime = new Date().getTime()
                    axios.post(
                        '/byjc/tickeitem/pager',
                        Qs.stringify(this.pager)
                    ).then(response => {
                        this.ticketItems = response.data.rows
                        this.ticketItems.forEach((data, index) =>{
                                if (data.playTime - currentTime >= 60 * 60 * 1000) {
                                    data.canSelectSeat = true
                                } else {
                                    data.canSelectSeat = false
                                }
                                data.playTime = timestampToDatetime(data.playTime)
                                data.itemDetailUrl = '<%=path%>/ticket-page/ticket-item-detail/' + data.id + '/' + openid
                                data.seatUrl = '<%=path%>/ticket-page/seat?itemId=' + data.id + '&floor=' + data.address + '&openid=' + openid
                                data.headImg = '/byjc/' + data.headImg
                            }
                        )
                    }).catch(error => {
                        console.log(error)
                    });
                }
            }
        }
    );
</script>
</html>