$(document).ready(function() {
    javaweb.check("my.html");
    $("#btn_login").click(function() {
        var ap = javaweb.createAP("/userJson");
        ap.type = "POST";
        ap.data = {
            "method": "login",
            "username": $("#input_username").val(),
            "password": $("#input_password").val()
        };
        ap.success = function(json) {
            alert(json.message);
            if (json.message === "登录成功") {
                location.href = "my.html";
            }
        }
        $.ajax(ap);
    });
});