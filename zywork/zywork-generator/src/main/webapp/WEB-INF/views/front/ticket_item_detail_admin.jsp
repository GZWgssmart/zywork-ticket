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
            width: 15%;
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
            width: 75%;
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
    </style>
</head>

<body>
<div id="app">
    <div class="item">
        <div class="img">
            <img :src="ticketItemDetail.headImg">
        </div>
        <div class="detail">
            <p class="title">{{ticketItemDetail.title}}</p>
            <p class="play-time">演出时间：{{ticketItemDetail.playTimeStr}}</p>
            <p class="address">
                演出地点：{{ticketItemDetail.address}}
            </p>
            <p class="unit-price"><strong>现价：￥{{ticketItemDetail.unitPriceC}}起</strong></p>
        </div>
        <p style="clear:both;"></p>
        <div>
            <p class="des">简介：{{ticketItemDetail.description}}</p>
        </div>
        <div class="other-info">
            <h4>其他信息</h4>
            <hr/>
            <p>1、演出地点：赣州市青少年活动中心</p>
            <p>2、客服电话：13607070913（微信同号）</p>
            <p>3、演出开始后不再售票</p>
            <p>4、查询购票订单请点击公众号菜单中的个人中心</p>
            <p>5、本产品有效期至本场演出结束，过期作废</p>
            <p>6、大人小孩一人一票持票进场，2周岁内的小孩免票入场，须提前与客服联系登记</p>
            <p>7、选座购票后，家长须出示个人中心购票订单现场取票。场次一经确定，不予更改，支付出票后不退不换，特此声明</p>
            <p>8、剧场周边停车位较少，请大家尽量环保出行</p>
            <p>9、有任何疑问请拨打客服电话或加客服微信</p>
        </div>
    </div>
    <div class="bottom">
        <a :href="ticketItemDetail.seatUrl" class="weui-btn weui-btn_primary">立即设置已售座位</a>
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
                ticketItemDetail: {}
            },
            created () {
                axios.get('/byjc/tickeitem/one/' + this.itemId).then(response => {
                    this.ticketItemDetail = response.data
                    this.ticketItemDetail.seatUrl = '<%=path%>/ticket-page/ticket-item-detail-time-admin?itemId=' + itemId
                    this.ticketItemDetail.headImg = '/byjc/' + this.ticketItemDetail.headImg
                    this.ticketItemDetail.address = allAddrs[this.ticketItemDetail.address]
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