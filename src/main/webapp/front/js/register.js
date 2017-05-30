$(function() {
    $("#btn_register").click(function() {
        var username = $("#input_username").val();
        var email = $("#input_email").val();
        var password = $("#input_password").val();
        var repeat_password = $("#input_repeat_password").val();

        if (password === repeat_password) {
            var regap = javaweb.createAP("/userJson");
            regap.type = "post";
            regap.data = {
                "method": "register",
                "username": username,
                "email": email,
                "password": password
            };
            regap.success = function(json) {
                alert(json.message);
            };
            $.ajax(regap);
        } else {
            alert("两次输入密码不一致");
        }
    });
});