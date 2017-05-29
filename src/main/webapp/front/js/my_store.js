$(function() {
    var id = location.search.split("=")[1];
    $("#btn_add_product").attr("href", "add_product.html?storeid=" + id);
    var apStore = javaweb.createAP("/storeJson?method=getVerbose&id=" + id);
    var apLoadOrder = javaweb.createAP("/orderJson?method=getByStore&store=" + id);
    apStore.success = function(json) {
        var store = json.store;
        $("#info_name").html(store.storename);
        $("#info_user_name").html(store.username);
        $("#info_register_date").html(store.registerDate);
        $("#info_region").html(store.region);
    };
    $.ajax(apStore);

    var apProduct = javaweb.createAP("/productJson?method=getByStore&store=" + id);
    apProduct.success = function(json) {
        var products = json.listProduct;
        var div_products = $("#div_products");
        for (var i = 0; i < products.length; i++) {
            var product = products[i];
            var div = $(document.createElement("div"));
            div.click((function(pid) {
                return function() {
                    location.href = "product_detail.html?pid=" + pid;
                };
            })(product.id));
            div.addClass("col-xs-3 productdiv");
            var img = $(document.createElement("img"));
            img.attr("src", javaweb.base + "/file?method=retrive&img=" + product.pic);
            img.attr("style", "width:240px;");
            var h3 = $(document.createElement("h3"));
            h3.append(product.name);
            var h3price = $(document.createElement("h3"));
            h3price.append("优惠价：" + product.price);
            h3price.attr("style", "color:red;");
            div.append(img);
            div.append(h3);
            div.append(h3price);
            div_products.append(div);
            div_products.append("<div class='col-xs-1'></div>");

        }
    };
    $.ajax(apProduct);
    javaweb.loadOrder(apLoadOrder);
});