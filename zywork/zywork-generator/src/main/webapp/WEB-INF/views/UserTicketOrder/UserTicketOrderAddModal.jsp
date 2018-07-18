<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">添加</h4>
</div>
<div class="modal-body">
    <form id="add-form" class="form-horizontal row" enctype="multipart/form-data">
        <div class="form-group">
    <label for="ticketOrderTicketItemId" class="col-sm-2 control-label">剧目编号：</label>
    <div class="col-sm-10">
        <input id="ticketOrderTicketItemId" name="ticketOrderTicketItemId" class="form-control" placeholder="请输入剧目编号"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderUserId" class="col-sm-2 control-label">用户编号：</label>
    <div class="col-sm-10">
        <input id="ticketOrderUserId" name="ticketOrderUserId" class="form-control" placeholder="请输入用户编号"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderOrderTime" class="col-sm-2 control-label">下单时间：</label>
    <div class="input-group date form_datetime col-sm-10" style="padding-left: 15px; padding-right: 15px;">
        <input id="ticketOrderOrderTime" name="ticketOrderOrderTime" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择下单时间"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderUnitPrice" class="col-sm-2 control-label">单价：</label>
    <div class="col-sm-10">
        <input id="ticketOrderUnitPrice" name="ticketOrderUnitPrice" class="form-control" placeholder="请输入单价"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderTotalSeat" class="col-sm-2 control-label">总座位数：</label>
    <div class="col-sm-10">
        <input id="ticketOrderTotalSeat" name="ticketOrderTotalSeat" class="form-control" placeholder="请输入总座位数"/>
    </div>
</div>

<div class="form-group">
    <label for="ticketOrderTotalPrice" class="col-sm-2 control-label">支付总额：</label>
    <div class="col-sm-10">
        <input id="ticketOrderTotalPrice" name="ticketOrderTotalPrice" class="form-control" placeholder="请输入支付总额"/>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-save" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-save', 'add-modal', 'add-form', '/user-order/save', 'data-list', '/user-order/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
