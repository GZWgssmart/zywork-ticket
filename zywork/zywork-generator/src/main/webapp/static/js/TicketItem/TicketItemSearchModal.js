function ticketItemSearchTable() {
    destroyTable('tickeitem-data-list');
    $('#tickeitem-data-list').bootstrapTable({
        url: contextPath + '/tickeitem/pager-cond',
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
	title: '名称',
	field: 'title',
	align: 'center',
	sortable: true
},
{
	title: '封面图片',
	field: 'headImg',
	align: 'center',
	sortable: true
},
{
	title: '放映时间',
	field: 'playTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '原价',
	field: 'price',
	align: 'center',
	sortable: true
},
{
	title: '优惠价',
	field: 'unitPrice',
	align: 'center',
	sortable: true
},
{
	title: '放映地点',
	field: 'address',
	align: 'center',
	sortable: true
},
{
	title: '描述',
	field: 'description',
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
    $.each($('#tickeitem-search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function ticketItemSearchModal(modalId, url) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        ticketItemSearchTable;
        initDatetime();
    });
    modal.modal({
        remote: url
    });
}
