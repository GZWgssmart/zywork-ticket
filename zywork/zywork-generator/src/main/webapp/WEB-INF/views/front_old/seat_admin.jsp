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
                margin-top: 70px;
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
                font-size: 12px;
            }

            .footer .bottom {
                width: 100%;
                height: 40px;
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
                height: 40px;
                line-height:40px;
                font-size: 16px;
                float: left;
            }

            .to-pay {
                float: right;
            }

            .to-pay a {
                width: 160px;
                height: 40px;
                font-size: 16px;
                line-height:40px;
            }

            .area {
                font-size: 12px;
            }

            .area-chg {
                margin-bottom:5px;
                font-size: 12px;
            }

            .area-chg a {
                display: inline-block;
                font-size: 16px;
                margin-right: 10px;
                background-color:#ff7700;
                color: #fff;
                border-radius:10px;
                padding: 0 5px;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <div style="position: fixed; top:0px;left:5px;">
                <div class="area">
                    <i class="iconfont icon-zuowei1" style="font-size: 18px;color:#00ff00;"></i>已选
                    <i class="iconfont icon-zuowei1" style="font-size: 18px;color:#ff0000;"></i>已售
                    <i class="iconfont icon-zuowei1" style="font-size: 18px;color:#dddddd;"></i>A区{{currentItem.unitPrice}}元
                    <i class="iconfont icon-zuowei1" style="font-size: 18px;color:#ADD7F0;"></i>B区{{currentItem.unitPriceB}}元
                    <i class="iconfont icon-zuowei1" style="font-size: 18px;color:#e4b9c0;"></i>C区{{currentItem.unitPriceC}}元
                </div>
                <div class="area-chg">
                    <a v-for="(item, index) in areas" href="javascript:;" @click="changeArea(index)">{{item}}</a>
                    {{time}}
                    <br/>
                    座位可上下左右移动哦
                </div>
            </div>
            <div class="seats">
                <div class="left">
                    <div v-for="(rowData, row) in seats" class="seat-row">
                        <span class="num">{{row + 1}}</span>
                    </div>
                </div>
                <div class="right">
                    <div v-for="(rowData, row) in seats" class="seat-row">
                            <span class="seat" v-for="(seat, col) in rowData">
                                <i v-if="seat.seat != '0-0' && seat.status == 1 && seat.area == 'A'" class="iconfont icon-zuowei1" v-on:click="selectSeat(seat, col, $event)" style="color:#dddddd;"></i>
                                <i v-else-if="seat.seat != '0-0' && seat.status == 1 && seat.area == 'B'" class="iconfont icon-zuowei1" v-on:click="selectSeat(seat, col, $event)" style="color:#ADD7F0;"></i>
                                <i v-else-if="seat.seat != '0-0' && seat.status == 1 && seat.area == 'C'" class="iconfont icon-zuowei1" v-on:click="selectSeat(seat, col, $event)" style="color:#e4b9c0;"></i>
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
                        共选座<span class="pay">{{ selectedSeat.length }}</span>个&nbsp;
                    </p>
                </div>
                <div class="bottom">
                    <div class="to-pay">
                        <a href="javascript:;" class="weui-btn weui-btn_primary" @click="submitOrderAndPay">添加已售</a>
                    </div>
                    <p style="clear:both;"></p>
                </div>
            </div>
        </div>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
    <script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
    <script src="<%=path%>/static/js/seats-${requestScope.address}.js"></script>
    <script>
        var itemId = '${requestScope.itemId}'
        var openid = '${requestScope.openid}'
        var time = '${requestScope.time}'
        var app = new Vue(
            {
                el: '#app',
                data: {
                    itemId: itemId,
                    areas: [],
                    seats: allSeats[0].seats,
                    selectedSeat: [],
                    selectedSeatString: '',
                    currentItem: {},
                    openid: openid,
                    time: time,
                    allSelectedSeats: [],
                    presentTotalNum: 7,
                    presentNum: 1
                },
                created () {
                    allSeats.forEach((item, index) => {
                        this.areas.push(item.floor + '楼' + item.areaStr + '区')
                    })
                    axios.get('/byjc/tickeitem/one/' + this.itemId).then(response => {
                        this.currentItem = response.data
                    }).catch (error => {
                        console.log(error)
                    })
                    axios.get('/byjc/tickeorder-detail/selected-seats/' + this.itemId + '/' + this.time).then(response => {
                        this.allSelectedSeats = response.data
                        this.changeArea(0)
                    }).catch(error => {
                        console.log(error)
                    })
                },
                methods: {
                    changeArea (floor) {
                        this.seats = allSeats[floor].seats
                        var areas = allSeats[floor].areas
                        // 对所有选中的座位进行循环
                        for (var k = 0; k < this.allSelectedSeats.length; k++) {
                            var isSetStatus = false
                            // 对原始座位进行二维数组的循环
                            for (var i = 0; i < this.seats.length; i++) {
                                for (var j = 0; j < this.seats[i].length; j++) {
                                    // 对区域进行循环
                                    for (var m = 0; m < areas.length; m++) {
                                        if (areas[m] + '-' + this.seats[i][j].seat == this.allSelectedSeats[k].seat) {
                                            this.seats[i][j].status = 3
                                            isSetStatus = true
                                            break
                                        }
                                    }
                                    if (isSetStatus) {
                                        break
                                    }
                                }
                                if (isSetStatus) {
                                    break
                                }
                            }
                        }

                    },
                    selectSeat (seat, col, event) {
                        if (seat.status == 1) {
                            event.currentTarget.style.color = '#00ff00'
                            seat.status = 2
                            // 放入已选中的座位
                            this.selectedSeat.push(seat)
                        } else if (seat.status == 2){
                            if (seat.area == 'A') {
                                event.currentTarget.style.color = '#dddddd'
                            } else if (seat.area == 'B') {
                                event.currentTarget.style.color = '#ADD7F0'
                            } else if (seat.area == 'C') {
                                event.currentTarget.style.color = '#e4b9c0'
                            }
                            seat.status = 1
                            // 从已选中的座位中移除
                            this.selectedSeat.forEach((element, index) => {
                                if (element.seat == seat.seat) {
                                    this.selectedSeat.splice(index, 1)
                                }
                            });
                        }
                        this.selectedSeatString = ''
                        this.selectedSeat.forEach((element, index) => {
                            this.selectedSeatString += element.area + '区' + element.seat.split('-')[0] + '排' + element.seat.split('-')[1] + '座\n'
                        })
                    },
                    submitOrderAndPay () {
                            if (this.selectedSeat.length <= 0) {
                                alert('请先选座再设置！')
                                return;
                            }
                            var selectedSeats = ''
                            this.selectedSeat.forEach((item, index) => {
                                if (selectedSeats == '') {
                                    selectedSeats = selectedSeats + item.area + '-' + item.seat
                                } else {
                                    selectedSeats = selectedSeats + ';' +  item.area + '-' + item.seat
                                }
                            })
                            axios.post(
                                '/byjc/tickeorder/save-admin',
                                Qs.stringify({
                                    openid: this.openid,
                                    ticketItemId: this.itemId,
                                    unitPrice: this.currentItem.unitPrice,
                                    totalSeat: this.selectedSeat.length,
                                    allSeatsString: this.selectedSeatString,
                                    selectedSeats: selectedSeats,
                                    playTimeStr: time
                                })
                            ).then(response => {
                                if (response.data.appId == 'none') {
                                    alert('您选择的座位有已经被人购买了，请重新设置已售座位')
                                    window.location.reload()
                                } else {
                                    alert('设置已售座位成功')
                                    window.location.reload()
                                }
                            }).catch(error => {
                                console.log(error)
                            })
                    }
                }
            }
        );
    </script>
</html>