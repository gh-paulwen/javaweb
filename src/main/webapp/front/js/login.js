$(document).ready(function() {
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
        }
        $.ajax(ap);
    });
});