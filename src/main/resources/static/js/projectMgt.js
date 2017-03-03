$(function () {
    $('#list_data').datagrid({
        title: '投票系统项目列表',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        fitColumns: true,
        fit: true,//自动大小
        url: '/manage/candidate/findAll',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                $("#dlg").dialog("open").dialog('setTitle', 'New Project');
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
        }]
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
        url: "/myVotes/vote.do?action=delProjects",
        data: "ids=" + ids,
        type: "post",
        dataType: "text",
        async: false,
        success: function (data) {
            if (data > 0) {
                $("#list_data").datagrid('reload');
                $('#list_data').datagrid('clearSelections', 'none');

            }
            else {
                $.messager.alert("error", "删除失败！")
            }
        },
        error: function () {
            $.messager.alert("error", "删除失败！")
        }
    })
}

function saveProject() {
    var img = $.trim($("#img").val());
    var isLegalImg = false;
    //jpg、bmp、gif
    if (img.endWith(".jpg") || img.endWith(".bmp") || img.endWith("gif")) {
        isLegalImg = true;
    }
    if (!isLegalImg) {
        $.messager.alert("error", "上传图片格式不正确，暂只支持jpg、bmp、gif格式图片！");
        return;
    }
    $("#fm").attr("action", "/myVotes/vote.do?action=addProjects");
    $("#fm").ajaxSubmit({
            type: "POST",
            success: function (data) {
                $("#dlg").dialog("close");
                if (data == 102) {
                    $("#list_data").datagrid('reload');
                }
                else if (data == 100) {
                    $.messager.alert("error", "添加失败，上传图片大于1M！")
                }
                else if (data == 101) {
                    $.messager.alert("error", "添加失败，上传图片格式不正确！")
                }
            }
        }
    )
}