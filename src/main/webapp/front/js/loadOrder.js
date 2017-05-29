javaweb.loadOrder = function(ap) {
    if (ap) {
        ap.success = function(json) {
            var orders = json.listOrder;
            for (var i = 0; i < orders.length; i++) {
                var order = orders[i].order;
                var ods = orders[i].ods;
                //创建.order div
                var divorder = $(document.createElement("div"));
                divorder.addClass("order");

                //创建订单创建时间，状态
                var div_date_status = $(document.createElement("div"));
                var span_date = $(document.createElement("span"));
                var span_status = $(document.createElement("span"));
                span_date.append("订单创建时间：" + order.createDate + "&nbsp;&nbsp;&nbsp;&nbsp;");
                span_status.append("订单状态: " + order.status + "<br>");
                div_date_status.append(span_date);
                div_date_status.append(span_status);
                divorder.append(div_date_status);

                //店铺名
                var div_storename = $(document.createElement("div"));
                var span_storename = $(document.createElement("span"));
                span_storename.append("店铺名：" + order.storename);
                div_storename.append(span_storename);
                divorder.append(div_storename);

                //收件人 收件人电话
                var div_receiver = $(document.createElement("div"));
                var span_receiver_name = $(document.createElement("span"));
                var span_receiver_phone = $(document.createElement("span"));
                span_receiver_name.append("收件人:" + order.receiverName + "&nbsp;&nbsp;&nbsp;&nbsp;");
                span_receiver_phone.append("收件人电话：" + order.receiverPhone + "<br>");
                div_receiver.append(span_receiver_name);
                div_receiver.append(span_receiver_phone);
                divorder.append(div_receiver);

                //地区 详细地址
                var div_region = $(document.createElement("div"));
                var span_region = $(document.createElement("span"));
                var span_verboseAddress = $(document.createElement("span"));
                span_region.append("地区：" + order.region + "&nbsp;&nbsp;&nbsp;&nbsp;");
                span_verboseAddress.append("详细地址：" + order.verboseAddress + "<br>");
                div_region.append(span_region);
                div_region.append(span_verboseAddress);
                divorder.append(div_region);

                var div_total_price = $(document.createElement("div"));
                var span_price = $(document.createElement("span"));
                div_total_price.append(span_price);
                divorder.append(div_total_price);
                divorder.append("<hr>");
                var div_ontop = $(document.createElement("div"));
                div_ontop.addClass("row");
                var div_ods = $(document.createElement("div"));
                div_ods.addClass("col-xs-10");
                var div_space = $(document.createElement("div"));
                div_space.addClass("col-xs-1");
                for (var j = 0; j < ods.length; j++) {
                    var od = ods[j];
                    //创建.od div
                    var divod = $(document.createElement("div"));
                    divod.addClass("od");
                    var divrow = $(document.createElement("div"));
                    divrow.addClass("row");
                    //图片div
                    var divimg = $(document.createElement("div"));
                    var img = $(document.createElement("img"));
                    img.attr("src", javaweb.base + "/file?method=retrive&img=" + od.pic);
                    img.attr("style", "width:240px;");
                    divimg.addClass("col-xs-4");
                    divimg.append(img);
                    var divdesc = $(document.createElement("div"));
                    divdesc.addClass("col-xs-8");
                    var pname = $(document.createElement("p"));
                    var pcategory = $(document.createElement("p"));
                    var pdesc = $(document.createElement("p"));
                    var pprice = $(document.createElement("p"));
                    var pcount = $(document.createElement("p"));
                    var ptotal = $(document.createElement("p"));
                    pname.append("商品名：" + od.name);
                    pcategory.append("分类：" + od.category);
                    pdesc.append("描述：" + od.description);
                    pprice.append("商品单价：" + od.price);
                    pcount.append("商品数量：" + od.count);
                    ptotal.append("商品总价：" + od.count * od.price);
                    divdesc.append(pname);
                    divdesc.append(pcategory);
                    divdesc.append(pdesc);
                    divdesc.append(pprice);
                    divdesc.append(pcount);
                    divdesc.append(ptotal);
                    divrow.append(divimg);
                    divrow.append(divdesc);
                    divod.append(divrow);
                    div_ods.append(divod);
                    div_ods.append("<hr>");
                }
                div_ontop.append(div_space);
                div_ontop.append(div_ods);
                divorder.append(div_ontop);
                $("#orders").append(divorder);
            }
        };
        $.ajax(ap);
    }
};