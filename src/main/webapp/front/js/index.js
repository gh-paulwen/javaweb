$(function() {
    var apCategory = javaweb.createAP("/categoryJson?method=getAllCategory");
    apCategory.success = function(json) {
        var categorys = json.listCategory;
        var ulcategory = $("#ulcategory");
        for (var i = 0; i < categorys.length; i++) {
            var cate = categorys[i];
            var li = $(document.createElement("li"));
            li.addClass("categoryitem");
            var a = $(document.createElement("a"));
            a.attr("href", "product_list.html?category=" + cate.id);
            a.append(cate.name);
            li.append(a);
            ulcategory.append(li);
        }
    };
    $.ajax(apCategory);
    var loadSomeProduct = function(target) {
        var apRandom = javaweb.createAP("/productJson?method=random");
        apRandom.success = function(json) {
            var products = json.listProduct;
            for (var i = 0; i < products.length; i++) {
                var product = products[i];
                var div = $(document.createElement("div"));
                div.click((function(pid) {
                    return function() {
                        location.href = "product_detail.html?pid=" + pid;
                    };
                })(product.id));
                div.addClass("col-xs-4 productdiv");
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
                target.append(div);
            };
        };
        $.ajax(apRandom);
    };
    loadSomeProduct($("#divp1"));
    loadSomeProduct($("#divp2"));
    loadSomeProduct($("#divp3"));
});


$(function() {
    $("#btn_search").click(function() {
        var keyword = $("#input_search").val();
        if (keyword === "") {
            alert("请输入关键字");
            return;
        }
        location.href = "product_list.html?keyword=" + keyword;
    });
});