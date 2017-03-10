$(function () {
    $('#list_data').datagrid({
        title: '活动系统列表',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        fitColumns: true,
        fit: true,//自动大小
        url: '/manage/activity/findAll',
        //sortName: 'code',
        //sortOrder: 'desc',
        loadMsg: '数据加载中，请等待！',
        remoteSort: false,
        idField: 'id',
        method: 'get',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        columns: [[
            {field: 'activityName', title: '活动姓名', width: 80, align: 'center'},
            {
                field: 'activityDate', title: '活动日期', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    return value.year+'-'+value.monthValue+'-'+value.dayOfMonth;
                }
            },
            {field: 'cost', title: '活动经费', width: 120, align: 'center'},
            {
                field: 'status', title: '活动状态', width: 120, align: 'center',
                formatter: function(value,row,index){
                    if (row.status == 1){
                        return '有效';
                    } else {
                        return '无效';
                    }
                }
            },
            {
                field: 'id', title: '操作', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    var html = "<a href='javascript:void(0)' onclick='editActivity(" + index + ")'>编辑</a>";
                    return html;
                }
            }
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                $("#dlg").dialog("open").dialog('setTitle', '新增活动');
                $("#fm").form("clear");
                //openDialog("add_dialog","add");
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {

                $.messager.confirm("提示", "你确定要删除吗？", function (r) {
                    if (r) {
                        var ids = [];
                        var rows = $('#list_data').datagrid("getSelections");
                        for (var i = 0; i < rows.length; i++) {
                            ids.push(rows[i].id);
                        }
                        //将选择到的行存入数组并用,分隔转换成字符串，
                        //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
                        var delIds = ids.join(',');

                        deleteRows(delIds);
                    }
                })

            }
        }
        ]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageNumber: 1,
        pageList: [5, 10, 15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'

    });

    $('#list_data').datagrid({checkOnSelect:false});

    $('#activityType').combobox({
        url:'/manage/activity/types',
        valueField:'type',
        textField:'typeName'
    });

});

String.prototype.endWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;
    if (this.substring(this.length - s.length) == s)
        return true;
    else
        return false;
    return true;
};

function deleteRows(ids) {
    $.ajax({
        url: "/manage/activity/delete",
        data: "ids=" + ids,
        type: "post",
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.flag) {
                $("#list_data").datagrid('reload');
                $('#list_data').datagrid('clearSelections', 'none');

            }
            else {
                $.messager.alert("error", data.errMsg)
            }
        },
        error: function (data) {
            console.log(data);
            if(data.status== 405 || data.status == 403) {
                $.messager.alert("error", '未授权的操作')
            } else {
                $.messager.alert("error", '系统错误')
            }
        }
    })
}

function save() {
    $('#fm').form('submit', {
        onSubmit: function () {
            //进行表单验证
            //如果返回false阻止提交
        },
        success: function (data) {
            $("#dlg").dialog("close");
            data = toJson(data);
            if (data.flag) {
                $("#list_data").datagrid('reload');
            } else {
                $.messager.alert("error", data.errMsg)
            }
        }
    });
}

function toJson(str){
    var json = eval('(' + str + ')');
    return json;
}

function editActivity(index) {
    var row = ($("#list_data").datagrid("getRows"))[index];
    var dateVal = row.activityDate;
    var aDate =  dateVal.monthValue+'/'+dateVal.dayOfMonth+'/'+dateVal.year
    $("#fm").removeAttr("action");
    $("#fm").attr("action",$("#updateUrl").val());

    $("#id").val(row.id);
    $("#activityName").val(row.activityName);
    $('#cost').numberbox('setValue', row.cost);
    $("#remark").val(row.remark);
    $('#aDate').datebox('setValue', aDate)
    $("#dlg").window('open');
}