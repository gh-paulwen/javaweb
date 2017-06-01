$(function() {
    javaweb.check(0, "login.html");
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

            var div_edit = $(document.createElement("div"));
            div_edit.css("text-align", "center");
            var a_edit = $(document.createElement("a"));
            a_edit.addClass("mybtn");
            a_edit.append("修改商品");
            a_edit.attr("href", `product_edit.html?product=${product.id}&store=${product.store}`);
            div_edit.append(a_edit);
            div.append(div_edit);
            div_products.append(div);
            div_products.append("<div class='col-xs-1' style='margin-top:20px;'></div>");
        }
    };
    $.ajax(apProduct);
    javaweb.loadOrder(apLoadOrder, 1);
});