<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">编辑</h4>
</div>
<div class="modal-body">
    <form id="edit-form" class="form-horizontal row" enctype="multipart/form-data">
        <input type="hidden" name="id"/>
        <div class="form-group">
    <label for="title" class="col-sm-2 control-label">名称：</label>
    <div class="col-sm-10">
        <input id="title" name="title" class="form-control" placeholder="请输入名称"/>
    </div>
</div>

<div class="form-group">
    <label for="headImg" class="col-sm-2 control-label">封面图片：</label>
    <div class="col-sm-10">
        <input type="file" id="headImg" name="headImgFile" class="form-control" placeholder="请上传封面图片"/>
    </div>
</div>

<div class="form-group">
    <label for="playTime" class="col-sm-2 control-label">放映时间：</label>
    <div class="input-group date form_datetime col-sm-10" style="padding-left: 15px; padding-right: 15px;">
        <input id="playTime" name="playTime" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择放映时间"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="form-group">
    <label for="price" class="col-sm-2 control-label">原价：</label>
    <div class="col-sm-10">
        <input id="price" name="price" class="form-control" placeholder="请输入原价"/>
    </div>
</div>

<div class="form-group">
    <label for="unitPrice" class="col-sm-2 control-label">优惠价：</label>
    <div class="col-sm-10">
        <input id="unitPrice" name="unitPrice" class="form-control" placeholder="请输入优惠价"/>
    </div>
</div>

<div class="form-group">
    <label for="address" class="col-sm-2 control-label">放映地点：</label>
    <div class="col-sm-10">
        <input id="address" name="address" class="form-control" placeholder="请输入放映地点"/>
    </div>
</div>

<div class="form-group">
    <label for="description" class="col-sm-2 control-label">描述：</label>
    <div class="col-sm-10">
        <input id="description" name="description" class="form-control" placeholder="请输入描述"/>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-edit" type="button" class="btn btn-primary" onclick="saveOrEditWithFile('btn-edit', 'edit-modal', 'edit-form', '/tickeitem/update', 'data-list', '/tickeitem/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
