$(document).ready(function() {
    javaweb.check(0, "login.html");
    //submit
    $("#btn_save").click(function() {
        var apSubmit = javaweb.createAP("/addressJson");
        apSubmit.type = "POST";
        apSubmit.success = function(json) {
            alert(json.message);
        };
        apSubmit.data = {
            "method": "save",
            "region": $("#select_city").val(),
            "verboseAddress": $("#input_verbose_address").val(),
            "receiverName": $("#input_receiver_name").val(),
            "receiverPhone": $("#input_receiver_phone").val()
        };
        $.ajax(apSubmit);
    });

});