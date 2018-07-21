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
        <style>
            .iconfont {
                font-size: 24px;
                color: #ccc;
            }

            #app div {
                margin-top: 5px;
            }

            .seat {
                color:#999;
            }

            .seat i {
                display: inline-block;
                margin-right: 3px;
            }

            .pay {
                font-weight: bold;
                color: orange;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <div v-for="(rowData, row) in seats">
                <span class="seat" style="display:inline-block; width: 20px;">{{row + 1}}</span>
                <span class="seat" v-for="(seat, col) in rowData">
                    <i v-if="seat.seat != '0-0' && seat.status == 1" class="iconfont icon-zuowei1" v-on:click="selectSeat(seat, col, $event)"></i>
                    <i v-else-if="seat.seat != '0-0' && seat.status == 2" class="iconfont icon-zuowei1" v-on:click="selectSeat(seat, col, $event)" style="color:#00ff00;"></i>
                    <i v-else-if="seat.seat == '0-0'" class="iconfont icon-zuowei1" style="color:#fff;"></i>
                    <i v-else-if="seat.seat != '0-0' && seat.status == 3" class="iconfont icon-zuowei1" style="color:#ff0000;"></i>
                </span>
            </div>
            <div>
                <p v-if="selectedSeat.length > 0">
                    您已选择<span class="pay">{{ selectedSeatString }}</span>
                </p>
                <p v-else>您还未选座</p>
                <p>
                    共选座<span class="pay">{{ selectedSeat.length }}</span>个，单价：<span class="pay">{{ unitPay }}元，</span>共需支付：<span class="pay">{{ totalPay }}</span>元
                </p>
            </div>
        </div>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.bootcss.com/axios/0.18.0/axios.min.js"></script>
    <script src="https://cdn.bootcss.com/qs/6.5.2/qs.min.js"></script>
    <script src="<%=path%>/static/js/seats.js"></script>
    <script>
        var app = new Vue(
            {
                el: '#app',
                data: {
                    seats: seats2,
                    selectedSeat: [],
                    selectedSeatString: '',
                    totalPay: 0.0,
                    unitPay: 30.99
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
                        this.totalPay = (this.unitPay * this.selectedSeat.length).toFixed(2)
                    }
                }
            }
        );
    </script>
</html>