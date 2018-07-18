$(function () {
    loadTable();
    initICheck('green');
    initDatetime();
});

function loadTable() {
    destroyTable('data-list');
    $('#data-list').bootstrapTable({
        url: contextPath + '/scheduler/pager-cond',
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
	title: '作业名称',
	field: 'name',
	align: 'center',
	sortable: true
},
{
	title: '作业完整类名',
	field: 'className',
	align: 'center',
	sortable: true
},
{
	title: 'CRON表达式',
	field: 'cronExpression',
	align: 'center',
	sortable: true
},
{
	title: '作业组名称',
	field: 'groupName',
	align: 'center',
	sortable: true
},
{
	title: '触发器名称',
	field: 'triggerName',
	align: 'center',
	sortable: true
},
{
	title: '触发器组',
	field: 'triggerGroup',
	align: 'center',
	sortable: true
},
{
	title: '作业描述',
	field: 'description',
	align: 'center',
	sortable: true
},
{
	title: '作业状态',
	field: 'jobStatus',
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
	title: '最近一次更新时间',
	field: 'updateTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '是否可用',
	field: 'isActive',
	align: 'center',
	sortable: true
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
    if (row.isActive === 0) {
        strArray.push('<li><a href="javascript:void(0)" class="to-inactive"><i class="fa fa-minus-square-o text-danger"></i><span class="text-danger">&nbsp;冻结</span></a></li>');
    } else {
        strArray.push('<li><a href="javascript:void(0)" class="to-active"><i class="fa fa-check-square-o text-success"></i><span class="text-success">&nbsp;激活</span></a></li>');
    }
    strArray.push('<li><a href="javascript:void(0)" class="to-remove"><i class="fa fa-remove text-danger"></i><span class="text-danger">&nbsp;删除</span></a></li>');
    strArray.push('</ul>');
    strArray.push('</div>');
    return strArray.join('');
}

let fieldTitles = {'id':'作业编号','name':'作业名称','className':'作业完整类名','cronExpression':'CRON表达式','groupName':'作业组名称','triggerName':'触发器名称','triggerGroup':'触发器组','description':'作业描述','jobStatus':'作业状态','createTime-date':'创建时间','updateTime-date':'最近一次更新时间','isActive':'是否可用'};

window.operateEvents = {
    'click .to-detail': function (e, value, row, index) {
        showRemoteDetailModal('detail-modal', '/scheduler/detail-modal', row, fieldTitles);
    },
    'click .to-edit': function (e, value, row, index) {
        showRemoteEditModal('edit-modal', '/scheduler/edit-modal', 'edit-form', row, validateFields());
    },
    'click .to-inactive': function (e, value, row, index) {
        active('/scheduler/active', row.id, 1, 'data-list', '/scheduler/pager-cond');
    },
    'click .to-active': function (e, value, row, index) {
        active('/scheduler/active', row.id, 0, 'data-list', '/scheduler/pager-cond');
    },
    'click .to-remove': function (e, value, row, index) {
        remove('/scheduler/remove/' + row.id, 'data-list', '/scheduler/pager-cond');
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
        
name: {
	validators: {
		notEmpty: {
			message: '作业名称是必须项'
		},
		stringLength: {
			min: 1,
			max: 50,
			message: '必须是1-50个字符'
		}
	}
},
className: {
	validators: {
		notEmpty: {
			message: '作业完整类名是必须项'
		},
		stringLength: {
			min: 1,
			max: 200,
			message: '必须是1-200个字符'
		}
	}
},
cronExpression: {
	validators: {
		notEmpty: {
			message: 'CRON表达式是必须项'
		},
		stringLength: {
			min: 1,
			max: 50,
			message: '必须是1-50个字符'
		}
	}
},
groupName: {
	validators: {

		stringLength: {
			min: 0,
			max: 50,
			message: '必须小于50个字符'
		}
	}
},
triggerName: {
	validators: {

		stringLength: {
			min: 0,
			max: 50,
			message: '必须小于50个字符'
		}
	}
},
triggerGroup: {
	validators: {

		stringLength: {
			min: 0,
			max: 50,
			message: '必须小于50个字符'
		}
	}
},
description: {
	validators: {

		stringLength: {
			min: 0,
			max: 500,
			message: '必须小于500个字符'
		}
	}
},
jobStatus: {
	validators: {
	}
}   
    };
}
