<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">编辑</h4>
</div>
<div class="modal-body">
    <form id="edit-form" class="form-horizontal row" enctype="multipart/form-data">
        <input type="hidden" name="id"/>
        <div class="form-group">
    <label for="title" class="col-sm-2 control-label">标题：</label>
    <div class="col-sm-10">
        <input id="title" name="title" class="form-control" placeholder="请输入标题"/>
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
    <button id="btn-edit" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-edit', 'edit-modal', 'edit-form', '/role/update', 'data-list', '/role/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
