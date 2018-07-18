<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>TicketItem</title>
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
            <button class="btn btn-primary" onclick="showRemoteAddModal('add-modal', '/tickeitem/add-modal', 'add-form', validateFields())">
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
                    <a href="javascript:void(0);" onclick="batchActive('/tickeitem/batch-active', 0, 'data-list', '/tickeitem/pager-cond', 'id')">
                        <i class="fa fa-check-square-o text-success"></i><span class="text-success">&nbsp;批量激活</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="batchActive('/tickeitem/batch-active', 1, 'data-list', '/tickeitem/pager-cond', 'id')">
                        <i class="fa fa-minus-square-o text-danger"></i><span class="text-danger">&nbsp;批量冻结</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="batchRemove('/tickeitem/batch-remove', 'data-list', '/tickeitem/pager-cond', 'id')">
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
    <label for="titleSearch" class="col-sm-4 control-label">名称：</label>
    <div class="col-sm-8">
        <input id="titleSearch" name="title" class="form-control" placeholder="请输入名称">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="headImgSearch" class="col-sm-4 control-label">封面图片：</label>
    <div class="col-sm-8">
        <input id="headImgSearch" name="headImg" class="form-control" placeholder="请输入封面图片">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="playTimeStartSearch" class="col-sm-4 control-label">放映时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="playTimeStartSearch" name="playTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择放映时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="playTimeEndSearch" class="col-sm-4 control-label">放映时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="playTimeEndSearch" name="playTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择放映时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="priceSearch" class="col-sm-4 control-label">原价：</label>
    <div class="col-sm-8">
        <input id="priceSearch" name="price" class="form-control" placeholder="请输入原价">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="unitPriceSearch" class="col-sm-4 control-label">优惠价：</label>
    <div class="col-sm-8">
        <input id="unitPriceSearch" name="unitPrice" class="form-control" placeholder="请输入优惠价">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="addressSearch" class="col-sm-4 control-label">放映地点：</label>
    <div class="col-sm-8">
        <input id="addressSearch" name="address" class="form-control" placeholder="请输入放映地点">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="descriptionSearch" class="col-sm-4 control-label">描述：</label>
    <div class="col-sm-8">
        <input id="descriptionSearch" name="description" class="form-control" placeholder="请输入描述">
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
<script src="<%=path%>/static/js/TicketItem/TicketItem.js"></script>
</html>
