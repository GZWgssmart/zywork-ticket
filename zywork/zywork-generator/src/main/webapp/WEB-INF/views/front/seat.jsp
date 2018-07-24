<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>

<html>
    <head>
        <title>在线选座</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
        <link href="<%=path%>/static/css/iconfont/iconfont.css" rel="stylesheet"/>
        <link href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css" rel="stylesheet"/>
        <style>
            .iconfont {
                font-size: 24px;
                color: #ccc;
            }

            .seats {
                width: 1300px;
                height: 300px;
                margin-top: 10px;
                overflow: scroll;
            }

            .seats .left {
                width: 20px;
                float: left;
                background-color: rgba(0, 0, 255, 0.5);
                color: #fff;
                padding-left: 5px;
            }

            .seats .right {
                margin-left: 5px;
                float: left;
                border: 1px dashed rgba(0, 0, 255, 0.5);
            }

            .seat-row {
                height: 30px;
            }

            .seat-row .num {
                display: inline-block;
                margin-top:8px;
            }

            .seat {
                color:#999;
            }

            .seat i {
                display: inline-block;
                margin-right: 3px;
            }

            .footer {
                font-size: 14px;
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
            }

            .footer .content {
                padding-left:5px;
                padding-right: 5px;
            }

            .footer .bottom {
                width: 100%;
                height: 60px;
                color: #fff;
                background-color: rgba(26, 173, 25, 0.6);
                padding-left: 5px;
                padding-right:5px;
            }

            .pay {
                font-weight: bold;
                color: #ff7700;
            }

            .total-pay {
                height: 60px;
                line-height:60px;
                font-size: 16px;
                float: left;
            }

            .to-pay {
                float: right;
            }

            .to-pay a {
                width: 160px;
                height: 60px;
                font-size: 16px;
                line-height:60px;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <div class="seats">
                <div class="left">
                    <div v-for="(rowData, row) in seats" class="seat-row">
                        <span class="num">{{row + 1}}</span>
                    </div>
                </div>
                <div class="right">
                    <div v-for="(rowData, row) in seats" class="seat-row">
                            <span class="seat" v-for="(seat, col) in rowData">
                                <i v-if="seat.seat != '0-0' && seat.status == 1" class="iconfont icon-zuowei1" v-on:click="selectSeat(seat, col, $event)"></i>
                                <i v-else-if="seat.seat != '0-0' && seat.status == 2" class="iconfont icon-zuowei1" v-on:click="selectSeat(seat, col, $event)" style="color:#00ff00;"></i>
                                <i v-else-if="seat.seat == '0-0'" class="iconfont icon-zuowei1" style="color:rgba(0,0,0,0);"></i>
                                <i v-else-if="seat.seat != '0-0' && seat.status == 3" class="iconfont icon-zuowei1" style="color:#ff0000;"></i>
                            </span>
                    </div>
                </div>
                <p style="clear:both;"></p>
            </div>
            <div class="footer">
                <div class="content">
                    <h3>{{currentItem.title}}</h3>
                    <p v-if="selectedSeat.length > 0">
                        您已选择<span class="pay">{{ selectedSeatString }}</span>
                    </p>
                    <p v-else>您还未选座</p>
                    <p>
                        共选座<span class="pay">{{ selectedSeat.length }}</span>个，单价：<span class="pay">{{ currentItem.unitPrice }}元</span>
                    </p>
                </div>
                <div class="bottom">
                    <div class="total-pay">
                        共需支付：<span class="pay">{{ totalPay }}</span>元
                    </div>
                    <div class="to-pay">
                        <a href="javascript:;" class="weui-btn weui-btn_primary" @click="submitOrderAndPay">立即支付</a>
                    </div>
                    <p style="clear:both;"></p>
                </div>
            </div>
        </div>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
    <script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
    <script src="<%=path%>/static/js/seats.js"></script>
    <script>
        var itemId = '${requestScope.itemId}'
        var floor = '${requestScope.floor}'
        var openid = '${requestScope.openid}'
        var app = new Vue(
            {
                el: '#app',
                data: {
                    itemId: itemId,
                    floor: floor,
                    seats: [],
                    selectedSeat: [],
                    selectedSeatString: '',
                    totalPay: 0.0,
                    currentItem: {},
                    openid: openid
                },
                created () {
                    if (this.floor == '1') {
                        this.seats = seats1
                    } else {
                        this.seats = seats2
                    }
                    axios.get('/byjc/tickeitem/one/' + this.itemId).then(response => {
                        this.currentItem = response.data
                    }).catch (error => {
                        console.log(error)
                    })
                },
                methods: {
                    selectSeat (seat, col, event) {
                        if (seat.status == 1) {
                            event.currentTarget.style.color = '#00ff00'
                            seat.status = 2
                            // 放入已选中的座位
                            this.selectedSeat.push(seat)
                        } else if (seat.status == 2){
                            event.currentTarget.style.color = '#ccc'
                            seat.status = 1
                            // 从已选中的座位中移除
                            this.selectedSeat.forEach((element, index) => {
                                if (element.seat == seat.seat) {
                                    this.selectedSeat.splice(index, 1)
                                }
                            });
                        }
                        console.log(this.selectedSeat)
                        this.selectedSeatString = ''
                        this.selectedSeat.forEach((element, index) => {
                            this.selectedSeatString += element.seat.split('-')[0] + '排' + element.seat.split('-')[1] + '座\n'
                        })
                        this.totalPay = (this.currentItem.unitPrice * this.selectedSeat.length).toFixed(2)
                    },
                    submitOrderAndPay () {
                        axios.post(
                            '/byjc/tickeorder/save',
                            Qs.stringify({
                                openid: this.openid,
                                itemId: this.itemId,
                                unitPrice: this.currentItem.unitPrice,
                                totalSeat: this.selectedSeat.length,
                                totalPrice: this.totalPay,
                                allSeatsString: this.selectedSeatString
                            })
                        ).then(response => {
                            this.pay(
                                response.data.appId,
                                response.data.timeStamp,
                                response.data.nonceStr,
                                response.data.packages,
                                response.data.paySign
                            )
                        }).catch(error => {
                            console.log(error)
                        })
                    },
                    pay (appId, timestamp, nonceStr, packages, paySign) {
                        if (typeof WeixinJSBridge == "undefined") {
                            if (document.addEventListener) {
                                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                            } else if (document.attachEvent) {
                                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                            }
                        } else {
                            WeixinJSBridge.invoke(
                                'getBrandWCPayRequest', {
                                    "appId": appId,
                                    "timeStamp": timestamp,
                                    "nonceStr": nonceStr,
                                    "package": packages,
                                    "signType": "MD5",
                                    "paySign": paySign
                                }, function (res) {
                                    if (res.err_msg == "get_brand_wcpay_request:ok") {
                                        alert('您已支付成功');
                                    } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
                                        alert('您已取消支付');
                                    } else if (res.err_msg == "get_brand_wcpay_request:fail") {
                                        alert('您支付失败');
                                    }
                                });
                        }
                    }
                }
            }
        );
    </script>
</html>