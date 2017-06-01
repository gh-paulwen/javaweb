$(function() {
    //加载个人信息
    var apInfo = javaweb.createAP("/userJson?method=getInfo");
    apInfo.success = function(json) {
        var info = json.info;
        $("#input_username").val(info.name);
        $("#input_email").val(info.email);
    };
    $.ajax(apInfo);
});

$(function() {
    $("#btn_edit").click(function() {
        var apEdit = javaweb.createAP("/userJson");
        apEdit.type = "post";
        apEdit.data = {
            "method": "update",
            "username": $("#input_username").val(),
            "email": $("#input_email").val()
        };
        apEdit.success = function(json) {
            alert(json.message);
            if (json.message != '修改成功') {
                return;
            }
            location.href = "my.html";
        };

        $.ajax(apEdit);
    });
});