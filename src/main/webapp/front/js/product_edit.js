$(function() {

    var search = location.search.substr(1);
    var arr = search.split("&");
    var params = {};
    for (var i = 0; i < arr.length; i++) {
        var ele = arr[i];
        var arr2 = ele.split("=");
        params[arr2[0]] = arr2[1];
    }

    if (!params.product) {
        alert("缺少参数");
        return;
    }
    if (!params.store) {
        alert("缺少参数");
        return;
    }

    var ap_load = javaweb.createAP("/productJson?method=getById&id=" + params["product"]);

    ap_load.success = function(json) {
        var product = json.product;
        $("#input_name").val(product.name);
        $("#input_price").val(product.price);
        $("#input_description").val(product.description);
        $("#select_category").val(product.category);
        $("#select_sec_category").val(product.secCategory);
        javaweb.loadSec(product.category, $("#select_sec_category"));
    };

    javaweb.loadCategory(ap_load);

    $("#btn_edit").click(function() {
        var ap_edit = javaweb.createAP("/productJson");
        ap_edit.success = function(json) {};
        ap_edit.type = "post";
        ap_edit.data = {
            "method": "update",
            "id": params["product"],
            "name": $("#input_name").val(),
            "price": $("#input_price").val(),
            "description": $("#input_description").val(),
            "secCategory": $("#select_sec_category").val(),
            "store": params["store"]
        };
        ap_edit.success = function(json) {
            alert(json.message);
            if (json.message === "更新商品成功") {
                location.href = "my_store.html?sid=" + params["store"];
            }
        };
        $.ajax(ap_edit);
    });
});