$(function () {


});

function validateUsername() {
    $.ajax({
        url:$("#validateUsernameURL").val(),
        data:'username='+$("#un").val(),
        type:'post',
        dataType:'json',
        success:function(data) {
            if(data.flag) {
                $("#errMsg").html("");
            } else {
                $("#errMsg").html(data.errMsg);
                $("#un").focus();
            }
        },
        error : function(data) {
            alert(data)
        }

    })
}

function validatePwd() {
    var password = $("#password").val();
    var confirmPwd = $("#confirmpassword").val();
    if(password != confirmPwd) {
        $("#errMsg").html("两次输入密码不同");
    } else {
        $("#errMsg").html("");
    }
}

function sb() {
    $("#registerForm").form('submit',{
        onSubmit:function() {
            var username = $("#un").val();
            var password = $("#password").val();
            var confirmPwd = $("#confirmpassword").val();

            if(typeof(username) == undefined || $.trim(username)=='') {
                return false;
            }
            if(typeof (password) == undefined || $.trim(password) == '') {
                return false;
            }
            if(password != confirmPwd) {
                return false;
            }
        },
        success : function(data) {
            alert("注册成功");
            window.location.href="/doLogin";
        }
    })
}
