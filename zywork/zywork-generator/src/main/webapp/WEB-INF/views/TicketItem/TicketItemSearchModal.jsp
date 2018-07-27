<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">选择</h4>
</div>
<div class="modal-body">
    <table id="tickeitem-data-list" data-classes="table table-hover text-nowrap" data-toolbar="#tickeitem-toolbar"></table>

    <div id="tickeitem-toolbar">
        <button class="btn btn-primary" onclick="">
            <i class="fa fa-key"></i>
            确认
        </button>
        <button class="btn btn-primary" onclick="showSearchForm('tickeitem-search-form')">
            <i class="fa fa-eye"></i>
            高级搜索
        </button>
        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>

        <form id="tickeitem-search-form" class="row form-horizontal search-form" style="display: none;">
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
                <label for="playTimeStrSearch" class="col-sm-4 control-label">放映时间：</label>
                <div class="col-sm-8">
                    <input id="playTimeStrSearch" name="playTimeStr" class="form-control" placeholder="请输入放映时间">
                </div>
            </div>

            <div class="col-sm-6 form-group">
                <label for="priceSearch" class="col-sm-4 control-label">A区原价：</label>
                <div class="col-sm-8">
                    <input id="priceSearch" name="price" class="form-control" placeholder="请输入A区原价">
                </div>
            </div>

            <div class="col-sm-6 form-group">
                <label for="unitPriceSearch" class="col-sm-4 control-label">A区优惠价：</label>
                <div class="col-sm-8">
                    <input id="unitPriceSearch" name="unitPrice" class="form-control" placeholder="请输入A区优惠价">
                </div>
            </div>
            <div class="col-sm-6 form-group">
                <label for="priceBSearch" class="col-sm-4 control-label">B区原价：</label>
                <div class="col-sm-8">
                    <input id="priceBSearch" name="priceB" class="form-control" placeholder="请输入B区原价">
                </div>
            </div>

            <div class="col-sm-6 form-group">
                <label for="unitPriceBSearch" class="col-sm-4 control-label">B区优惠价：</label>
                <div class="col-sm-8">
                    <input id="unitPriceBSearch" name="unitPriceB" class="form-control" placeholder="请输入B区优惠价">
                </div>
            </div>

            <div class="col-sm-6 form-group">
                <label for="priceCSearch" class="col-sm-4 control-label">C区原价：</label>
                <div class="col-sm-8">
                    <input id="priceCSearch" name="priceC" class="form-control" placeholder="请输入C区原价">
                </div>
            </div>

            <div class="col-sm-6 form-group">
                <label for="unitPriceCSearch" class="col-sm-4 control-label">C区优惠价：</label>
                <div class="col-sm-8">
                    <input id="unitPriceCSearch" name="unitPriceC" class="form-control" placeholder="请输入C区优惠价">
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
                <button type="button" class="btn btn-primary" onclick="doSearch('tickeitem-data-list')">
                    <i class="fa fa-search"></i>
                    搜索
                </button>
                <button type="button" class="btn btn-primary" onclick="doSearchAll('tickeitem-data-list', 'tickeitem-search-form')">
                    <i class="fa fa-search"></i>
                    搜索所有
                </button>
                <button type="button" class="btn btn-primary" onclick="hideSearchForm('tickeitem-data-list', 'tickeitem-search-form')">
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
