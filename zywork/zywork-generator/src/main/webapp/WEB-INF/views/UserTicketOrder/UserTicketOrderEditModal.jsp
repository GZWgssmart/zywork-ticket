<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">编辑</h4>
</div>
<div class="modal-body">
    <form id="edit-form" class="form-horizontal row" enctype="multipart/form-data">
        <input type="hidden" name="ticketOrderId"/>
        <div class="form-group">
    <label for="ticketOrderTicketItemIdEdit" class="col-sm-2 control-label">剧目编号：</label>
    <div class="col-sm-10">
        <input id="ticketOrderTicketItemIdEdit" name="ticketOrderTicketItemId" class="form-control" placeholder="请输入剧目编号"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderOpenidEdit" class="col-sm-2 control-label">微信openid：</label>
    <div class="col-sm-10">
        <input id="ticketOrderOpenidEdit" name="ticketOrderOpenid" class="form-control" placeholder="请输入微信openid"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderOrderNoEdit" class="col-sm-2 control-label">订单编号：</label>
    <div class="col-sm-10">
        <input id="ticketOrderOrderNoEdit" name="ticketOrderOrderNo" class="form-control" placeholder="请输入订单编号"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderOrderTimeEdit" class="col-sm-2 control-label">下单时间：</label>
    <div class="input-group date form_datetime col-sm-10" style="padding-left: 15px; padding-right: 15px;">
        <input id="ticketOrderOrderTimeEdit" name="ticketOrderOrderTime" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择下单时间"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderUnitPriceEdit" class="col-sm-2 control-label">单价：</label>
    <div class="col-sm-10">
        <input id="ticketOrderUnitPriceEdit" name="ticketOrderUnitPrice" class="form-control" placeholder="请输入单价"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderTotalSeatEdit" class="col-sm-2 control-label">总座位数：</label>
    <div class="col-sm-10">
        <input id="ticketOrderTotalSeatEdit" name="ticketOrderTotalSeat" class="form-control" placeholder="请输入总座位数"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderTotalPriceEdit" class="col-sm-2 control-label">支付总额：</label>
    <div class="col-sm-10">
        <input id="ticketOrderTotalPriceEdit" name="ticketOrderTotalPrice" class="form-control" placeholder="请输入支付总额"/>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-edit" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-edit', 'edit-modal', 'edit-form', '/user-order/update', 'data-list', '/user-order/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
