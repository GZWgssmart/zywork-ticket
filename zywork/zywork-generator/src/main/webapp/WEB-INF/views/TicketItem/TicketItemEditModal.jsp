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
            <label for="playTimeStr" class="col-sm-2 control-label">放映时间：</label>
            <div class="col-sm-10">
                <input id="playTimeStr" name="playTimeStr" class="form-control" placeholder="请输入放映时间，格式为2018-07-28 12:00;2018-07-28 16:00"/>
            </div>
        </div>

        <div class="form-group">
            <label for="address" class="col-sm-2 control-label">放映地点：</label>
            <div class="col-sm-10">
                <select id="address" name="address" class="form-control" data-placeholder="请选择放映地点"></select>
            </div>
        </div>

        <div class="form-group">
            <label for="price" class="col-sm-2 control-label">A区原价：</label>
            <div class="col-sm-10">
                <input id="price" name="price" class="form-control" placeholder="请输入A区原价"/>
            </div>
        </div>

        <div class="form-group">
            <label for="unitPrice" class="col-sm-2 control-label">A区优惠价：</label>
            <div class="col-sm-10">
                <input id="unitPrice" name="unitPrice" class="form-control" placeholder="请输入A区优惠价"/>
            </div>
        </div>
        <div class="form-group">
            <label for="priceB" class="col-sm-2 control-label">B区原价：</label>
            <div class="col-sm-10">
                <input id="priceB" name="priceB" class="form-control" placeholder="请输入B区原价"/>
            </div>
        </div>

        <div class="form-group">
            <label for="unitPriceB" class="col-sm-2 control-label">B区优惠价：</label>
            <div class="col-sm-10">
                <input id="unitPriceB" name="unitPriceB" class="form-control" placeholder="请输入B区优惠价"/>
            </div>
        </div>
        <div class="form-group">
            <label for="priceC" class="col-sm-2 control-label">C区原价：</label>
            <div class="col-sm-10">
                <input id="priceC" name="priceC" class="form-control" placeholder="请输入C区原价"/>
            </div>
        </div>

        <div class="form-group">
            <label for="unitPriceC" class="col-sm-2 control-label">C区优惠价：</label>
            <div class="col-sm-10">
                <input id="unitPriceC" name="unitPriceC" class="form-control" placeholder="请输入C区优惠价"/>
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
