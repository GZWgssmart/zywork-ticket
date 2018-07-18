<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">添加</h4>
</div>
<div class="modal-body">
    <form id="add-form" class="form-horizontal row" enctype="multipart/form-data">
        <div class="form-group">
    <label for="email" class="col-sm-2 control-label">邮箱：</label>
    <div class="col-sm-10">
        <input id="email" name="email" class="form-control" placeholder="请输入邮箱"/>
    </div>
</div>

<div class="form-group">
    <label for="phone" class="col-sm-2 control-label">手机号：</label>
    <div class="col-sm-10">
        <input id="phone" name="phone" class="form-control" placeholder="请输入手机号"/>
    </div>
</div>

<div class="form-group">
    <label for="accountName" class="col-sm-2 control-label">账户名：</label>
    <div class="col-sm-10">
        <input id="accountName" name="accountName" class="form-control" placeholder="请输入账户名"/>
    </div>
</div>

<div class="form-group">
    <label for="password" class="col-sm-2 control-label">密码：</label>
    <div class="col-sm-10">
        <input id="password" name="password" class="form-control" placeholder="请输入密码"/>
    </div>
</div>

<div class="form-group">
    <label for="salt" class="col-sm-2 control-label">加密盐值：</label>
    <div class="col-sm-10">
        <input id="salt" name="salt" class="form-control" placeholder="请输入加密盐值"/>
    </div>
</div>

<div class="form-group">
    <label for="headicon" class="col-sm-2 control-label">头像：</label>
    <div class="col-sm-10">
        <input id="headicon" name="headicon" class="form-control" placeholder="请输入头像"/>
    </div>
</div>

<div class="form-group">
    <label for="nickname" class="col-sm-2 control-label">昵称：</label>
    <div class="col-sm-10">
        <input id="nickname" name="nickname" class="form-control" placeholder="请输入昵称"/>
    </div>
</div>

<div class="form-group">
    <label for="gender" class="col-sm-2 control-label">性别：</label>
    <div class="col-sm-10">
        <input id="gender" name="gender" class="form-control" placeholder="请输入性别"/>
    </div>
</div>

<div class="form-group">
    <label for="openid" class="col-sm-2 control-label">微信openid：</label>
    <div class="col-sm-10">
        <input id="openid" name="openid" class="form-control" placeholder="请输入微信openid"/>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-save" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-save', 'add-modal', 'add-form', '/user/save', 'data-list', '/user/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
