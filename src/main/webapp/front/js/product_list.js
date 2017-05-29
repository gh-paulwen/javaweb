$(function() {
    var reqStr = "/productJson?method=all";
    if (location.search) {
        var search = location.search.substr(1);
        var arr = search.split("&");
        var params = {};
        for (var i = 0; i < arr.length; i++) {
            var ele = arr[i];
            var arr2 = ele.split("=");
            params[arr2[0]] = arr2[1];
        }
        if (params.keyword) {
            //search
            reqStr = "/productJson?method=search&keyword=" + params.keyword;
        } else if (params.category) {
            //category
            reqStr = "/productJson?method=categoryAll&category=" + params.category;
        }
    }


    var apProduct = javaweb.createAP(reqStr);
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
            div.addClass("col-xs-3 product");
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
            div_products.append("<div class='col-xs-1' style='margin-top:20px;'></div>");
        }
    };

    $.ajax(apProduct);
});