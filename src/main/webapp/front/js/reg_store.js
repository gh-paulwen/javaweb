$(document).ready(function() {
    var apInfo = javaweb.createAP("/userJson?method=getInfo");
    apInfo.success = function(json) {
        $("#info_name").html(json.info.name);
    };
    $.ajax(apInfo);

    $("#btn_register").click(function() {
        var apStore = javaweb.createAP("/storeJson");
        apStore.type = "POST";
        apStore.data = {
            "method": "save",
            "region": $("#select_city").val(),
            "name": $("#input_store_name").val()
        };
        apStore.success = function(json) {
            alert(json.message);
        };
        $.ajax(apStore);
    });

});