function ticketOrderSearchTable() {
    destroyTable('tickeorder-data-list');
    $('#tickeorder-data-list').bootstrapTable({
        url: contextPath + '/tickeorder/pager-cond',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        idField: 'id',
        pagination: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 20, 30],
        queryParams: queryParams,
        singleSelect: false,
        maintainSelected: true,
        striped: true,
        search: false,
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        icons: {
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        },
        columns: [
                     {
	field: '_checkbox',
	checkbox: true
},
{
	field: 'id',
	align: 'center',
	visible: false
},
{
	title: '序号',
	field: '_number',
	align: 'center',
	formatter: formatTableIndex
},
{
	title: '剧目编号',
	field: 'ticketItemId',
	align: 'center',
	sortable: true
},
{
	title: '用户编号',
	field: 'userId',
	align: 'center',
	sortable: true
},
{
	title: '微信openid',
	field: 'openid',
	align: 'center',
	sortable: true
},
{
	title: '订单编号',
	field: 'orderNo',
	align: 'center',
	sortable: true
},
{
	title: '下单时间',
	field: 'orderTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '单价',
	field: 'unitPrice',
	align: 'center',
	sortable: true
},
{
	title: '总座位数',
	field: 'totalSeat',
	align: 'center',
	sortable: true
},
{
	title: '支付总额',
	field: 'totalPrice',
	align: 'center',
	sortable: true
},
{
	title: '创建时间',
	field: 'createTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '更新时间',
	field: 'updateTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
}
                 ]
    });
}

function queryParams(params) {
    let query = {
        limit:params.limit,
        offset:params.offset,
        sortOrder: params.order
    };
    $.each($('#tickeorder-search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function ticketOrderSearchModal(modalId, url) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        ticketOrderSearchTable;
        initDatetime();
    });
    modal.modal({
        remote: url
    });
}
