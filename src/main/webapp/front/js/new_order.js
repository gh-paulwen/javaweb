var javaweb_order = {};
$(function() {
    javaweb.check(0, "login.html");
    //{order:{store:1,address:1},orderDetails:[{product:1,count:1},{product:2,count:1}]}
    var total = 0;
    javaweb_order.data = {};
    javaweb_order.orderMap = {};
    javaweb_order.orderDetailsArr = [];
    if (location.search === "") {
        //从购物车中取出
        var apCart = javaweb.createAP("/cartJson?method=getVerbose");
        apCart.success = function(json) {
            var verboses = json.listVerbose,
                order_details = $("#orderDetails");
            javaweb_order.orderMap["store"] = verboses[0].store;
            for (var i = 0; i < verboses.length; i++) {
                var verbose = verboses[i],
                    divod = $(document.createElement("div")),
                    divrow = $(document.createElement("div")),
                    div1 = $(document.createElement("div")),
                    div2 = $(document.createElement("div")),
                    img = $(document.createElement("img")),
                    h3 = $(document.createElement("h3")),
                    p = $(document.createElement("p")),
                    h2 = $(document.createElement("h2")),
                    h3count = $(document.createElement("h3")),
                    h3heji = $(document.createElement("h3")),
                    heji = verbose.count * verbose.price;
                var od = {};
                od["product"] = verbose.id;
                od["count"] = verbose.count;
                javaweb_order.orderDetailsArr.push(od);
                divod.click((function(pid) {
                    return function() {
                        location.href = "product_detail.html?pid=" + pid;
                    };
                })(verbose.id));
                total += heji;
                divrow.addClass("row");
                div1.addClass("col-xs-4");
                div2.addClass("col-xs-4");
                img.attr("style", "width:180px;");
                img.attr("src", javaweb.base + "/file?method=retrive&img=" + verbose.pic);
                h3.append("商品名称：" + verbose.name);
                p.append("商品描述：" + verbose.description);
                h2.append(`商品价格:<span style="color:red;">${verbose.price}</span>`);
                h3heji.append(`商品合计:<span style="color:red;">${heji}</span>`);
                h3count.append(`商品数量：${verbose.count}`);

                div2.append(h3);
                div2.append(p);
                div2.append(h2);
                div2.append(h3count);
                div2.append(h3heji);
                div1.append(img);
                divod.append(div1);
                divod.append(div2);
                divod.addClass("orderDetail");
                divod.attr("style", "min-height:250px;");
                order_details.append(divod);
            }
            $("#info_total").html(total);
        };
        $.ajax(apCart);
    } else {
        //直接购买
        //?product=8&count=3
        var search = location.search.substr(1);
        var arr = search.split("&");
        var params = {};
        for (var i = 0; i < arr.length; i++) {
            var ele = arr[i];
            var arr2 = ele.split("=");
            params[arr2[0]] = arr2[1];
        }
        var apGet = javaweb.createAP("/productJson?method=getVerbose&id=" + params["product"]);
        var order_details = $("#orderDetails");
        apGet.success = function(json) {
            var product = json.product;
            javaweb_order.orderMap["store"] = product.storeid;
            var od = {};
            od["product"] = product.id;
            od["count"] = params["count"];
            javaweb_order.orderDetailsArr.push(od);
            divod = $(document.createElement("div")),
                divrow = $(document.createElement("div")),
                div1 = $(document.createElement("div")),
                div2 = $(document.createElement("div")),
                img = $(document.createElement("img")),
                h3 = $(document.createElement("h3")),
                p = $(document.createElement("p")),
                h2 = $(document.createElement("h2")),
                h3count = $(document.createElement("h3")),
                h3heji = $(document.createElement("h3")),
                heji = params["count"] * product.price;
            divod.click((function(pid) {
                return function() {
                    location.href = "product_detail.html?pid=" + pid;
                };
            })(product.id));
            total = heji;
            divrow.addClass("row");
            div1.addClass("col-xs-4");
            div2.addClass("col-xs-4");
            img.attr("style", "width:180px;");
            img.attr("src", javaweb.base + "/file?method=retrive&img=" + product.pic);
            h3.append("商品名称：" + product.productname);
            p.append("商品描述：" + product.description);
            h2.append(`商品价格:<span style="color:red;">${product.price}</span>`);
            h3heji.append(`商品合计:<span style="color:red;">${heji}</span>`);
            h3count.append(`商品数量：${params["count"]}`);

            div2.append(h3);
            div2.append(p);
            div2.append(h2);
            div2.append(h3count);
            div2.append(h3heji);
            div1.append(img);
            divod.append(div1);
            divod.append(div2);
            divod.addClass("orderDetail");
            divod.attr("style", "min-height:250px;");
            order_details.append(divod);
            $("#info_total").html(total);
        };
        $.ajax(apGet);
    }
});


$(function() {
    //加载地址
    var apAddress = javaweb.createAP("/addressJson?method=getVerbose");
    apAddress.success = function(json) {
        var verboses = json.listVerbose;
        var first = true;
        for (var i = 0; i < verboses.length; i++) {
            var verbose = verboses[i];
            var div = $(document.createElement("tr"));
            div.addClass("col-xs-3");
            div.addClass("address");
            var pRegion = $(document.createElement("p"));
            var pVerbose = $(document.createElement("p"));
            var pName = $(document.createElement("p"));
            var pPhone = $(document.createElement("p"));
            pRegion.append(verbose.region);
            pVerbose.append(verbose.verboseAddress);
            pName.append(verbose.receiverName);
            pPhone.append(verbose.receiverPhone);
            div.append(pRegion);
            div.append(pVerbose);
            div.append(pName);
            div.append(pPhone);
            div.click((function(aid, divthis) {
                return function() {
                    javaweb_order.orderMap["address"] = aid;
                    $("#addresses").find(".address").css("border", "");
                    divthis.css("border", "1px solid grey");
                };
            })(verbose.id, div));
            if (first) {
                div.css("border", "1px solid grey");
                javaweb_order.orderMap.address = verbose.id;
                first = false;
            }
            $("#addresses").append(div);
        }
    };
    $.ajax(apAddress);
});

$(function() {
    $("#btn_confirm").click(function() {
        var confirm_code = confirm("提交订单？");
        if (confirm_code) {
            javaweb_order.data["order"] = javaweb_order.orderMap;
            javaweb_order.data["orderDetails"] = javaweb_order.orderDetailsArr;
            var datastr = JSON.stringify(javaweb_order.data);

            var apOrder = javaweb.createAP("/orderJson");
            apOrder.type = "post";
            apOrder.data = {
                "method": "save",
                "json": datastr
            };

            apOrder.success = function(json) {
                alert(json.message);
                location.href = "order.html";
            };
            $.ajax(apOrder);
        }
    });
});