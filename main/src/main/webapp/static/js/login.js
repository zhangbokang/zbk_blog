/**
 * Created by zhangbokang on 2017/6/4.
 */
function loginFn() {
    var $uObj = $("#username");
    var $pObj = $("#password");
    var $errorMsg = $("#errorMsg");
    var username = $uObj.val();
    var password = $pObj.val();
    if (!$.trim(username)) {
        $errorMsg.text("请输入用户名！");
        $uObj.focus();
        return;
    }
    if (!$.trim(password)) {
        $errorMsg.text("请输入密码！");
        $pObj.focus();
        return;
    }
    var passwordMD5 = md5(password);
    var userData = new Object();
    userData.username = username;
    userData.password = passwordMD5;
    //发送登陆请求
    $.ajax({
        url: "/loginAuth",
        dataType: "json",
        type: "POST",
        data: userData,
        success: function (result) {
            if (result.code == 0) {
                //失败
                $errorMsg.text(result.msg);
                return;
            }
            if (result.code == 1) {
//                    $.cookie("userId",result.data.userId,{path:"/"});
                window.location = "/admin";
                return;
            }
        },
        error: function () {
            $errorMsg.text("登陆请求失败，请稍后重试！");
            return;
        }
    });
}