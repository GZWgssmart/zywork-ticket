$(function () {
    loadTable();
    initICheck('green');
    initDatetime();
});

function loadTable() {
    destroyTable('data-list');
    $('#data-list').bootstrapTable({
        url: contextPath + '/user-order/pager-cond',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        idField: 'ticketOrderId',
        pagination: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 20, 30],
        queryParams: queryParams,
        sortable: true,
        singleSelect: false,
        maintainSelected: true,
        striped: true,
        search: false,
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        detailView: true,
        detailFormatter: formatDetail,
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
	field: 'ticketOrderId',
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
	title: '订单编号',
	field: 'ticketOrderOrderNo',
	align: 'center',
	sortable: true
},
{
	title: '下单时间',
	field: 'ticketOrderOrderTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '单价',
	field: 'ticketOrderUnitPrice',
	align: 'center',
	sortable: true
},
{
	title: '总座位数',
	field: 'ticketOrderTotalSeat',
	align: 'center',
	sortable: true
},
{
	title: '支付总额',
	field: 'ticketOrderTotalPrice',
	align: 'center',
	sortable: true
},
{
	title: '名称',
	field: 'ticketItemTitle',
	align: 'center',
	sortable: true
},
{
	title: '封面图片',
	field: 'ticketItemHeadImg',
	align: 'center',
	formatter: formatImg
},
{
	title: '放映时间',
	field: 'ticketItemPlayTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '微信openid',
	field: 'userOpenid',
	align: 'center',
	sortable: true
},
            {
                title: '昵称',
                field: 'userNickname',
                align: 'center',
                sortable: true
            },
            {
                title: '座位信息',
                field: 'allSeatsString',
                align: 'center',
				formatter: formatSeats
            },
                     {
                         title: '操作',
                         field: '_operation',
                         align: 'center',
                         events: operateEvents,
                         formatter: formatOperators,
                         class: 'operation-column'
                     }
                 ]
    });
}
function formatImg(index, row) {
    if (row.ticketItemHeadImg != null && row.ticketItemHeadImg != '') {
        return '<img src="' + contextPath + '/' + row.ticketItemHeadImg + '" style="width:60px;height:60px;"/>';
    } else {
        return '-';
    }
}

function formatSeats(index, row) {
	return '<div style="width: 200px;">' + row.allSeatsString + '</div>';
}

function formatOperators(value, row, index) {
    let strArray = [];
    strArray.push('<div class="btn-group">');
    strArray.push('<button type="button" class="to-detail btn btn-primary"><i class="fa fa-list"></i>&nbsp;详情</button>');
    strArray.push('<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">');
    strArray.push('<span class="caret"></span>');
    strArray.push('<span class="sr-only">Toggle Dropdown</span>');
    strArray.push('</button>');
    strArray.push('<ul class="dropdown-menu">');
    strArray.push('<li><a href="javascript:void(0)" class="to-edit"><i class="fa fa-edit "></i>&nbsp;修改</a></li>');
    if (row.ticketOrderIsActive === 0) {
        strArray.push('<li><a href="javascript:void(0)" class="to-inactive"><i class="fa fa-minus-square-o text-danger"></i><span class="text-danger">&nbsp;冻结</span></a></li>');
    } else {
        strArray.push('<li><a href="javascript:void(0)" class="to-active"><i class="fa fa-check-square-o text-success"></i><span class="text-success">&nbsp;激活</span></a></li>');
    }
    strArray.push('<li><a href="javascript:void(0)" class="to-remove"><i class="fa fa-remove text-danger"></i><span class="text-danger">&nbsp;删除</span></a></li>');
    strArray.push('</ul>');
    strArray.push('</div>');
    return strArray.join('');
}

let fieldTitles = {'ticketOrderTicketItemId':'剧目编号','ticketOrderOpenid':'微信openid','ticketOrderOrderNo':'订单编号','ticketOrderOrderTime-date':'下单时间','ticketOrderUnitPrice':'单价','ticketOrderTotalSeat':'总座位数','ticketOrderTotalPrice':'支付总额','ticketItemId':'编号','ticketItemTitle':'名称','ticketItemHeadImg':'封面图片','ticketItemPlayTime-date':'放映时间','userOpenid':'微信openid'};

window.operateEvents = {
    'click .to-detail': function (e, value, row, index) {
        showRemoteDetailModal('detail-modal', '/user-order/detail-modal', row, fieldTitles);
    },
    'click .to-edit': function (e, value, row, index) {
        showRemoteEditModal('edit-modal', '/user-order/edit-modal', 'edit-form', row, validateFields());
    },
    'click .to-inactive': function (e, value, row, index) {
        active('/user-order/active', row.ticketOrderId, 1, 'data-list', '/user-order/pager-cond');
    },
    'click .to-active': function (e, value, row, index) {
        active('/user-order/active', row.ticketOrderId, 0, 'data-list', '/user-order/pager-cond');
    },
    'click .to-remove': function (e, value, row, index) {
        remove('/user-order/remove/' + row.ticketOrderId, 'data-list', '/user-order/pager-cond');
    }
};

function formatDetail(index, row) {
    let detail = '';
    $.each(fieldTitles, function (field, title) {
        let fieldArray = field.split("-");
        let value = row[fieldArray[0]];
        if (value !== undefined) {
            detail += '<div class="col-xs-12 col-sm-4 col-md-2 col-lg-2">'
                + '<span class="row-detail-title">'
                + title
                + ':</span>'
                + '</div><div class="col-xs-12 col-sm-8 col-md-4 col-lg-4">'
                + (value === null ? '-' : fieldArray[1] !== undefined && fieldArray[1] === 'date' ? timestampToDatetime(value) : value)
                + '</div>';
        }
    });
    return detail;
}

function queryParams(params) {
    let query = {
        limit: params.limit,
        offset: params.offset,
        sort: params.sort,
        order: params.order
    };
    $.each($('#search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function validateFields() {
    return {
        
ticketOrderTicketItemId: {
	validators: {
	}
},
ticketOrderOpenid: {
	validators: {

		stringLength: {
			min: 0,
			max: 200,
			message: '必须小于200个字符'
		}
	}
},
ticketOrderOrderNo: {
	validators: {

		stringLength: {
			min: 0,
			max: 100,
			message: '必须小于100个字符'
		}
	}
},
ticketOrderOrderTime: {
	validators: {
	}
},
ticketOrderUnitPrice: {
	validators: {
	}
},
ticketOrderTotalSeat: {
	validators: {
	}
},
ticketOrderTotalPrice: {
	validators: {
	}
},
ticketItemId: {
	validators: {
		notEmpty: {
			message: '编号是必须项'
		}
	}
},
ticketItemTitle: {
	validators: {
		notEmpty: {
			message: '名称是必须项'
		},
		stringLength: {
			min: 1,
			max: 100,
			message: '必须是1-100个字符'
		}
	}
},
ticketItemHeadImg: {
	validators: {

		stringLength: {
			min: 0,
			max: 500,
			message: '必须小于500个字符'
		}
	}
},
ticketItemPlayTime: {
	validators: {
		notEmpty: {
			message: '放映时间是必须项'
		}
	}
},
userOpenid: {
	validators: {

		stringLength: {
			min: 0,
			max: 200,
			message: '必须小于200个字符'
		}
	}
}
    };
}
