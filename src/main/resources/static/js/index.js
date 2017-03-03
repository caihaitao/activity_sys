$(function () {
    $(".vote").click(function () {
        var activityId = $(this).parent().parent().find("input[name=activityId]").val();
        $.ajax({
            url:$("#signUpURL").val(),
            data:'activityId=' + activityId,
            type:'post',
            dataType:'json',
            success:function(data) {
                if(data.flag) {
                    $.messager.alert('报名结果','报名成功!','info');
                } else {
                    $.messager.alert('报名结果',data.errMsg,'error');
                }
            },
            error : function(data) {
                alert(data)
            }

        })
    });

});