<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>UserTicketOrder</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <%@include file="../master/include-css.jsp"%>
</head>
<body class="gray-bg">
<div class="row wrapper wrapper-content animated fadeInRight">
    <div class="col-sm-12">
        <table id="data-list" data-classes="table table-hover text-nowrap" data-toolbar="#toolbar"></table>

        <div id="toolbar">
            <button class="btn btn-primary" onclick="showRemoteAddModal('add-modal', '/user-order/add-modal', 'add-form', validateFields())">
                <i class="fa fa-plus"></i>
                添加
            </button>
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fa fa-th-list"></i>
                批量操作&nbsp;<span class="caret"></span>
              </button>
              <ul class="dropdown-menu">
                <li>
                    <a href="javascript:void(0);" onclick="batchActive('/user-order/batch-active', 0, 'data-list', '/user-order/pager-cond', 'ticketOrderId')">
                        <i class="fa fa-check-square-o text-success"></i><span class="text-success">&nbsp;批量激活</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="batchActive('/user-order/batch-active', 1, 'data-list', '/user-order/pager-cond', 'ticketOrderId')">
                        <i class="fa fa-minus-square-o text-danger"></i><span class="text-danger">&nbsp;批量冻结</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="batchRemove('/user-order/batch-remove', 'data-list', '/user-order/pager-cond', 'ticketOrderId')">
                        <i class="fa fa-remove text-danger"></i><span class="text-danger">&nbsp;批量删除</span>
                    </a>
                </li>
              </ul>
            </div>
            <button class="btn btn-primary" onclick="showSearchForm('search-form')">
                <i class="fa fa-eye"></i>
                高级搜索
            </button>

            <form id="search-form" class="row form-horizontal search-form" style="display: none;">
                <div class="col-sm-6 form-group">
    <label for="ticketOrderTicketItemIdSearch" class="col-sm-4 control-label">剧目编号：</label>
    <div class="col-sm-8">
        <input id="ticketOrderTicketItemIdSearch" name="ticketOrderTicketItemId" class="form-control" placeholder="请输入剧目编号">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="ticketOrderOpenidSearch" class="col-sm-4 control-label">微信openid：</label>
    <div class="col-sm-8">
        <input id="ticketOrderOpenidSearch" name="ticketOrderOpenid" class="form-control" placeholder="请输入微信openid">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="ticketOrderOrderNoSearch" class="col-sm-4 control-label">订单编号：</label>
    <div class="col-sm-8">
        <input id="ticketOrderOrderNoSearch" name="ticketOrderOrderNo" class="form-control" placeholder="请输入订单编号">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="ticketOrderOrderTimeStartSearch" class="col-sm-4 control-label">下单时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="ticketOrderOrderTimeStartSearch" name="ticketOrderOrderTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择下单时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="ticketOrderOrderTimeEndSearch" class="col-sm-4 control-label">下单时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="ticketOrderOrderTimeEndSearch" name="ticketOrderOrderTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择下单时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="ticketOrderUnitPriceSearch" class="col-sm-4 control-label">单价：</label>
    <div class="col-sm-8">
        <input id="ticketOrderUnitPriceSearch" name="ticketOrderUnitPrice" class="form-control" placeholder="请输入单价">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="ticketOrderTotalSeatSearch" class="col-sm-4 control-label">总座位数：</label>
    <div class="col-sm-8">
        <input id="ticketOrderTotalSeatSearch" name="ticketOrderTotalSeat" class="form-control" placeholder="请输入总座位数">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="ticketOrderTotalPriceSearch" class="col-sm-4 control-label">支付总额：</label>
    <div class="col-sm-8">
        <input id="ticketOrderTotalPriceSearch" name="ticketOrderTotalPrice" class="form-control" placeholder="请输入支付总额">
    </div>
</div>



                <div class="col-sm-6 form-group text-right" style="padding-right: 30px;">
                    <button type="button" class="btn btn-primary" onclick="doSearch('data-list')">
                    <i class="fa fa-search"></i>
                        搜索
                    </button>
                    <button type="button" class="btn btn-primary" onclick="doSearchAll('data-list', 'search-form')">
                        <i class="fa fa-search"></i>
                        搜索所有
                    </button>
                    <button type="button" class="btn btn-primary" onclick="hideSearchForm('data-list', 'search-form')">
                        <i class="fa fa-eye-slash"></i>
                        取消搜索
                    </button>
                </div>
            </form>
        </div>
    </div>

</div>

<div class="modal fade" id="add-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
        </div>
    </div>
</div>

<div class="modal fade" id="edit-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
        </div>
    </div>
</div>

<div class="modal fade" id="detail-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
        </div>
    </div>
</div>

</body>
<%@include file="../master/include-js.jsp"%>
<script src="<%=path%>/static/js/address.js"></script>
<script src="<%=path%>/static/js/UserTicketOrder/UserTicketOrder.js"></script>
</html>
