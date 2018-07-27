$(function () {
    loadTable();
    initICheck('green');
    initDatetime();
});

function loadTable() {
    destroyTable('data-list');
    $('#data-list').bootstrapTable({
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
	title: '名称',
	field: 'title',
	align: 'center',
	sortable: true
},
{
	title: '封面图片',
	field: 'headImg',
	align: 'center',
    formatter: formatImg
},
{
	title: '放映时间',
	field: 'playTimeStr',
	align: 'center',
	sortable: true
},
{
	title: 'A区原价',
	field: 'price',
	align: 'center',
	sortable: true
},
{
	title: 'A区优惠价',
	field: 'unitPrice',
	align: 'center',
	sortable: true
},
            {
                title: 'B区原价',
                field: 'priceB',
                align: 'center',
                sortable: true
            },
            {
                title: 'B区优惠价',
                field: 'unitPriceB',
                align: 'center',
                sortable: true
            },
            {
                title: 'C区原价',
                field: 'priceC',
                align: 'center',
                sortable: true
            },
            {
                title: 'C区优惠价',
                field: 'unitPriceC',
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

let fieldTitles = {'id':'编号','title':'名称','headImg':'封面图片','playTime-date':'放映时间','price':'原价','unitPrice':'优惠价','address':'放映地点','description':'描述','createTime-date':'创建时间','updateTime-date':'更新时间'};

window.operateEvents = {
    'click .to-detail': function (e, value, row, index) {
        showRemoteDetailModal('detail-modal', '/tickeitem/detail-modal', row, fieldTitles);
    },
    'click .to-edit': function (e, value, row, index) {
        showRemoteEditModal('edit-modal', '/tickeitem/edit-modal', 'edit-form', row, validateFields());
    },
    'click .to-inactive': function (e, value, row, index) {
        active('/tickeitem/active', row.id, 1, 'data-list', '/tickeitem/pager-cond');
    },
    'click .to-active': function (e, value, row, index) {
        active('/tickeitem/active', row.id, 0, 'data-list', '/tickeitem/pager-cond');
    },
    'click .to-remove': function (e, value, row, index) {
        remove('/tickeitem/remove/' + row.id, 'data-list', '/tickeitem/pager-cond');
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

function formatImg(index, row) {
    if (row.headImg != null && row.headImg != '') {
        return '<img src="' + contextPath + '/' + row.headImg + '" style="width:60px;height:60px;"/>';
    } else {
        return '-';
    }
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

        title: {
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
        headImg: {
            validators: {

                notEmpty: {
                    message: '封面图片是必须项'
                }
            }
        },
        playTimeStr: {
            validators: {
                notEmpty: {
                    message: '放映时间是必须项'
                }
            }
        },
        price: {
            validators: {
                notEmpty: {
                    message: 'A区原价是必须项'
                }
            }
        },
        unitPrice: {
            validators: {
                notEmpty: {
                    message: 'A区优惠价是必须项'
                }
            }
        },
        priceB: {
            validators: {
                notEmpty: {
                    message: 'B区原价是必须项'
                }
            }
        },
        unitPriceB: {
            validators: {
                notEmpty: {
                    message: 'B区优惠价是必须项'
                }
            }
        },
        priceC: {
            validators: {
                notEmpty: {
                    message: 'C区原价是必须项'
                }
            }
        },
        unitPriceC: {
            validators: {
                notEmpty: {
                    message: 'C区优惠价是必须项'
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
}  
    };
}
