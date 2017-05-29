$(function() {
    var id = location.search.split("=")[1];
    var apInfo = javaweb.createAP("/productJson?method=getVerbose&id=" + id);
    apInfo.success = function(json) {
        var product = json.product;
        $("#info_name").html(product.productname);
        $("#info_price").html(product.price).css("color", "red");
        $("#info_store").html(product.storename);
        $("#info_category").html(product.category);
        $("#info_create_date").html(product.createDate);
        $("#info_description").html(product.description);
        $("#img_pic").attr("src", javaweb.base + "/file?method=retrive&img=" + product.pic);
    };
    $.ajax(apInfo);

    $("#btn_cart").click(function() {
        var apCart = javaweb.createAP("/cartJson");
        apCart.type = "post";
        apCart.data = {
            "method": "save",
            "product": id,
            "count": $("#input_count").val()
        };
        apCart.success = function(json) {
            alert(json.message);
        };
        $.ajax(apCart);
    });

    $("#btn_collect").click(function() {
        var apCollect = javaweb.createAP("/collectJson");
        apCollect.type = "post";
        apCollect.data = {
            "method": "collect",
            "product": id
        };
        apCollect.success = function(json) {
            alert(json.message);
        };

        $.ajax(apCollect);
    });

    $("#btn_add").click(function() {
        var value = $("#input_count").val();
        var v2 = window.parseInt(value);
        $("#input_count").val(v2 + 1);
    });

    $("#btn_decrease").click(function() {
        var value = window.parseInt($("#input_count").val());
        if (value > 1) {
            $("#input_count").val(value - 1);
        }
    });
    $("#btn_buy").click(function() {
        var count = $("#input_count").val()
        alert(count);
        window.location.href = `new_order.html?product=${id}&count=${count}`;
    });
});