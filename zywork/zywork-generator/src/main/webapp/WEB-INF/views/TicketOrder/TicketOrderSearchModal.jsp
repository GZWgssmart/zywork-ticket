<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">选择</h4>
</div>
<div class="modal-body">
    <table id="tickeorder-data-list" data-classes="table table-hover text-nowrap" data-toolbar="#tickeorder-toolbar"></table>

    <div id="tickeorder-toolbar">
        <button class="btn btn-primary" onclick="">
            <i class="fa fa-key"></i>
            确认
        </button>
        <button class="btn btn-primary" onclick="showSearchForm('tickeorder-search-form')">
            <i class="fa fa-eye"></i>
            高级搜索
        </button>
        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>

        <form id="tickeorder-search-form" class="row form-horizontal search-form" style="display: none;">
            <div class="col-sm-6 form-group">
    <label for="ticketItemIdSearch" class="col-sm-4 control-label">剧目编号：</label>
    <div class="col-sm-8">
        <input id="ticketItemIdSearch" name="ticketItemId" class="form-control" placeholder="请输入剧目编号">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="userIdSearch" class="col-sm-4 control-label">用户编号：</label>
    <div class="col-sm-8">
        <input id="userIdSearch" name="userId" class="form-control" placeholder="请输入用户编号">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="openidSearch" class="col-sm-4 control-label">微信openid：</label>
    <div class="col-sm-8">
        <input id="openidSearch" name="openid" class="form-control" placeholder="请输入微信openid">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="orderNoSearch" class="col-sm-4 control-label">订单编号：</label>
    <div class="col-sm-8">
        <input id="orderNoSearch" name="orderNo" class="form-control" placeholder="请输入订单编号">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="orderTimeStartSearch" class="col-sm-4 control-label">下单时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="orderTimeStartSearch" name="orderTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择下单时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="orderTimeEndSearch" class="col-sm-4 control-label">下单时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="orderTimeEndSearch" name="orderTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择下单时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="unitPriceSearch" class="col-sm-4 control-label">单价：</label>
    <div class="col-sm-8">
        <input id="unitPriceSearch" name="unitPrice" class="form-control" placeholder="请输入单价">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="totalSeatSearch" class="col-sm-4 control-label">总座位数：</label>
    <div class="col-sm-8">
        <input id="totalSeatSearch" name="totalSeat" class="form-control" placeholder="请输入总座位数">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="totalPriceSearch" class="col-sm-4 control-label">支付总额：</label>
    <div class="col-sm-8">
        <input id="totalPriceSearch" name="totalPrice" class="form-control" placeholder="请输入支付总额">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="createTimeStartSearch" class="col-sm-4 control-label">创建时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="createTimeStartSearch" name="createTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择创建时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="createTimeEndSearch" class="col-sm-4 control-label">创建时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="createTimeEndSearch" name="createTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择创建时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="updateTimeStartSearch" class="col-sm-4 control-label">更新时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="updateTimeStartSearch" name="updateTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择更新时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="updateTimeEndSearch" class="col-sm-4 control-label">更新时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="updateTimeEndSearch" name="updateTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择更新时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>



            <div class="col-sm-6 form-group text-right" style="padding-right: 30px;">
                <button type="button" class="btn btn-primary" onclick="doSearch('tickeorder-data-list')">
                    <i class="fa fa-search"></i>
                    搜索
                </button>
                <button type="button" class="btn btn-primary" onclick="doSearchAll('tickeorder-data-list', 'tickeorder-search-form')">
                    <i class="fa fa-search"></i>
                    搜索所有
                </button>
                <button type="button" class="btn btn-primary" onclick="hideSearchForm('tickeorder-data-list', 'tickeorder-search-form')">
                    <i class="fa fa-eye-slash"></i>
                    取消搜索
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    initDatetime();
</script>
