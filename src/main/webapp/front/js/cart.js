$(function() {
    javaweb.check(0, "login.html");
    var apCart = javaweb.createAP("/cartJson?method=getVerbose");
    apCart.success = function(json) {
        var verboses = json.listVerbose,
            total = 0,
            div_carts = $("#div_carts");
        for (var i = 0; i < verboses.length; i++) {
            var verbose = verboses[i],
                divcart = $(document.createElement("div")),
                divrow = $(document.createElement("div")),
                div1 = $(document.createElement("div")),
                div2 = $(document.createElement("div")),
                div3 = $(document.createElement("div")),
                img = $(document.createElement("img")),
                h3 = $(document.createElement("h3")),
                p = $(document.createElement("p")),
                h2 = $(document.createElement("h2")),
                h3heji = $(document.createElement("h3")),
                h3count = $(document.createElement("h3")),
                heji = verbose.count * verbose.price,
                adelete = $(document.createElement("a"));
            divcart.click((function(pid) {
                return function() {
                    location.href = "product_detail.html?pid=" + pid;
                };
            })(verbose.id));
            total += heji;
            divrow.addClass("row");
            div1.addClass("col-xs-4");
            div2.addClass("col-xs-4");
            div3.addClass("col-xs-4");
            img.attr("style", "width:180px;");
            img.attr("src", javaweb.base + "/file?method=retrive&img=" + verbose.pic);
            h3.append("商品名称：" + verbose.name);
            p.append("商品描述：" + verbose.description);
            h2.append(`商品价格:<span style="color:red;">${verbose.price}</span>`);
            h3heji.append(`商品合计:<span style="color:red;">${heji}</span>`);
            h3count.append(`商品数量：${verbose.count}`);

            adelete.append("删除");
            adelete.click((function(pid) {
                return function() {
                    var apDelete = javaweb.createAP("/cartJson?method=delete&product=" + pid);
                    apDelete.success = function(json) {
                        alert(json.message);
                    }
                    $.ajax(apDelete);
                };
            })(verbose.id));
            div3.append(adelete);
            div2.append(h3);
            div2.append(p);
            div2.append(h2);
            div2.append(h3count);
            div2.append(h3heji);
            div1.append(img);
            divcart.append(div1);
            divcart.append(div2);
            divcart.append(div3);
            divcart.addClass("cart");
            divcart.attr("style", "min-height:250px;");
            div_carts.append(divcart);
        }
        $("#info_total").html(total);
    };
    $.ajax(apCart);
});